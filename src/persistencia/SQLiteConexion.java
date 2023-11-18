package persistencia;

import persistencia.DAO.EstadoJuego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase para crear la conexión a la base de datos SQLite.
 */
public class SQLiteConexion {

    private static final String URL = "jdbc:sqlite:/Users/danielsanchez/IdeaProjects/java-triqui/triqui.db";
    private static final String USUARIO = "";
    private static final String CLAVE = "";

    private static Connection conexion;

    /**
     * Obtiene una conexión a la base de datos utilizando el patrón Singleton.
     *
     * @return Una instancia de la conexión a la base de datos.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public static Connection getConexion() {
        try {
            if(conexion == null)
                conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }
}
