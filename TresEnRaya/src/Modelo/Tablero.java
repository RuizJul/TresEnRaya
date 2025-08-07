/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Julian
 */
public class Tablero {

    private char[][] tablero;
    private final int TAM = 3;

    // tablero vacio de 3x3
    public Tablero() {
        tablero = new char[TAM][TAM];
        inicializar();
    }

    // llenamos el tablero con espacios vacios 
    public void inicializar() {
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    // ubicamos una ficha en el espacio correspondiente
    public boolean colocarFicha(int fila, int col, char simbolo) {
        if (fila >= 0 && fila < TAM && col >= 0 && col < TAM) {//La coordenada ingresada es valida
            if (tablero[fila][col] == ' ') {//La celda esta vacia
                tablero[fila][col] = simbolo;
                return true;
            }
        }
        return false; // Movimientos invalidos
    }

    public boolean hayGanador(char simbolo) {
        // Filas y columnas
        for (int i = 0; i < TAM; i++) {
            if ((tablero[i][0] == simbolo && tablero[i][1] == simbolo && tablero[i][2] == simbolo)
                    || (tablero[0][i] == simbolo && tablero[1][i] == simbolo && tablero[2][i] == simbolo)) {
                return true;
            }
        }
        // Diagonales
        return (tablero[0][0] == simbolo && tablero[1][1] == simbolo && tablero[2][2] == simbolo)
                || (tablero[0][2] == simbolo && tablero[1][1] == simbolo && tablero[2][0] == simbolo);
    }

    // metodo para validar empate 
    public boolean estaLleno() {
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // estado actual del tablero
    public char[][] getEstado() {
        return tablero;
    }

    //construimos el tablero para mostrarlo en consola
    public void mostrar() {
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                System.out.print(" " + tablero[i][j] + " ");
                if (j < TAM - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < TAM - 1) {
                System.out.println("---+---+---");
            }
        }
    }

    // creamos copia del tablero actual para simular jugadas en el arbol
    public Tablero copiar() {
        Tablero copia = new Tablero();
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                copia.tablero[i][j] = this.tablero[i][j];
            }
        }
        return copia;
    }
}
