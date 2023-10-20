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
  public static int totalJugadores;

  public Bingo() {
    totalJugadores = 0;
    listaJugadores = new ArrayList<>(); // Inicializa la lista de jugadores
  }

  public boolean validarJugador(Jugador pJugador) {
    boolean jugadorRepetido = false; //Variables booleana para control

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
    Jugador newPlayer = new Jugador(nombreCompleto, cedula, correo);

    if (!validarJugador(newPlayer)) {
      //Añade al jugador al juego
      listaJugadores.add(newPlayer);
      totalJugadores++;
    }
  }

  public boolean enviarCartonAJugador(int pCantidadDeCartones, int pCedula) {
    int totalCartones = Carton.getContadorCartones();
    if (pCantidadDeCartones <= totalCartones) {
      Jugador jugadorEncontrado = null;
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
        // Aquí puedes trabajar con el archivo, por ejemplo, cargar o manipular el archivo.
        String codeCarton = archivo.getName(); //Obtiene el nombre de la imagen, que corresponde a su código
        jugadorEncontrado.asignarCarton(codeCarton); //Asigna el cartón al jugador correspondiente a la cédula
        String rutaImagen = archivo.getAbsolutePath(); //Obtiene la ruta absoluta de la imagen
        rutaImagenes.add(rutaImagen); //Agrega la ruta a la lista
      }
      String correoJugador = jugadorEncontrado.getCorreo();
      String cuerpoCorreo = jugadorEncontrado.toString();
      
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
}