/**
 * Este código representa la lógica para evaluar si un jugador ha ganado en el juego de Triqui al formar
 * un triplete de su símbolo en filas, columnas o diagonales en un tablero predefinido.
 *
 *
 * El juego tiene las siguientes características:
 *
 * El tablero del juego es representado por una matriz (array bidimensional) de caracteres.
 * Cada celda de la matriz contiene un carácter que puede ser 'X', 'O' o espacio en blanco ' '.
 *
 * Los parámetros del juego se definen al comienzo del método main, como el tamaño del tablero (TAMANO),
 * los símbolos para los jugadores A y B (SIMBOLO_JUGADOR_A y SIMBOLO_JUGADOR_B respectivamente),
 * la información sobre si es el turno del jugador A (esTurnoJugadorA), una posición en el tablero (coordenadas x e y),
 * el símbolo actual a evaluar (simbolo) y una variable para indicar si ha ocurrido un triqui (esTriqui).
 *
 * El código realiza una verificación en la variable esTurnoJugadorA y asigna el símbolo correspondiente
 * al jugador actual (A o B).
 *
 * La matriz del tablero se inicializa con algunos valores de ejemplo.
 *
 * El código utiliza un contador para evaluar si hay un triqui en una fila, columna o diagonal.
 * Comienza evaluando las filas horizontales, luego las columnas verticales, y finalmente las dos diagonales.
 *
 * Dentro de bucles "for", el código verifica si el carácter en la posición actual de la matriz es igual
 * al símbolo del jugador actual. Si es así, incrementa el contador.
 *
 * Después de cada evaluación de fila, columna o diagonal, el código verifica si el contador es igual a 3,
 * lo que significa que hay un triqui formado por el jugador actual.
 *
 * Finalmente, el código imprime el tablero del juego y muestra un mensaje indicando si se ha formado un triqui o no.
 */

public class Evaluador {
    public static void main(String[] args) {
        // Parámetros del juego:
        int TAMANO = 3;
        char SIMBOLO_JUGADOR_A = 'X';
        char SIMBOLO_JUGADOR_B = 'O';
        boolean esTurnoJugadorA = true;
        int x = 2;
        int y = 2;
        char simbolo = SIMBOLO_JUGADOR_A;
        boolean esTriqui = false;

        if(!esTurnoJugadorA)
            simbolo = SIMBOLO_JUGADOR_B;

        // Tablero:
        char[][] matriz = {
                {'X', 'O', 'O'},
                {' ', 'X', ' '},
                {' ', ' ', 'X'}
        };

        int contador = 0;

        // Verifico la línea horizontal:
        for(int i = 0; i < matriz.length; i++){
            if(matriz[y][i] == simbolo){
                contador += 1;
            }
        }
        esTriqui = (contador == 3);
        // Verifico la línea vertical:
        if(!esTriqui){
            contador = 0;
            for(int i = 0; i < matriz.length; i++){
                if(matriz[i][x] == simbolo){
                    contador += 1;
                }
            }
            esTriqui = (contador == 3);
            // Verifico la diagonal principal:
            if(x == y && !esTriqui){
                contador = 0;
                for(int i = 0; i < matriz.length; i++){
                    if(matriz[i][i] == simbolo){
                        contador += 1;
                    }
                }
                esTriqui = (contador == 3);
                // Verifico la diagonal invertida:
                if(x + y == TAMANO - 1 && !esTriqui){
                    contador = 0;
                    for(int i = 0; i < matriz.length; i++){
                        if(matriz[i][TAMANO-1-i] == simbolo){
                            contador += 1;
                        }
                    }
                    esTriqui = (contador == 3);
                }
            }
        }

        // Imprimir matriz:
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz[i].length; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        if(esTriqui)
            System.out.println("Es Triqui");
        else
            System.out.println("NO es Triqui");
    }
}
