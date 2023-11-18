package presentacion;

import logicaNegocio.Evaluador;
import logicaNegocio.JuegoAlmacenado;
import logicaNegocio.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para la Ventana Principal de la aplicación.
 */
public class VentanaPrincipal extends JFrame {
    private JPanel pnlPrincipal;
    private JButton btnNuevoJuego;
    private JTextField txtTamanoTablero;
    private JPanel pnlSuperior;
    private JLabel lblTamanoTablero;
    private JPanel pnlJuego;


    private Tablero tablero;

    private int TAMANO;

    private JuegoAlmacenado juegoAlmacenado;

    /**
     * Método que inicializa la ventana con todos sus controles de UI.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public void inicializarVentana() {
        // Cerrar el proceso cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Asignar el panel principal a la ventana
        this.setContentPane(pnlPrincipal);

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Asignar el tamaño de la ventana (JFrame actual) con tamaño de la pantalla
        this.setSize(screenSize);

        this.setVisible(true);
        btnNuevoJuego.addActionListener(new ActionListener() {
            /**
             * Evento clic del botón jugar.
             *
             * @param e El evento a ser procesado.
             *
             * Complejidad Temporal: O(N^2) Complejidad Cuadrática
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    TAMANO = Integer.parseInt(txtTamanoTablero.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El tamaño del tablero debe ser un número entero");
                    return;
                }
                tablero = new Tablero(TAMANO);

                iniciarJuego();
            }
        });
    }

    private void iniciarJuego() {
        // Se deshabilitan los controles txtTamanoTablero y btnNuevoJuego
        // Para que el usuario no pueda crear un juego nuevo hasta que se termine el juego en curso
        txtTamanoTablero.setEnabled(false);
        btnNuevoJuego.setEnabled(false);

        imprimirMatriz(tablero.getMatrix());
    }

    private void mostrarVentanaContinuarJuego() {
        // Se presenta un cuadro de diálogo de confirmación simple con opciones sí y no (JOptionPane.YES_NO_OPTION)
        int resultadoSiNo = JOptionPane.showConfirmDialog(
                null,
                "Tienes un juego almacenado, ¿Deseas continuar con el juego?",
                "Reanudar juego",
                JOptionPane.YES_NO_OPTION
        );

        // Se revisa la elección del usuario
        if (resultadoSiNo == JOptionPane.YES_OPTION) {
            // Se ejecuta la acción asociada con Sí
            tablero = juegoAlmacenado.consultarJuegoAlmacenado();
            iniciarJuego();
        } else if (resultadoSiNo == JOptionPane.NO_OPTION || resultadoSiNo == JOptionPane.CLOSED_OPTION) {
            // Se elimina el registro en la base de datos
            juegoAlmacenado.eliminarJuegoAlmacenado();
        }
    }

    /**
     * Constructor de la clase presentacion.VentanaPrincipal
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    public VentanaPrincipal(){
        // Se aasigna el título de la ventana
        super("Triqui");

        inicializarVentana();

        juegoAlmacenado = new JuegoAlmacenado();
        boolean existeJuego = juegoAlmacenado.existeJuegoAlmacenado();
        if(existeJuego) {
            mostrarVentanaContinuarJuego();
        }
    }



    /**
     * Método para reiniciar el juego.
     *
     * Complejidad Temporal: O(1) Complejidad Constante.
     */
    private void reiniciarJuego() {
        inicializarVentana();

        // Se limpian los componentes existentes en pnlJuego
        pnlJuego.removeAll();

        // Se limpia el objeto tablero para iniciar un nuevo juego
        this.tablero = null;

        // Se habilitan los controles txtTamanoTablero y btnNuevoJuego
        // para que el usuario pueda crear un juego nuevo
        txtTamanoTablero.setEnabled(true);
        btnNuevoJuego.setEnabled(true);
    }

    /**
     * Método para realizar el movimiento de un jugaoor.
     *
     * @param x Número de columna seleccionada
     * @param y Número de fila seleccionada
     *
     * Complejidad Temporal: O(n^2) Complejidad Cuadrática
     *     (La complejidad más alta de todos los métodos que llama, en este caso, el método de imprimirMatriz).
     */
    private void realizarMovimiento(int x, int y) {
        tablero.jugar(x, y);
        imprimirMatriz(tablero.getMatrix());
        boolean esTriqui = tablero.isTriqui();
        boolean finJuego = tablero.isFinJuego();

        if(esTriqui)
            JOptionPane.showMessageDialog(this, "Es Triqui!!!!");

        if(esTriqui || finJuego) {
            juegoAlmacenado.eliminarJuegoAlmacenado();
            // Se presenta un cuadro de diálogo de confirmación simple con opciones sí y no (JOptionPane.YES_NO_OPTION)
            int resultadoSiNo = JOptionPane.showConfirmDialog(
                    null,
                    "El juego terminó, ¿Deseas jugar otra vez?",
                    "Jugar otra vez",
                    JOptionPane.YES_NO_OPTION
            );

            // Se revisa la elección del usuario
            if (resultadoSiNo == JOptionPane.YES_OPTION) {
                // Se ejecuta la acción asociada con Sí
                reiniciarJuego();
            } else if (resultadoSiNo == JOptionPane.NO_OPTION || resultadoSiNo == JOptionPane.CLOSED_OPTION) {
                // Se ejecuta la acción asociada con No o cerrar el cuadro de diálogo

                // Se cierra la ventana
                this.dispose();
            }
        }
    }

    /**
     * Método para imprimir la matriz en la interfaz gráfica por medio de botones.
     * @param matriz Matriz para realizar la impresión del tablero en la interfaz gráfica.
     *
     * Complejidad Temporal: O(N^2) Complejidad Cuadrática.
     */
    private void imprimirMatriz(char[][] matriz) {
        JPanel pnlTablero = new JPanel();
        pnlTablero.setBackground(Color.BLACK);
        pnlTablero.setLayout(new GridLayout(matriz.length, matriz[0].length));

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                int x = i;
                int y = j;
                String texto = "";
                if (matriz[j][i] == 'X' || matriz[j][i] == 'O')
                    texto = "" + matriz[j][i];
                JButton button = new JButton(texto);

                // Establecer estilos y colores
                button.setFont(new Font("Arial", Font.BOLD, 40));
                button.setFocusPainted(false);
                button.setBackground(Color.RED);


                // Añadir animación al hacer hover sobre el botón
                button.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        button.setBackground(Color.MAGENTA);
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        button.setBackground(Color.LIGHT_GRAY);
                    }
                });

                button.addActionListener(new ActionListener() {
                    /**
                     * Evento clic de cada botón del juego.
                     *
                     * @param e El evento a ser procesado.
                     *
                     * Complejidad Temporal: O(N^2) Complejidad Cuadrática
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        realizarMovimiento(x, y);
                    }
                });
                pnlTablero.add(button);
            }
        }

        // Se limpia los componentes existentes en pnlJuego
        pnlJuego.removeAll();

        // Se establece el layout para pnlJuego
        pnlJuego.setLayout(new BorderLayout());

        // Se agrega pnlTablero a pnlJuego
        pnlJuego.add(pnlTablero, BorderLayout.CENTER);

        // Se asegura que el diseño esté actualizado repintando el tablero
        pnlJuego.revalidate();
        pnlJuego.repaint();
    }
}
