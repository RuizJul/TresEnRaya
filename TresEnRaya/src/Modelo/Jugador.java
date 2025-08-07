/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Julian
 */
public class Jugador {
    private String nombre;
    private char simbolo;
    private boolean esHumano;

    // jagdor con atributos de nombre, simbolo de ficha a usar,  es jugador humano o computadora
    public Jugador(String nombre, char simbolo, boolean esHumano) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.esHumano = esHumano;
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

}
