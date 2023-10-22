package LogicaDeBingo;

import static LogicaDeBingo.Configuracion.E;
import static LogicaDeBingo.Configuracion.L;
import static LogicaDeBingo.Configuracion.X;
import static LogicaDeBingo.Configuracion.Z;
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
  protected Bingo bingo;
  private Configuracion tipoDeJuego;
  private double premio;
  private ArrayList<Integer> numerosCantados;
  private LocalDate fechaDelJuego;
  private LocalTime horaDelJuego;
  

  public Juego(Configuracion pTipoDeJuego, double pPremio, Bingo pBingo) {
    this.tipoDeJuego = pTipoDeJuego;
    this.premio = pPremio;
    this.fechaDelJuego = LocalDate.now(ZoneId.systemDefault()); // Obtiene la fecha actual del sistema
    this.horaDelJuego = LocalTime.now(ZoneId.systemDefault()); // Obtiene la hora actual del sistema
    this.bingo = pBingo;
    numerosCantados = new ArrayList<>();
  }
  
  public int generarBolas() {
    int numeroCantado = bolas.generarNumeroAleatorio();
    numerosCantados.add(numeroCantado);
    return numeroCantado;
  }
  
  public ArrayList<Integer> getNumerosCantados() {
    return numerosCantados;
  }
  
  public static void main(String[] args) {
    Bola bolas = new Bola();
    Bingo bingo = new Bingo();
    Juego juego = new Juego(Configuracion.X, 500, bingo);
    int i = 0;
    while(i < 75){
      int bola = juego.generarBolas();
      System.out.println("bola: " + bola);
      i++;
    }
    System.out.println("Total de bolas generadas");
    System.out.println("Lista de bolas: " + juego.getNumerosCantados());
  }
}


