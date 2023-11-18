package persistencia.DAO;

import persistencia.SQLiteConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase DAO para ingressar, modificar y eliminar registros de la tabla EstadoJuego en la base de datos.
 */
public class EstadoJuegoDAO {

    /**
     * Método para transformar una matriz de char a String
     *
     * @param matriz Matriz a transformar
     * @return String resultado de la transformación
     *
     * Complejidad Temporal: O(N^2) Complejidad Constante
     */
    private String convertirMatrizAString(char[][] matriz) {
        String cadena = "";
        for (int i=0; i<matriz.length; i++) {
            for (int j=0; j<matriz.length; j++) {
                char posicion = matriz[j][i];
                if(posicion == 'O' || posicion == 'X')
                    cadena += posicion;
                else
                    cadena += '_';
            }
        }
        return cadena;
    }

    /**
     * Método para transformar un String a matriz de char
     *
     * @param cadena String a transformar
     * @param tamano número de filas
     * @return matriz transformada
     *
     * Complejidad Temporal: O(N^2) Complejidad Constante
     */
    private static char[][] convertirStringAMatriz(String cadena, int tamano) {
        char[][] matriz = new char[tamano][tamano];
        int index = 0;

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                char posicion = cadena.charAt(index);
                if(posicion == 'O' || posicion == 'X')
                    matriz[j][i] = posicion;
                index++;
            }
        }

        return matriz;
    }

    /**
     * Método para ejecutar sentencias SQL de tipo INSERT, UPDATE o DELETE
     *
     * @param estadoJuego Objeto con los datos a almacenar en la base de datos
     *
     * Complejidad Temporal: O(1) Complejidad Constante
     */
    public void guardar(EstadoJuego estadoJuego) {
        String sentenciaSQL = "INSERT INTO EstadoJuego(tamano, simboloActual,turnoJugadorA,matriz,noMovimientos,triqui,finJuego) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conexion = SQLiteConexion.getConexion();

            char[][] matriz = estadoJuego.getMatriz();
            String matrizString = convertirMatrizAString(matriz);

            // Se crea un PreparedStatement para evitar problemas de seguridad (SQL injection)
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSQL)) {
                preparedStatement.setInt(1, estadoJuego.getTamano());
                preparedStatement.setInt(2, estadoJuego.getSimboloActual());
                preparedStatement.setBoolean(3, estadoJuego.isTurnoJugadorA());
                preparedStatement.setString(4, matrizString);
                preparedStatement.setInt(5, estadoJuego.getNoMovimientos());
                preparedStatement.setBoolean(6, estadoJuego.isTriqui());
                preparedStatement.setBoolean(7, estadoJuego.isFinJuego());

                // Ejecutar la sentencia SQL para insertar el registro
                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para eliminar un registro de la base de datos.
     *
     * Complejidad Temporal: O(1) Complejidad Constante
     */
    public void eliminar() {
        String sentenciaSQL = "DELETE FROM EstadoJuego";
        try {
            Connection conexion = SQLiteConexion.getConexion();
            // Se crea un PreparedStatement para evitar problemas de seguridad (SQL injection)
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSQL)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Consulta la existencia de un juego en la base de datos.
     *
     * @return true si existe al menos un juego en la base de datos, false si no hay juegos o si ocurre un error durante la consulta.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public boolean consultarExisteJuego() {
        String sentenciaSQL = "SELECT * FROM EstadoJuego";
        ResultSet resultado = null;
        try {
            Connection conexion = SQLiteConexion.getConexion();
            // Se crea un PreparedStatement para evitar problemas de seguridad (SQL injection)
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSQL)) {
                resultado = preparedStatement.executeQuery();
                if(resultado.next())
                    return true;
                else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Método para ejecutar sentencias SQL de tipo SELECT y retornar los resultados
     *
     * @return Recordset con los resultados de la consulta
     *
     * Complejidad Temporal: O(1) Complejidad Constante
     */
    public EstadoJuego consultar() {
        String sentenciaSQL = "SELECT * FROM EstadoJuego ORDER BY id DESC LIMIT 1";
        ResultSet resultado = null;
        try {
            Connection conexion = SQLiteConexion.getConexion();
            // Se crea un PreparedStatement para evitar problemas de seguridad (SQL injection)
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSQL)) {
                resultado = preparedStatement.executeQuery();

                // Procesar el resultado
                int tamano = resultado.getInt("tamano");
                char simboloActual = resultado.getString("simboloActual").charAt(0);
                String matrizString = resultado.getString("matriz");

                char[][] matriz = convertirStringAMatriz(matrizString, tamano);

                if (resultado.next()) {
                    EstadoJuego estadoJuego = new EstadoJuego(
                            tamano,
                            simboloActual,
                            resultado.getBoolean("turnoJugadorA"),
                            matriz,
                            resultado.getInt("noMovimientos"),
                            resultado.getBoolean("triqui"),
                            resultado.getBoolean("finJuego")
                    );
                    return estadoJuego;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
