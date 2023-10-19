package LogicaDeBingo;
import java.util.ArrayList;

/**
 * Clase Jugador
 * Abstracción en código de los jugadores del bingo
 * 
 * @author (Heldyis Agüero) 
 * @version (16/10/2023 )
 */
public class Jugador {
    
    private Carton carton;
    private String nombreCompleto;
    private int cedula;
    private String correo;
    private ArrayList<Carton> cartonesAsignados;
    
    public Jugador(String pNombreCompleto, int pCedula,String pCorreo) {
        this.nombreCompleto = pNombreCompleto;
        this.cedula = pCedula;
        this.correo = pCorreo;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public int getCedula() {
        return cedula;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    private void asignarCarton(Carton pCarton) {
        cartonesAsignados.add(pCarton);
    }
}
