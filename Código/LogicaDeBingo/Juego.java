package LogicaDeBingo;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.ZoneId;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.MessagingException;


/**
 * Clase juego. Abstracción de la clase juego.
 *
 * @author (Heldyis Agüero Espinoza)
 * @version (17/10/20)
 */
public class Juego {

  private Jugador jugador;
  private Carton carton;
  private CuentaCorreo correo;
  private Configuracion tipoDeJuego;
  private double premio;
  public ArrayList<Integer> listaDeBolas;
  public ArrayList<Jugador> listaJugadores;
  public static int totalJugadores;
  private LocalDate fechaDelJuego;
  private LocalTime horaDelJuego;

  public Juego(Configuracion pTipoDeJuego, double pPremio) {
    this.tipoDeJuego = pTipoDeJuego;
    this.premio = pPremio;
    this.fechaDelJuego = LocalDate.now(ZoneId.systemDefault()); // Obtiene la fecha actual del sistema
    this.horaDelJuego = LocalTime.now(ZoneId.systemDefault()); // Obtiene la hora actual del sistema
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
  public static void main(String[] args) {
    Configuracion tipoJuego = Configuracion.X; // Reemplaza TU_ENUM_AQUI con el valor de Configuracion que desees
    
    double premio = 100.0; // Premio de ejemplo

    Juego juego = new Juego(tipoJuego, premio);

    // Crea una lista de jugadores de ejemplo
    juego.listaJugadores = new ArrayList<>();
    juego.registrarJugador("Heldyis Agüero Espinoza", 123456, "heldyis1020@gmail.com");

    int cedulaJugador = 123456; // Cédula de un jugador existente
    int cantidadCartones = 5; // Cantidad de cartones para enviar

    if (juego.enviarCartonAJugador(cantidadCartones, cedulaJugador)) {
        System.out.println("Se enviaron los cartones al jugador con cédula " + cedulaJugador);
    } else {
        System.out.println("No se pudo enviar los cartones al jugador con cédula " + cedulaJugador);
    }
  }
  
}