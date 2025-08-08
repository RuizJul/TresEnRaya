package Modelo;

import java.util.*;

public class Tablero_Utils {
    
    private static Tablero copia;
    
    public static void copiarTableros(Tablero original){
        copia = original;
    }
    public static List<int[]> obtenerMovimientosDisponibles() {
        List<int[]> movimientos = new ArrayList<>();
        char[][] tabla = copia.getTablero();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabla[i][j] == ' ') {
                    movimientos.add(new int[]{i, j});
                }
            }
        }
        return movimientos;
    }

    //Utilitarios para cuando hagamos minimax algorithm
    public static int evaluarEstados() {
        // 1 si gana X, -1 si gana O, 0 si empate o sin resultado
        if (hayGanador('X')) return 1;
        if (hayGanador('O')) return -1;
        return 0;
    }

    private static boolean hayGanador(char simbolo){
    char[][] tabla = copia.getTablero();
    // Filas
    for (int i = 0; i < 3; i++) {
        if (tabla[i][0] == simbolo && tabla[i][1] == simbolo && tabla[i][2] == simbolo) {
            return true;
        }
    }
    // Columnas
    for (int j = 0; j < 3; j++) {
        if (tabla[0][j] == simbolo && tabla[1][j] == simbolo && tabla[2][j] == simbolo) {
            return true;
        }
    }
    // Diagonales
    if (tabla[0][0] == simbolo && tabla[1][1] == simbolo && tabla[2][2] == simbolo) {
        return true;
    }
    if (tabla[0][2] == simbolo && tabla[1][1] == simbolo && tabla[2][0] == simbolo) {
        return true;
    }

    return false;
}


}
