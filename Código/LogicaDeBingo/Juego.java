package LogicaDeBingo;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.io.File;
import java.time.ZoneId;



/**
 * Clase juego. Abstracción de la clase juego.
 *
 * @author (Heldyis Agüero Espinoza)
 * @version (18/10/20)
 */
public class Juego {

  private final Bola  bolas = new Bola();
  private Configuracion tipoDeJuego;
  private double premio;
  private final ArrayList<Integer> listaDeBolas;
  private final ArrayList<Jugador> listaJugadores;
  private ArrayList<Integer> numerosCantados;
  private LocalDate fechaDelJuego;
  private LocalTime horaDelJuego;

  public Juego(Configuracion pTipoDeJuego, double pPremio) {
    this.tipoDeJuego = pTipoDeJuego;
    this.premio = pPremio;
    this.fechaDelJuego = LocalDate.now(ZoneId.systemDefault()); // Obtiene la fecha actual del sistema
    this.horaDelJuego = LocalTime.now(ZoneId.systemDefault()); // Obtiene la hora actual del sistema
    listaJugadores = new ArrayList<>(); // Inicializa la lista de jugadores
    listaDeBolas = new ArrayList<>();
    numerosCantados = new ArrayList<>();
  }
  
  public void generarBolas() {
    int numeroCantado = bolas.generarNumeroAleatorio();
    numerosCantados.add(numeroCantado);
  }
}