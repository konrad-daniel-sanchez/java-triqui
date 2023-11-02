import java.util.Scanner;

/**
 * Clase Principal del juego de Triqui
 */
public class Principal {
    /**************************************************************************
     * Métodos
     **************************************************************************/

    /**
     * Método que imprime la matriz en la consola.
     * @param matriz Matriz a imprimir.
     *
     * Complejidad Temporal: O(n^2) Complejidad Cuadrática.
     */
    public static void imprimirMatriz(char[][] matriz){
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz[i].length; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Método principal del programa.
     * @param args: Argumentos por consola.
     *
     * Complejidad Temporal: O(n^2) Complejidad Cuadrática
     *     (La complejidad más alta de todos los métodos que llama, en este caso, el método de imprimirMatriz).
     */
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        int TAMANO = 3;

        Tablero tablero = new Tablero(TAMANO);
        Evaluador evaluador = new Evaluador(TAMANO);

        imprimirMatriz(tablero.getMatrix());

        int i = 0;
        boolean esTriqui = false;
        // Reglas del juego:
        while(i < TAMANO*TAMANO && !esTriqui){
            boolean esPosicionValida = false;
            int x = 0;
            int y = 0;
            while (!esPosicionValida){
                System.out.println("Ingresa tu posición (x)");
                x = lector.nextInt();
                System.out.println("Ingresa tu posición (y)");
                y = lector.nextInt();
                esPosicionValida = tablero.jugar(x, y);
                if (!esPosicionValida)
                    System.out.println("NO es una posición válida, Ingresa tu posición de nuevo");
            }

            imprimirMatriz(tablero.getMatrix());
            esTriqui = evaluador.evaluar(tablero.getMatrix(), tablero.getSimboloActual(), x, y);

            if(esTriqui)
                System.out.println("Es Triqui");
            else
                System.out.println("NO es Triqui");
            i += 1;
        }
    }
}
