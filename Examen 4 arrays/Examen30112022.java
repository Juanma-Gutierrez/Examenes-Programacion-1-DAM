
/**
 * Examen30112022.java
 *
 * @version: 30/11/2022
 * @author: Juan Manuel Gutiérrez
 *          https://github.com/Juanma-Gutierrez
 */

import java.util.Scanner;

public class Examen30112022 {
    public static void main(String[] args) {
        // Var declarations
        Character[][] arr;
        int WIDTH = 11;
        int HEIGHT = 11;
        int posX = WIDTH / 2;
        int posY = HEIGHT / 2;
        String opt;
        String mode;

        // Var init
        arr = new Character[WIDTH][HEIGHT];
        arr = limpiarTablero(arr);
        mode = "mover";

        // Scanner class
        Scanner sc = new Scanner(System.in);

        // Process
        do {
            imprime(arr, posX, posY, mode); // imprimimos la matriz y el menú
            opt = sc.next();
            switch (opt) {
                case "a": // mueve izquierda
                    arr[posY][posX] = compruebaPintar(posX, posY, mode, arr[posY][posX]);
                    if (posX > 1)
                        posX--;
                    break;
                case "d": // mueve derecha
                    arr[posY][posX] = compruebaPintar(posX, posY, mode, arr[posY][posX]);
                    if (posX < WIDTH - 2)
                        posX++;
                    break;
                case "w": // mueve abajo
                    arr[posY][posX] = compruebaPintar(posX, posY, mode, arr[posY][posX]);
                    if (posY > 1)
                        posY--;
                    break;
                case "s": // mueve arriba
                    arr[posY][posX] = compruebaPintar(posX, posY, mode, arr[posY][posX]);
                    if (posY < HEIGHT - 2)
                        posY++;
                    break;
                case "p": // Modo pintar
                    mode = "pintar";
                    break;
                case "b": // Modo borrar
                    mode = "borrar";
                    break;
                case "m": // Modo mover
                    mode = "mover";
                    break;
                case "r": // Rotar antihorario
                    arr = rotarArray(arr);
                    break;
                case "e": // Espejo horizontal
                    arr = espejo(arr);
                    break;
                case "l": // Limpiar tablero
                    arr = limpiarTablero(arr);
                    break;
            }
        } while (!opt.equals("g"));

        // Close Scanner
        sc.close();
    }

    /**
     * limpiaTablero: Limpia todo el contenido del array y lo rellena con el borde
     * de asteriscos
     *
     * @param arr Array de entrada
     * @return Array de salida
     */
    public static Character[][] limpiarTablero(Character[][] arr) {
        int WIDTH = arr.length;
        int HEIGHT = arr[0].length;
        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++)
                if (i == 0 || i == WIDTH - 1 || j == 0 || j == HEIGHT - 1)
                    arr[i][j] = '*';
                else
                    arr[i][j] = ' ';
        return arr;
    }

    /**
     * rotarArray: Rota el array en sentido antihorario
     *
     * @param arr Array de entrada
     * @return Array rotado hacia la izquierda
     */
    public static Character[][] rotarArray(Character[][] arr) {
        Character[][] aux;
        aux = new Character[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[0].length; j++)
                aux[i][j] = arr[j][arr[0].length - i - 1];
        return aux;
    }

    /**
     * compruebaPintar: Comprueba si tiene que pintar un espacio, una X o el
     * carácter inicial
     *
     * @param posX posición X del cursor
     * @param posY posición Y del cursor
     * @param mode modo del cursor (pintar, borrar, mover)
     * @param c    carácter
     * @return Carácter que se debe poner
     */
    public static Character compruebaPintar(int posX, int posY, String mode, Character c) {
        if (mode.equals("pintar"))
            return 'x';
        if (mode.equals("borrar"))
            return ' ';
        return c;
    }

    /**
     * espejo: Hace un volteado horizontal de la matriz
     *
     * @param arr Array de entrada
     * @return Array rotado
     */
    public static Character[][] espejo(Character[][] arr) {
        Character[][] aux;
        aux = new Character[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[0].length; j++)
                aux[i][j] = arr[i][arr.length - 1 - j];
        return aux;
    }

    /**
     * imprime: imprime el tablero array con el menú incluido
     *
     * @param arr  Array de entrada
     * @param posX Posición X del cursor
     * @param posY Posición Y del cursor
     * @param mode Modo del cursor
     */
    public static void imprime(Character[][] arr, int posX, int posY, String mode) {
        int WIDTH = arr.length;
        int HEIGHT = arr[0].length;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (i != posY || j != posX)
                    System.out.print(arr[i][j] + " ");
                else
                    switch (mode) {
                        case "mover":
                            System.out.print("M" + " ");
                            break;
                        case "pintar":
                            System.out.print("P" + " ");
                            break;
                        case "borrar":
                            System.out.print("B" + " ");
                            break;
                    }
            }
            System.out.println();
        }
        System.out.println("a. Izquierda");
        System.out.println("d. Derecha");
        System.out.println("s. Abajo");
        System.out.println("w. Arriba");
        System.out.println("p. Pintar");
        System.out.println("b. Borrar");
        System.out.println("m. Mover");
        System.out.println("r. Rotar sentido antihorario");
        System.out.println("e. Espejo horizontal");
        System.out.println("l. Limpiar tablero");
        System.out.println("g. Salir");
    }
}