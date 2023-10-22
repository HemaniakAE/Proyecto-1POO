package LogicaDeBingo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Jugador {

  private String nombreCompleto;
  private int cedula;
  private String correo;
  private static final String FILENAME = "jugadores.csv";
  private ArrayList<Carton> cartonesAsignados;

  public Jugador(String pNombreCompleto, int pCedula, String pCorreo) {
    this.nombreCompleto = pNombreCompleto;
    this.cedula = pCedula;
    this.correo = pCorreo;
    cartonesAsignados = new ArrayList<>();
    
  }
  
  public String toString() {
        return "Nombre: " + nombreCompleto + ", DNI: " + cedula + ", Correo: " + correo;
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
  
  public void asignarCarton(Carton pCarton) {
    cartonesAsignados.add(pCarton);
  }
  
  public ArrayList<Carton> getCartonesAsignados() {
    return cartonesAsignados;
  }

  // Añade el jugador al archivo CSV
  public void guardar() throws IOException {
    if (!correoValido(correo)) {
      throw new IllegalArgumentException("Correo inválido");
    }
    if (cedulaExiste(this.cedula)) {
      throw new IllegalArgumentException("Cédula ya registrada");
    }
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) {
      bw.write(this.cedula + "," + this.nombreCompleto + "," + this.correo);
      bw.newLine();
    }
  }

  private static boolean cedulaExiste(int cedula) {
    try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
      String line;
      while ((line = br.readLine()) != null) {
        int cedulaExistente = Integer.parseInt(line.split(",")[0]);
        if (cedula == cedulaExistente) {
          return true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean correoValido(String email) {
    // Patrón para validar el email
    Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    return pattern.matcher(email).find();
  }

  public static List<String> listarJugadores() throws IOException {
    List<String> jugadores = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] data = line.split(",");
        String jugador = "Cédula: " + data[0] + ", Nombre: " + data[1] + ", Correo: " + data[2];
        jugadores.add(jugador);
      }
    } catch (IOException e) {
      throw e;
    }

    return jugadores;
  }

  public static void main(String[] args) {
    // Crear un jugador
    Jugador jugador1 = new Jugador("Ana Rodríguez", 23456789, "ana.rodriguez@example.com");
    Jugador jugador2 = new Jugador("Carlos Torres", 34567890, "carlos.torres@example.net");
    Jugador jugador3 = new Jugador("Beatriz Méndez", 45678901, "beatriz.mendez@example.org");

    try {
      // Guardar el jugador en el archivo CSV
      jugador1.guardar();
      jugador2.guardar();
      jugador3.guardar();
      System.out.println("Jugador registrado exitosamente!");
    } catch (IllegalArgumentException e) {
      // Mostrar errores como correo inválido o cédula ya registrada
      System.out.println(e.getMessage());
    } catch (IOException e) {
      // Mostrar errores relacionados con la manipulación del archivo
      System.out.println("Error al guardar el jugador: " + e.getMessage());
    }
  }
}
