/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Modelo.*;
import java.util.Scanner;

/**
 *
 * @author Julian
 */
public class Juego {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Scanner scanner;

    public Juego(Jugador jugador1, Jugador jugador2) {
        this.tablero = new Tablero();
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        Jugador turnoActual = jugador1;
        mostrarTablero();

        while (true) {
            System.out.println("\nTurno de " + turnoActual.getNombre() + " (" + turnoActual.getSimbolo() + ")");
            int fila, col;
            boolean movimientoValido = false;

            while (!movimientoValido) {
                try {
                    if (turnoActual.esHumano()) {
                        int[] movimiento = pedirMovimiento();
                        fila = movimiento[0];
                        col = movimiento[1];
                    } else {
                        int[] movimiento = obtenerMovimientoIA();
                        fila = movimiento[0];
                        col = movimiento[1];
                        System.out.println("IA juega en: " + fila + ", " + col);
                    }

                    Movimiento move = new Movimiento(fila, col, turnoActual);
                    tablero.colocarFicha(move); // Aquí puede lanzar excepción si no es válido
                    movimientoValido = true; // Si no lanza excepción, movimiento válido
                } catch (IllegalArgumentException e) {
                    System.out.println("Movimiento inválido: " + e.getMessage() + ". Intenta de nuevo.");
                }
            }

            mostrarTablero();

            if (tablero.verificarGanador()) {
                System.out.println("\n" + turnoActual.getNombre() + " ha ganado!");
                break;
            } else if (!tablero.verificarEstructura()) { // No hay casillas vacías → empate
                System.out.println("\nEmpate!");
                break;
            }

            // Cambiar turno
            turnoActual = (turnoActual == jugador1) ? jugador2 : jugador1;
        }
    }

    private int[] pedirMovimiento() {
        int fila, col;
        while (true) {
            System.out.print("Ingresa fila (0-2): ");
            fila = scanner.nextInt();
            System.out.print("Ingresa columna (0-2): ");
            col = scanner.nextInt();
            if (fila >= 0 && fila < 3 && col >= 0 && col < 3) break;
            System.out.println("Coordenadas fuera de rango. Intenta de nuevo.");
        }
        return new int[]{fila, col};
    }

    private int[] obtenerMovimientoIA() {
        char[][] estado = tablero.getTablero();
        for (int i = 0; i < estado.length; i++) {
            for (int j = 0; j < estado[i].length; j++) {
                if (estado[i][j] == ' ') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private void mostrarTablero() {
        System.out.println(tablero.armarEstructura());
    }
}

