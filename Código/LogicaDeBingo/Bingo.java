package LogicaDeBingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;

/**
 * Clase Bingo. Abstracción de la clase bingo.
 *
 * @author (Heldyis Agüero Espinoza)
 * @version (17/10/20)
 */
public class Bingo {

  
  private ArrayList<Jugador> listaJugadores;
  private ArrayList<Carton> cartonesYaAsignados;
  public static int totalJugadores;

  public Bingo() {
    totalJugadores = 0;
    listaJugadores = new ArrayList<>(); // Inicializa la lista de jugadores
    cartonesYaAsignados = new ArrayList<>();
  }

  public boolean validarJugador(Jugador pJugador) {
    boolean jugadorRepetido = false; //Variables booleana para control}
    
    for (Jugador jugadorExistente : listaJugadores) {
      //Comparación de las cédulas
      if (jugadorExistente.getCedula() == pJugador.getCedula()) {
        jugadorRepetido = true;
        return jugadorRepetido;
      }
    }
    return jugadorRepetido;
  }

  public void registrarJugador(String nombreCompleto, int cedula, String correo) {
    Jugador newPlayer = new Jugador(nombreCompleto, cedula, correo); //Crea la instancia jugador
    //Si validarJugador es false significa que el jugador no existe aun
    if (!validarJugador(newPlayer)) { 
      //Añade al jugador al juego
      listaJugadores.add(newPlayer);
      totalJugadores++;
    }
  }

  public boolean enviarCartonAJugador(int pCantidadDeCartones, int pCedula) {
    int totalCartones = Carton.getContadorCartones(); //Obtiene el total de cartones generados
    //Compara la cantidad de cartones total con la cantidad de cartones solicitados
    if (pCantidadDeCartones <= totalCartones) {
      Jugador jugadorEncontrado = null;
      //Busca el jugador a partir de la cedula designada
      for (Jugador jugador : listaJugadores) {
        if (jugador.getCedula() == pCedula) {
          jugadorEncontrado = jugador;
          break; // Terminar el bucle si se encuentra el jugador
        }
      }
      if (jugadorEncontrado == null) {
        return false;
      }
      List<File> cartonesAleatorios = obtenerArchivosAleatoriosEnCarpeta("Cartones", pCantidadDeCartones);

      if (cartonesAleatorios.isEmpty()) {
        return false; // No se encontraron archivos
      }
      List<String> rutaImagenes = new ArrayList<>(); //Crea una lista que contiene la ruta de las imagenes
      for (File archivo : cartonesAleatorios) {
            boolean cartonAsignado = false;
            while (!cartonAsignado) {
                // Aquí puedes trabajar con el archivo, por ejemplo, cargar o manipular el archivo.
                String nombreArchivo = archivo.getName();
                int indexOfExtension = nombreArchivo.lastIndexOf('.');
                if (indexOfExtension > 0) {
                    String codigoCarton = nombreArchivo.substring(0, indexOfExtension);
                    Carton cartonPorAsignar = Carton.getCartonPorCodigo(codigoCarton);
                    // Verifica si el cartón ya está asignado a otro jugador
                    if (cartonesYaAsignados.contains(cartonPorAsignar)) {
                        // Cartón ya asignado, elige otro aleatorio
                        cartonesAleatorios.remove(archivo); // Elimina el cartón repetido de la lista
                        if (cartonesAleatorios.isEmpty()) {
                            return false; // No hay más cartones disponibles
                        }
                        archivo = cartonesAleatorios.get(0); // Elige otro cartón aleatorio
                    } else {
                        // Cartón no asignado, asígnalo al jugador y agrega a la lista de asignados
                        jugadorEncontrado.asignarCarton(cartonPorAsignar);
                        cartonesYaAsignados.add(cartonPorAsignar);
                        cartonAsignado = true;
                        String rutaImagen = archivo.getAbsolutePath(); // Obtiene la ruta absoluta de la imagen
                        rutaImagenes.add(rutaImagen); // Agrega la ruta a la lista
                    }
                }
            }
        }
      String correoJugador = jugadorEncontrado.getCorreo();
      String cuerpoCorreo = jugadorEncontrado.toString();
      //Envío de mail
      CuentaCorreo correoBingo = new CuentaCorreo("bingoteclimon@gmail.com"); //Crea una instancia de correo
      correoBingo.enviarCorreo(correoJugador, "Cartones de Bingo",cuerpoCorreo , rutaImagenes);
      return true;
    }
    return false;
  }
  
  public List<File> obtenerArchivosAleatoriosEnCarpeta(String nombreCarpeta, int cantidadArchivos) {
    File carpeta = new File(nombreCarpeta);

    if (!carpeta.exists() || !carpeta.isDirectory()) {
        return new ArrayList<>(); // La carpeta no existe o no es una carpeta válida
    }
    File[] archivos = carpeta.listFiles();
    List<File> archivosAleatorios = new ArrayList<>();
    if (archivos != null) {
        int totalArchivos = archivos.length;
        Random random = new Random();

        while (archivosAleatorios.size() < cantidadArchivos && archivosAleatorios.size() < totalArchivos) {
            int indiceAleatorio = random.nextInt(totalArchivos);
            File archivoSeleccionado = archivos[indiceAleatorio];

            if (!archivosAleatorios.contains(archivoSeleccionado)) {
                archivosAleatorios.add(archivoSeleccionado);
            }
        }
    }
    return archivosAleatorios;
  }
 
  public int getTotalJugadores() {
    return totalJugadores;
  }
  
  public ArrayList<Jugador> getListaJugadores() {
    return listaJugadores;
  }
  
  public ArrayList<Carton> getCartonesYaAsignados() {
    return cartonesYaAsignados;
  }
}
