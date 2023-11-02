package logicaNegocio;

/**
 * Este código implementa un juego básico de Triqui en la consola,
 * donde los jugadores pueden ingresar sus movimientos alternativamente
 * y ver el estado del tablero después de cada movimiento.
 * El juego continúa en un bucle infinito hasta que se detenga manualmente.
 *
 *
 * Se crea un objeto Scanner llamado "lector" para permitir la entrada de datos desde la consola.
 *
 * Se definen los parámetros del juego, como el tamaño del tablero (tamano), los símbolos para
 * los jugadores A y B (SIMBOLO_JUGADOR_A y SIMBOLO_JUGADOR_B respectivamente),
 * y una bandera para indicar si es el turno del jugador A (esTurnoJugadorA).
 *
 * Se inicializa la matriz del tablero con un tamaño de tamano x tamano,
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


/**
 * Clase logicaNegocio.Tablero de Triqui
 */
public class Tablero {
    /**************************************************************************
     * Atributos
     **************************************************************************/
    private int tamano;
    private char simboloActual;
    private char SIMBOLO_JUGADOR_A;
    private char SIMBOLO_JUGADOR_B;
    private boolean esTurnoJugadorA;
    private char[][] matriz;

    /**************************************************************************
     * Métodos
     **************************************************************************/

    /**
     * Constructor de la clase logicaNegocio.Tablero
     * @param tamano: Tamaño de las filas y columnas (es un tablero cuadrado).
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public Tablero(int tamano){
        this.tamano = tamano;
        this.SIMBOLO_JUGADOR_A = 'X';
        this.SIMBOLO_JUGADOR_B = 'O';
        this.esTurnoJugadorA = true;
        this.matriz = new char[tamano][tamano];
    }

    /**
     * Método get para obtener la matriz del tablero.
     * @return La matriz del tablero.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public char[][] getMatrix(){
        return matriz;
    }

    /**
     * Método get para obtener si es el turno del jugador A.
     * @return Booleano que indica el turno del jugador A.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public boolean isEsTurnoJugadorA() {
        return esTurnoJugadorA;
    }

    /**
     * Método get para obtener el símbolo actual del jugador.
     * @return El símbolo del jugador actual.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public char getSimboloActual() {
        return simboloActual;
    }

    /**
     * Método que verifica si una coordenada se enncuentra dentro de los límites del tablero.
     * @param coordenada (número entero x o y)
     * @return Booleano que indica si la coordenada es válida.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    private boolean verficarCoordenada(int coordenada) {
        return coordenada >= 0 && coordenada < this.tamano;
    }

    /**
     * Método que verifica si una casilla está vacía.
     * @param x Coordenada x de la casilla.
     * @param y Coordenada y de la casilla.
     * @return Booleano que indica si la casilla está vacía.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    private boolean verificarCasillaVacia(int x, int y) {
        return !(matriz[y][x] == SIMBOLO_JUGADOR_A || matriz[y][x] == SIMBOLO_JUGADOR_B);
    }

    /**
     * Método que cambia el símbolo actual del jugador.
     * Si es el turno del jugador A, el símbolo actual es X, de lo contrario es O.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    private void cambiarSimboloActual() {
        this.simboloActual = SIMBOLO_JUGADOR_A;
        if (!esTurnoJugadorA)
            this.simboloActual = SIMBOLO_JUGADOR_B;
    }

    /**
     * Método que permite a un jugador jugar un movimiento en una casilla.
     * @param x Coordenada x de la casilla.
     * @param y Coordenada y de la casilla.
     * @return Booleano que indica si el movimiento fue exitoso.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public boolean jugar(int x, int y) {
        if(verficarCoordenada(x) && verficarCoordenada(y) && verificarCasillaVacia(x, y)) {
            cambiarSimboloActual();

            matriz[y][x] = this.simboloActual;

            esTurnoJugadorA = !esTurnoJugadorA;
            return true;
        }
        return false;
    }
}
