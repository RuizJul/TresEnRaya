package Modelo;

public class Movimiento {
    private int fila;
    private int columna;
    private char simbolo;
    private Jugador jugador;

    public Movimiento(int fila, int columna, Jugador jugador) {
        if (fila < 0 || fila >= 3 || columna < 0 || columna >= 3) {
            throw new IllegalArgumentException("Jugada imposible, coordenadas fuera de rango");
        }
        this.fila = fila;
        this.columna = columna;
        this.jugador = jugador;
        this.simbolo = jugador.getSimbolo();
    }
    public void jugadaInvalida(){
        // repetirTurno();
        throw new IllegalArgumentException("Casilla ocupada, movimiento inválido");
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
