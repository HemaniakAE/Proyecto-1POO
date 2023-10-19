package LogicaDeBingo;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.ZoneId;

/**
 * Clase juego.
 * Abstracción de la clase juego.
 * 
 * @author (Heldyis Agüero Espinoza) 
 * @version (17/10/20)
 */
public class Juego {
    
    private Jugador jugador;
    private Carton carton;
    private Configuracion tipoDeJuego;
    private double premio;
    private ArrayList<Integer> listaDeBolas;
    private ArrayList<Jugador> listaJugadores;
    private static int totalJugadores;
    private LocalDate fechaDelJuego;
    private LocalTime horaDelJuego;
    
    public Juego(Configuracion pTipoDeJuego, double pPremio) {
        this.tipoDeJuego = pTipoDeJuego;
        this.premio = pPremio;
        this.fechaDelJuego = LocalDate.now(ZoneId.systemDefault()); // Obtiene la fecha actual del sistema
        this.horaDelJuego = LocalTime.now(ZoneId.systemDefault()); // Obtiene la hora actual del sistema
        totalJugadores = 0;
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
        
        if (validarJugador(newPlayer)) {
            //Añade al jugador al juego
            listaJugadores.add(newPlayer);
            totalJugadores++;
        }
    }
    
    public boolean enviarCartonAJugador(int pCantidadDeCartones, int pCedula) {
        int totalCartones = Carton.getContadorCartones();
        if(pCantidadDeCartones <= totalCartones) {
            Jugador jugadorEncontrado = null;
            for (Jugador jugador : listaJugadores) {
                if (jugador.getCedula() == pCedula) {
                    jugadorEncontrado = jugador;
                    break; // Terminar el bucle si se encuentra el jugador
                }
            }
            if(jugadorEncontrado == null) {
                return false;
            }
            // Seleccionar n cartones aleatorios
            ArrayList<Carton> cartonesSeleccionados = new ArrayList<>();
            Random rand = new Random();
            
            while (cartonesSeleccionados.size() < pCantidadDeCartones) {
                int indiceAleatorio = rand.nextInt(totalCartones);
                Carton cartonAleatorio = Carton.listaCartonesGenerados.get(indiceAleatorio);
                
                if (!cartonesSeleccionados.contains(cartonAleatorio)) {
                    cartonesSeleccionados.add(cartonAleatorio);
                }
            }
            // Carpeta para almacenar los cartones seleccionados
            File carpetaCartonesSeleccionados = new File("cartonesSeleccionados");
            if (!carpetaCartonesSeleccionados.exists()) {
                carpetaCartonesSeleccionados.mkdir(); // Crea la carpeta si no existe
            }

            // Copiar los archivos de los cartones seleccionados en la carpeta
            for (Carton carton : cartonesSeleccionados) {
                String codigoCarton = carton.codigoCarton;
                File archivoOrigen = new File("cartones/" + codigoCarton + ".png");
                File archivoDestino = new File("cartonesSeleccionados/" + codigoCarton + ".png");

                try {
                    Files.copy(archivoOrigen.toPath(), archivoDestino.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Enviar los archivos de cartones al correo del jugador
            String correo = jugadorEncontrado.getCorreo();
            // Implementa el envío de correo con los archivos adjuntos aquí

            return true;
            
            
        } 
        return false; 
    }
    
   
    
    
    
}