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
 * y una bandera para indicar si es el turno del jugador A (turnoJugadorA).
 *
 * Se inicializa la matriz del tablero con un tamaño de tamano x tamano,
 * donde cada celda se inicializa con el valor de espacio en blanco (' ').
 *
 * Se imprime la matriz vacía en la consola, mostrando el estado inicial del tablero.
 *
 * A continuación, se establece un ciclo "while(true)" que representa el flujo del juego,
 * ya que el juego continúa hasta que el ciclo sea interrumpido.
 *
 * Dentro del ciclo, se determina el símbolo del jugador actual en función de la variable "turnoJugadorA".
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
 * para cambiar el valor de "turnoJugadorA".
 */

import persistencia.DAO.EstadoJuego;
import persistencia.DAO.EstadoJuegoDAO;

import javax.swing.*;
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
    private boolean turnoJugadorA;
    private char[][] matriz;
    private int noMovimientos;
    private boolean triqui;
    private boolean finJuego;

    private Evaluador evaluador;

    /**************************************************************************
     * Métodos
     **************************************************************************/

    /**************************** CONSTRUCTOR ****************************/
    /**
     * Constructor de la clase logicaNegocio.Tablero
     * @param tamano: Tamaño de las filas y columnas (es un tablero cuadrado).
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public Tablero(int tamano){
        this.tamano = tamano;
        this.SIMBOLO_JUGADOR_A = 'X';  // Símbolo del jugador A (fijo como 'X').
        this.SIMBOLO_JUGADOR_B = 'O';  // Símbolo del jugador B (fijo como 'O').
        this.turnoJugadorA = true;
        this.matriz = new char[tamano][tamano];
        this.evaluador = new Evaluador(this.tamano);
    }

    /**
     * Constructor de la clase Tablero que inicializa un objeto Tablero con los parámetros proporcionados.
     *
     * @param tamano Tamaño del tablero.
     * @param simboloActual Símbolo del jugador actual (X o O).
     * @param turnoJugadorA Indica si es el turno del jugador A (true) o del jugador B (false).
     * @param matriz Matriz que representa el estado actual del tablero.
     * @param noMovimientos Número de movimientos realizados en el juego.
     * @param triqui Indica si se ha formado un triqui en el tablero.
     * @param finJuego Indica si el juego ha llegado a su fin.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public Tablero(int tamano, char simboloActual, boolean turnoJugadorA, char[][] matriz, int noMovimientos, boolean triqui, boolean finJuego) {
        // Inicializa los atributos del objeto Tablero con los valores proporcionados.
        this.tamano = tamano;
        this.SIMBOLO_JUGADOR_A = 'X';  // Símbolo del jugador A (fijo como 'X').
        this.SIMBOLO_JUGADOR_B = 'O';  // Símbolo del jugador B (fijo como 'O').
        this.simboloActual = simboloActual;
        this.turnoJugadorA = turnoJugadorA;
        this.matriz = matriz;
        this.noMovimientos = noMovimientos;
        this.triqui = triqui;
        this.finJuego = finJuego;
        this.evaluador = new Evaluador(this.tamano);
    }


    /**************************** GETTER ****************************/
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
    public boolean isTurnoJugadorA() {
        return turnoJugadorA;
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
     * Método get para obtener el número de movimientos realizados por ambos jugadores.
     * @return El número de movimientos realizados.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public int getNoMovimientos() {
        return noMovimientos;
    }

    /**
     * Método get para obtener un booleano que indica si se realizó triqui.
     * @return Booleano que indica si se realizó triqui.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public boolean isTriqui() {
        return triqui;
    }

    /**
     * Método get para obtener el booleano que indica si el juego se finalizó.
     * @return Booleano que indica si el juego finalizó.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public boolean isFinJuego() {
        return finJuego;
    }

    /**************************** MÉTODOS DE PROPOÓSITO GENERAL ****************************/
    /**
     * Método que verifica si una coordenada se enncuentra dentro de los límites del tablero.
     * @param coordenada (número entero x o y)
     * @return Booleano que indica si la coordenada es válida.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    private boolean verificarCoordenada(int coordenada) {
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
        if (!turnoJugadorA)
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
        boolean movimientoValido = false;
        if(this.noMovimientos < this.tamano * this.tamano && !this.triqui) {
            movimientoValido = this.realizarMovimiento(x, y);
            this.triqui = evaluador.evaluar(this.matriz, this.simboloActual, x, y);

            this.noMovimientos += 1;
            this.finJuego = (this.noMovimientos == this.tamano * this.tamano);
        }

        // Se almacena el estado del juego en la base de datos:
        EstadoJuego estadoJuego = new EstadoJuego(tamano, simboloActual, turnoJugadorA, matriz, noMovimientos, triqui, finJuego);
        EstadoJuegoDAO estadoJuegoDAO = new EstadoJuegoDAO();
        estadoJuegoDAO.guardar(estadoJuego);

        return movimientoValido;
    }

    /**
     * Realiza un movimiento en el juego del triqui en la posición especificada.
     *
     * @param x Coordenada en el eje horizontal.
     * @param y Coordenada en el eje vertical.
     * @return true si el movimiento fue exitoso, false si la coordenada no es válida o la casilla no está vacía.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    private boolean realizarMovimiento(int x, int y) {
        // Verifica que las coordenadas proporcionadas sean válidas.
        if (verificarCoordenada(x) && verificarCoordenada(y) && verificarCasillaVacia(x, y)) {
            // Cambia el símbolo actual (X o O).
            cambiarSimboloActual();

            // Asigna el símbolo actual a la posición especificada en la matriz.
            matriz[y][x] = this.simboloActual;

            // Cambia el turno al siguiente jugador.
            turnoJugadorA = !turnoJugadorA;

            // Indica que el movimiento fue exitoso.
            return true;
        }
        // Indica que el movimiento no fue exitoso.
        return false;
    }

}
