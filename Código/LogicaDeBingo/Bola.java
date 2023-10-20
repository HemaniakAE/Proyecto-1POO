package LogicaDeBingo;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Bola {
  private List<Integer> numerosPorLlamar;
  private Random random = new Random();
    
  public Bola() {
    resetearNumeros();
  }
    
    private void resetearNumeros() {
    numerosPorLlamar = new ArrayList<>();
    for (int i = 1; i <= 75; i++) {
      numerosPorLlamar.add(i);
    }
  } 
  
  // Genera un número aleatorio sin repetición
  public int generarNumeroAleatorio() {
    if (numerosPorLlamar.isEmpty()) {
      // Si ya no hay números, podrías devolver un valor especial o lanzar una excepción
      throw new IllegalStateException("Todos los números ya han sido generados");
    }

    int numeroSeleccionado;
    do {
      numeroSeleccionado = random.nextInt(75) + 1;
    } while (!numerosPorLlamar.contains(numeroSeleccionado));

    // Elimina el número de la lista para garantizar que no se repita
    numerosPorLlamar.remove(Integer.valueOf(numeroSeleccionado));
    // Retorna el número seleccionado
    return numeroSeleccionado;
  }
  
  public List<Integer> getNumerosPorLlamar() {
    return numerosPorLlamar;
  }
}