package LogicaDeBingo;

import java.util.ArrayList;

/**
 * Clase Jugador Abstracción en código de los jugadores del bingo
 *
 * @author (Heldyis Agüero)
 * @version (16/10/2023 )
 */
public class Jugador {

  private Carton carton;
  private String nombreCompleto;
  private int cedula;
  private String correo;
  private ArrayList<String> cartonesAsignados;

  public Jugador(String pNombreCompleto, int pCedula, String pCorreo) {
    this.nombreCompleto = pNombreCompleto;
    this.cedula = pCedula;
    this.correo = pCorreo;
    cartonesAsignados = new ArrayList<>();
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

  public void asignarCarton(String pCodeCarton) {
    cartonesAsignados.add(pCodeCarton);
  }
  
  public ArrayList<String> getCartonesAsignados() {
    return cartonesAsignados;
  }
  
  public String toString() {
    StringBuilder cuerpoCorreo = new StringBuilder();
    cuerpoCorreo.append("Estimado ").append(nombreCompleto).append(",\n\n");
    cuerpoCorreo.append("Te enviamos a continuación adjuntamos tus cartones para el juego de bingo:\n\n");

    // Agrega los códigos de los cartones asignados
    cuerpoCorreo.append("Tus cartones:\n");
    for (String codigoCarton : cartonesAsignados) {
        cuerpoCorreo.append("- ").append(codigoCarton).append("\n");
    }

    cuerpoCorreo.append("\n");
    cuerpoCorreo.append("¡Buena suerte en el juego de bingo!\n\n");
    cuerpoCorreo.append("Atentamente,\n");
    cuerpoCorreo.append("El equipo de Bingo");

    return cuerpoCorreo.toString();
  }
}
