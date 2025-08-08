/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Julian Ruiz, Jose Mora
 */
public class Jugador {
    private String nombre;
    private char simbolo;
    public enum Tipo {Jugador1, Jugador2};
    private boolean esHumano;
    private static int instancias = 0;
    private Tipo tipo;

    public Jugador(String nombre) {
        this.nombre = nombre;
        instancias++;
        if (instancias == 1) {
            this.tipo = Tipo.Jugador1;
            this.simbolo = 'X';
        } else if (instancias == 2) {
            this.tipo = Tipo.Jugador2;
            this.simbolo = 'O';
        } else {
            throw new IllegalStateException("Solo pueden haber dos jugadores");
        }
        this.esHumano = true; // por defecto, se puede cambiar con setter
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public void setEsHumano(boolean esHumano) {
        this.esHumano = esHumano;
    }

    public String getNombre() {
        return nombre;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public boolean esHumano() {
        return esHumano;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public static int getInstancias() {
        return instancias;
    }

    // Método para resetear instancias al iniciar nueva partida
    public static void resetInstancias() {
        instancias = 0;
    }
}
