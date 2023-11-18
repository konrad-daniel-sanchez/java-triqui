package logicaNegocio;

import persistencia.DAO.EstadoJuego;
import persistencia.DAO.EstadoJuegoDAO;

/**
 * Clase que se conecta a las clase de base de datos
 */
public class JuegoAlmacenado {
    /**
     * Verifica si existe un juego almacenado en la base de datos.
     *
     * @return true si hay un juego almacenado, false si no hay juego almacenado o si ocurre un error durante la verificación.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public boolean existeJuegoAlmacenado() {
        // Se instancia un objeto EstadoJuegoDAO para manejar las operaciones de acceso a la base de datos.
        EstadoJuegoDAO estadoJuegoDAO = new EstadoJuegoDAO();

        // Se intenta recuperar el estado del juego almacenado desde la base de datos.
        return estadoJuegoDAO.consultarExisteJuego();
    }

    /**
     * Elimina el juego almacenado en la base de datos.
     *
     * Este método utiliza un objeto EstadoJuegoDAO para realizar la operación de eliminación
     * en la base de datos, eliminando el registro que representa el estado del juego almacenado.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public void eliminarJuegoAlmacenado() {
        // Se instancia un objeto EstadoJuegoDAO para manejar las operaciones de acceso a la base de datos.
        EstadoJuegoDAO estadoJuegoDAO = new EstadoJuegoDAO();

        // Se intenta recuperar el estado del juego almacenado desde la base de datos.
        // Llamando al método eliminar de EstadoJuegoDAO, se eliminará el registro correspondiente.
        estadoJuegoDAO.eliminar();
    }

    /**
     * Intenta abrir un juego almacenado recuperando el estado del juego desde la base de datos.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public Tablero consultarJuegoAlmacenado() {
        // Se instancia un objeto EstadoJuegoDAO para manejar las operaciones de acceso a la base de datos.
        EstadoJuegoDAO estadoJuegoDAO = new EstadoJuegoDAO();

        // Se intenta recuperar el estado del juego almacenado desde la base de datos.
        EstadoJuego estadoJuego = estadoJuegoDAO.consultar();

        // Si no hay juego almacenado, retorna false indicando que no se pudo abrir el juego.
        if (estadoJuego != null) {
            // Si hay juego almacenado, se actualizan los atributos del juego actual con los datos recuperados.
            Tablero tablero = new Tablero(
                estadoJuego.getTamano(),
                estadoJuego.getSimboloActual(),
                estadoJuego.isTurnoJugadorA(),
                estadoJuego.getMatriz(),
                estadoJuego.getNoMovimientos(),
                estadoJuego.isTriqui(),
                estadoJuego.isFinJuego()
            );
            return tablero;
        }
        return null;
    }
}
