/**
 * Este código implementa un juego básico de Triqui en la consola,
 * donde los jugadores pueden ingresar sus movimientos alternativamente
 * y ver el estado del tablero después de cada movimiento.
 * El juego continúa en un bucle infinito hasta que se detenga manualmente.
 *
 *
 * Se crea un objeto Scanner llamado "lector" para permitir la entrada de datos desde la consola.
 *
 * Se definen los parámetros del juego, como el tamaño del tablero (TAMANO), los símbolos para
 * los jugadores A y B (SIMBOLO_JUGADOR_A y SIMBOLO_JUGADOR_B respectivamente),
 * y una bandera para indicar si es el turno del jugador A (esTurnoJugadorA).
 *
 * Se inicializa la matriz del tablero con un tamaño de TAMANO x TAMANO,
 * donde cada celda se inicializa con el valor de espacio en blanco (' ').
 *
 * Se imprime la matriz vacía en la consola, mostrando el estado inicial del tablero.
 *
 * A continuación, se establece un ciclo "while(true)" que representa el flujo del juego,
 * ya que el juego continúa hasta que el ciclo sea interrumpido.
 *
 * Dentro del ciclo, se determina el símbolo del jugador actual en función de la variable "esTurnoJugadorA".
 * Si no es el turno del jugador A, se establece el símbolo del jugador B.
 *
 * El juego solicita al jugador actual que ingrese su posición deseada en las coordenadas (x, y).
 * Las coordenadas se ingresan como números enteros utilizando el objeto "lector" de tipo Scanner.
 *
 * Después de recibir las coordenadas del jugador, el símbolo correspondiente se coloca
 * en la posición especificada en la matriz del tablero.
 *
 * Luego, se imprime la matriz actualizada en la consola, mostrando el estado actual
 * del tablero con el símbolo recién colocado.
 *
 * Finalmente, se alterna el turno del jugador actual utilizando el operador de negación ("!")
 * para cambiar el valor de "esTurnoJugadorA".
 */

import java.util.Scanner;


public class Tablero {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        // Parámetros del juego:
        int TAMANO = 3;
        char SIMBOLO_JUGADOR_A = 'X';
        char SIMBOLO_JUGADOR_B = 'O';
        boolean esTurnoJugadorA = true;

        // Tablero:
        char[][] matriz = new char[TAMANO][TAMANO];

        // Imprimir matriz:
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz[i].length; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        // Reglas del juego:
        while(true){
            char simbolo = SIMBOLO_JUGADOR_A;
            if(!esTurnoJugadorA)
                simbolo = SIMBOLO_JUGADOR_B;
            System.out.println("Ingresa tu posición (x)");
            int x = lector.nextInt();
            System.out.println("Ingresa tu posición (y)");
            int y = lector.nextInt();

            matriz[y][x] = simbolo;

            // Imprimir matriz:
            for(int i=0; i<matriz.length; i++){
                for(int j=0; j<matriz[i].length; j++){
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.println();
            }
            esTurnoJugadorA = !esTurnoJugadorA;
        }
    }
}
