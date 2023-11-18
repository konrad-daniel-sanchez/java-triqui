package persistencia.DAO;

/**
 * Clase Juego para almacenar en la BD
 */
public class EstadoJuego {
    private int tamano;
    private char simboloActual;
    private boolean turnoJugadorA;
    private char[][] matriz;
    private int noMovimientos;
    private boolean triqui;
    private boolean finJuego;

    /**
     * Constructor de la clase EstadoJuego
     *
     * @param tamano Tamaño de cada lado del tablero del juego
     * @param simboloActual Símbolo del jugador actual
     * @param turnoJugadorA Booleano que indica si es el turno del jugador A
     * @param matriz Matriz con el estado actual del juego
     * @param noMovimientos Número de movimientos realizados
     * @param triqui Booleano que indica si es Triqui
     * @param finJuego Booleano que indica si el juego se finalizó
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public EstadoJuego(int tamano, char simboloActual, boolean turnoJugadorA, char[][] matriz, int noMovimientos, boolean triqui, boolean finJuego) {
        this.tamano = tamano;
        this.simboloActual = simboloActual;
        this.turnoJugadorA = turnoJugadorA;
        this.matriz = matriz;
        this.noMovimientos = noMovimientos;
        this.triqui = triqui;
        this.finJuego = finJuego;
    }

    /**
     * Método que obtiene el tamaño del juego
     *
     * @return tamaño del juego
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Método que obtiene el símbolo del jugador actual
     *
     * @return símbolo del jugador actual
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public char getSimboloActual() {
        return simboloActual;
    }

    /**
     * Método que indica si es el turno del jugador A
     *
     * @return true si es el turno del jugador A, false si es el turno del jugador B
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public boolean isTurnoJugadorA() {
        return turnoJugadorA;
    }

    /**
     * Método que obtiene la matriz del juego
     *
     * @return la matriz del juego
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public char[][] getMatriz() {
        return matriz;
    }

    /**
     * Método que obtiene el número de movimientos realizados en el juego
     *
     * @return el número de movimientos realizados
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public int getNoMovimientos() {
        return noMovimientos;
    }

    /**
     * Método que indica si se ha formado un triqui en el juego
     *
     * @return true si hay triqui, false en caso contrario
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public boolean isTriqui() {
        return triqui;
    }

    /**
     * Método que indica si el juego ha llegado a su fin
     *
     * @return true si el juego ha terminado, false en caso contrario
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public boolean isFinJuego() {
        return finJuego;
    }
}
