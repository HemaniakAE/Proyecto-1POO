package LogicaDeBingo;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase juego.
 * Abstracción de la clase juego.
 * 
 * @author (Heldyis Agüero Espinoza) 
 * @version (17/10/20)
 */
public class Juego {
    
    private Jugador jugador;
    private Configuracion tipoDeJuego;
    private double premio;
    private ArrayList<Integer> listaDeBolas;
    private ArrayList<Jugador> listaJugadores;
    private static int totalJugadores;
    private LocalDate fechaDelJuego;
    private LocalTime horaDelJuego;
    
    public Juego(Configuracion pTipoDeJuego, double pPremio, LocalDate pFechaDelJuego, LocalTime pHoraDelJuego) {
        this.tipoDeJuego = pTipoDeJuego;
        this.premio = pPremio;
        this.fechaDelJuego = pFechaDelJuego;
        this.horaDelJuego = pHoraDelJuego; 
        totalJugadores = 0;
    }
    
    public boolean validarJugador(Jugador pJugador) {
        boolean jugadorRepetido = false;
        
        for (Jugador jugadorExistente : listaJugadores) {
            if (jugadorExistente.getCedula() == pJugador.getCedula()) {
                jugadorRepetido = true;
                break;
            }
        }
        
        if (!jugadorRepetido) {
            listaJugadores.add(pJugador);
            totalJugadores++;
            return true;
        } else {
            return false;
        }
    }
}