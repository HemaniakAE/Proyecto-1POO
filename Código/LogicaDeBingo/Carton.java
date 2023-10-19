package LogicaDeBingo;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * Clase Carton.
 * Abstracción para codificación de los cartones
 * de un bingo.
 * 
 * @author (Quiriat Mata) 
 * @version (15/10/2023)
 */

public class Carton {

  public String codigoCarton;
  private int[][] matriz;
  private static int contadorCartones = 0;
  protected static ArrayList<Carton> listaCartonesGenerados = new ArrayList<>();


  public Carton() {
    this.codigoCarton = generarCodigoCarton();
    this.matriz = new int[5][5];
    llenarMatriz();
    generarImagen();
    listaCartonesGenerados.add(this);
  }

  private String generarCodigoCarton() {
    String codigo = "HQ" + String.format("%03d", contadorCartones);
    contadorCartones++;
    return codigo;
  }

  private void llenarMatriz() {
    Random random = new Random();
    for (int columna = 0; columna < 5; columna++) {
      ArrayList<Integer> numerosGenerados = new ArrayList<>(); // Lista para números generados en esta columna
      for (int fila = 0; fila < 5; fila++) {
        int inicio = columna * 15 + 1;
        int fin = inicio + 14;
        int numeroAleatorio; // Definición movida aquí
        do {
          numeroAleatorio = inicio + random.nextInt(fin - inicio + 1);
        } while (numerosGenerados.contains(numeroAleatorio)); // Repite hasta obtener un número no generado previamente
            
        numerosGenerados.add(numeroAleatorio); // Agrega el número generado a la lista
        matriz[fila][columna] = numeroAleatorio; // Asigna el número a la matriz
      }
    }
  }


  public static ArrayList<Carton> generarCartones(int pCantidadCartones) {
    ArrayList<Carton> listaCartones = new ArrayList<>();

    for (int i = 0; i < pCantidadCartones && i < 500; i++) {
        Carton carton = new Carton();
        listaCartones.add(carton);
    }
    return listaCartones;
  }

  public void imprimirMatriz() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        System.out.print(matriz[i][j] + "\t");
      }
      System.out.println();
    }
  }
  
  private void generarImagen() {
    int width = 300; // Define el ancho de la imagen
    int height = 300; // Define el alto de la imagen
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = image.createGraphics();

    // Establece el color de fondo y dibuja un rectángulo lleno
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);

    // Establece el color del texto y el tipo de letra
    g.setColor(Color.BLACK);
    g.setFont(new Font("Arial", Font.BOLD, 20));

    // Dibuja "BINGO" en la parte superior
    char[] bingo = "BINGO".toCharArray();
    int cellWidth = width / 5;
    for (int i = 0; i < bingo.length; i++) {
      g.drawString(Character.toString(bingo[i]), i * cellWidth + cellWidth / 2 - 10, 20);
    }

    // Dibuja los números de la matriz en la imagen
    int cellHeight = height / 7; // Ajustado para dejar espacio para "BINGO" y el código
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        String numero = String.valueOf(matriz[i][j]);
        g.drawString(numero, j * cellWidth + cellWidth / 3, i * cellHeight + cellHeight / 2 + 30); // +30 para dejar espacio para "BINGO"
      }
    }

    // Dibuja el código del cartón en la parte inferior
    g.drawString(codigoCarton, width / 2 - 40, height - 10);

    // Guarda la imagen
    try {
      File directory = new File("cartones");
      if (!directory.exists()) {
        directory.mkdir(); // Crea el directorio si no existe
      }
      ImageIO.write(image, "png", new File("cartones/" + codigoCarton + ".png"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    g.dispose();
  }

    public static int getContadorCartones() {
          return contadorCartones;
    }

  public static void main(String[] args) {
    ArrayList<Carton> lista = generarCartones(5);  //ejemplo 5 cartones
    for (Carton carton : lista) {
      System.out.println("Código del cartón: " + carton.codigoCarton);
      carton.imprimirMatriz();
      System.out.println("-----------------------------------");
    }
  }

}