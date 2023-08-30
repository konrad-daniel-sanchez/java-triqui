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
