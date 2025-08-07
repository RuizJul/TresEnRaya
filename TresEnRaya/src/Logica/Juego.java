/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Modelo.Jugador;
import Modelo.Tablero;
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

    // comienza el juego con funcionalidad basica para consola
    public void iniciar() {
        Jugador turnoActual = jugador1;
        tablero.mostrar();

        while (true) {
            System.out.println("\nTurno de " + turnoActual.getNombre() + " (" + turnoActual.getSimbolo() + ")");
            int fila, col;
            // solicita coordenadas a jugador humano [fila,columna] para ubicar ficha
            if (turnoActual.esHumano()) {
                int[] movimiento = pedirMovimiento();
                fila = movimiento[0];
                col = movimiento[1];
            } else {
                // IA simple primer espacio libre
                int[] movimiento = obtenerMovimientoIA(turnoActual.getSimbolo());
                fila = movimiento[0];
                col = movimiento[1];
                System.out.println("IA juega en: " + fila + ", " + col);
            }
            // colocamos ficha en el tablero
            if (tablero.colocarFicha(fila, col, turnoActual.getSimbolo())) {
                tablero.mostrar();
                
                // si hay ganador o emapte se lo mestra por consola y se termina juego
                if (tablero.hayGanador(turnoActual.getSimbolo())) {
                    System.out.println("\n " + turnoActual.getNombre() + " ha ganado!");
                    break;
                } else if (tablero.estaLleno()) {
                    System.out.println("\n Empate!");
                    break;
                }
                
                // cambiamos de turno
                turnoActual = (turnoActual == jugador1) ? jugador2 : jugador1;
                //movimiento invalido (ubicar ficha en casilla ocupada)
            } else {
                System.out.println("Movimiento invalido. Intenta de nuevo.");
            }
        }
    }
    
    // metodo para solicitar coordenadas para ubicar la ficha en el tablero
    private int[] pedirMovimiento() {
        int fila, col;
        while (true) {
            System.out.print("Ingresa fila (0-2): ");
            fila = scanner.nextInt();
            System.out.print("Ingresa columna (0-2): ");
            col = scanner.nextInt();
            if (fila >= 0 && fila < 3 && col >= 0 && col < 3) break;// rango valido?
            System.out.println("Coordenadas fuera de rango. Intenta de nuevo.");
        }
        return new int[]{fila, col}; //retorna movimiento en un arreglo
    }
    
    // metodo temporal se reemplazara posiblemente por nodoarbol.mejormovimento() en proceso..
    private int[] obtenerMovimientoIA(char simboloIA) {
        char[][] estado = tablero.getEstado(); // estado actual del tablero
        //buscamos primera casilla libre
        for (int i = 0; i < estado.length; i++) {
            for (int j = 0; j < estado[i].length; j++) {
                if (estado[i][j] == ' ') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // No debería ocurrir si verificamos empate 
    }
}


