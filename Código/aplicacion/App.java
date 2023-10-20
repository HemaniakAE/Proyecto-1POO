package aplicacion;

import LogicaDeBingo.Configuracion;
import LogicaDeBingo.Juego;
import LogicaDeBingo.Carton;
import LogicaDeBingo.Jugador;
import LogicaDeBingo.Bola;

import java.util.ArrayList;

public class App {
  
  public static void main(String[] args) {
    Carton carton = new Carton();
    carton.generarCartones(5);
    int cantidadDeCartones = carton.getContadorCartones();
    
    System.out.println("Cantidad de cartones creados : " + cantidadDeCartones);
    
    Configuracion tipoJuego = Configuracion.X; // Reemplaza TU_ENUM_AQUI con el valor de Configuracion que desees
    
    double premio = 100.0; // Premio de ejemplo

    Juego juego = new Juego(tipoJuego, premio);

    // Crea una lista de jugadores de ejemplo
    juego.listaJugadores = new ArrayList<>();
    juego.registrarJugador("Heldyis Agüero Espinoza", 123456, "heldyis1020@gmail.com");
    
    System.out.println("Lista de jugadores inscritos :" + juego.listaJugadores); 
    
    int cedulaJugador = 123456; // Cédula de un jugador existente
    int cantidadCartones = 5; // Cantidad de cartones para enviar

    if (juego.enviarCartonAJugador(cantidadCartones, cedulaJugador)) {
        System.out.println("Se enviaron los cartones al jugador con cédula " + cedulaJugador);
    } else {
        System.out.println("No se pudo enviar los cartones al jugador con cédula " + cedulaJugador);
    }
  }
}