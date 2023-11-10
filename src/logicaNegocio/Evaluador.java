package logicaNegocio;

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
 * Los parámetros del juego se definen al comienzo del método main, como el tamaño del tablero (tamano),
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

/**
 * Clase logicaNegocio.Evaluador de Triqui
 * Dada una posición, evalúa si alguno de los jugadores completó un triqui.
 */
public class Evaluador {
    /**************************************************************************
     * Atributos
     **************************************************************************/
    private int tamano;

    /**************************************************************************
     * Métodos
     **************************************************************************/

    /**
     * Constructor de la clase logicaNegocio.Evaluador
     * @param tamano Tamaño de las filas y columnas (es un tablero cuadrado).
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public Evaluador(int tamano) {
        this.tamano = tamano;
    }

    /**
     * Método que verifica si hay un triqui en la línea horizontal.
     * @param matriz Matriz del tablero.
     * @param simbolo Símbolo del jugador actual.
     * @param y Posición en la fila.
     * @return true si hay un triqui, false de lo contrario.
     *
     * Complejidad Temporal: O(N) Complejidad Lineal.
     */
    private boolean verificarHorizontal(char[][] matriz, char simbolo, int y) {
        // Se verifica la línea horizontal:
        int contador = 0;
        for(int i = 0; i < matriz.length; i++){
            if(matriz[y][i] == simbolo){
                contador += 1;
            }
        }
        return (contador == this.tamano);
    }

    /**
     * Método que verifica si hay un triqui en la línea vertical.
     * @param matriz Matriz del tablero.
     * @param simbolo Símbolo del jugador actual.
     * @param x Posición en la columna.
     * @return true si hay un triqui, false de lo contrario.
     *
     * Complejidad Temporal: O(N) Complejidad Lineal.
     */
    private boolean verificarVertical(char[][] matriz, char simbolo, int x){
        // Se verifica la línea vertical:
        int contador = 0;
        for(int i = 0; i < matriz.length; i++){
            if(matriz[i][x] == simbolo){
                contador += 1;
            }
        }
        return (contador == this.tamano);
    }

    /**
     * Método que verifica si hay un triqui en la diagonal principal.
     * @param matriz Matriz del tablero.
     * @param simbolo Símbolo del jugador actual.
     * @param x Posición en la columna.
     * @param y Posición en la fila.
     * @return true si hay un triqui, false de lo contrario.
     *
     * Complejidad Temporal: O(N) Complejidad Lineal.
     */
    private boolean verificarDiagonalPrincipal(char[][] matriz, char simbolo, int x, int y){
        // Se verifica la diagonal principal:
        if(x == y) {
            int contador = 0;
            for (int i = 0; i < matriz.length; i++) {
                if (matriz[i][i] == simbolo) {
                    contador += 1;
                }
            }
            return (contador == this.tamano);
        }
        return false;
    }

    /**
     * Método que verifica si hay un triqui en la diagonal invertida.
     * @param matriz Matriz del tablero.
     * @param simbolo Símbolo del jugador actual.
     * @param x Posición en la columna.
     * @param y Posición en la fila.
     * @return true si hay un triqui, false de lo contrario.
     *
     * Complejidad Temporal: O(N) Complejidad Lineal.
     */
    private boolean verificarDiagonalInvertida(char[][] matriz, char simbolo, int x, int y){
        // Se verifica la diagonal invertida:
        if(x + y == tamano - 1){
            int contador = 0;
            for(int i = 0; i < matriz.length; i++){
                if(matriz[i][tamano-1-i] == simbolo){
                    contador += 1;
                }
            }
            return (contador == this.tamano);
        }
        return false;
    }

    /**
     * Método que evalúa si un jugador ha formado un triqui.
     * @param matriz Matriz del tablero.
     * @param simbolo Símbolo del jugador actual.
     * @param x Posición en la columna.
     * @param y Posición en la fila.
     * @return true si hay un triqui, false de lo contrario.
     *
     * Complejidad Temporal: O(N) Complejidad Lineal. (La complejidad más alta de los métodos que se llaman).
     */
    public boolean evaluar(char[][] matriz, char simbolo, int x, int y){
        boolean esTriqui = verificarHorizontal(matriz, simbolo, y);
        if(!esTriqui) {
            esTriqui = verificarVertical(matriz, simbolo, x);
            if(!esTriqui) {
                esTriqui = verificarDiagonalPrincipal(matriz, simbolo, x, y);
                if(!esTriqui) {
                    esTriqui = verificarDiagonalInvertida(matriz, simbolo, x, y);
                }
            }
        }
        return esTriqui;
    }
}
