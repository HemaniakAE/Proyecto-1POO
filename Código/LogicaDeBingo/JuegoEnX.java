package LogicaDeBingo;

import Ventanas.MenuEnviarCarton;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Heldyis Agüero 
 */
public class JuegoEnX extends Juego {
  private static ArrayList<Carton> cartonesGanadores;
  
  public JuegoEnX() {
        
    cartonesGanadores = new ArrayList<>();
  }
  
  public static boolean buscarGanadores(ArrayList<Integer> pNumerosCantados,ArrayList<String> pCartonesAsignados) {
    
    ArrayList<Carton> cartonesAsignados = convertirCodigosACartones(pCartonesAsignados, Carton.listaCartonesGenerados);
    for(Carton carton : cartonesAsignados) {
      int[][] matriz = carton.getMatriz();
      ArrayList<Integer> diagonales = obtenerDiagonales(matriz);
      boolean cartonGanador = true;
        for (Integer numero : diagonales) {
            if (!pNumerosCantados.contains(numero)) {
                cartonGanador = false;
                break; // No es necesario continuar verificando
            }
        }
        if (cartonGanador) {
            cartonesGanadores.add(carton);
        }
    }
    return !cartonesGanadores.isEmpty();
  }
  
  private static ArrayList<Carton> convertirCodigosACartones(ArrayList<String> codigos, ArrayList<Carton> cartonesDisponibles) {
    ArrayList<Carton> cartonesAsignados = new ArrayList<>();

    for (String codigo : codigos) {
        Carton cartonAsignado = null;
        for (Carton carton : cartonesDisponibles) {
            if (carton.getCodigoCarton().equals(codigo)) {
                cartonAsignado = carton;
                break;
            }
        }
        if (cartonAsignado != null) {
            cartonesAsignados.add(cartonAsignado);
        }
    }
    return cartonesAsignados;
}

  
  private static ArrayList<Integer> obtenerDiagonales(int[][] matriz) {
        ArrayList<Integer> diagonales = new ArrayList<>();

        int n = matriz.length;
        // Recorre la diagonal principal
        for (int i = 0; i < n; i++) {
            diagonales.add(matriz[i][i]);
        }
        // Recorre la diagonal secundaria
        for (int i = 0; i < n; i++) {
            diagonales.add(matriz[i][n - 1 - i]);
        }
        // Elimina el número en el centro de la matriz si se repite
        if (n % 2 == 1) {
            int centro = n / 2;
            diagonales.remove(Integer.valueOf(matriz[centro][centro]));
        }

        return diagonales;
    }
    
  public static ArrayList<String> getListaCodigosCartonesGanadores() {
    ArrayList<String> codigosCartonesGanadores = new ArrayList<>();

    for (Carton carton : cartonesGanadores) {
        codigosCartonesGanadores.add(carton.getCodigoCarton());
    }

    return codigosCartonesGanadores;
  }
  
  public static ArrayList<Carton> getCartonesGanadores() {
    return cartonesGanadores;
  }


  
}
