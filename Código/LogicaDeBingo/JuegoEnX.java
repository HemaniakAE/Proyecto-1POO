package LogicaDeBingo;

import java.util.ArrayList;
/**
 *
 * @author Heldyis Agüero 
 */
public class JuegoEnX extends Juego {
  private ArrayList<Carton> cartonesGanadores;
  
  public JuegoEnX(Configuracion pTipoDeJuego, double pPremio, Bingo pBingo) {
        super(pTipoDeJuego, pPremio, pBingo);
        cartonesGanadores = new ArrayList<>();
  }
  
  public boolean buscarGanadores() {
    ArrayList<Integer> numerosCantados = super.getNumerosCantados();
    Bingo bingo = super.bingo;
    for(Carton carton : bingo.getCartonesYaAsignados()) {
      int[][] matriz = carton.getMatriz();
      ArrayList<Integer> diagonales = obtenerDiagonales(matriz);
      boolean cartonGanador = true;
        for (Integer numero : diagonales) {
            if (!numerosCantados.contains(numero)) {
                cartonGanador = false;
                break; // No es necesario continuar verificando
            }
        }
        if (cartonGanador) {
            cartonesGanadores.add(carton);
        }
    }
    return true;
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
  
  public static void main(String[] args) {
        int[][] matriz = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
        };

        ArrayList<Integer> diagonales = obtenerDiagonales(matriz);

        System.out.println("Diagonales: " + diagonales);
    }
  
}
