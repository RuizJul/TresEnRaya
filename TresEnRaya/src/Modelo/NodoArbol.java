/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.*;

/**
 *
 * @author Julian
 */
import Modelo.Tablero;
import java.util.ArrayList;
import java.util.List;

public class NodoArbol {

    private Tablero estado;
    private int[] movimiento; // fila, columna
    private List<NodoArbol> hijos;
    private int valor; // evaluación Minimax

    public NodoArbol(Tablero estado, int[] movimiento) {
        this.estado = estado;
        this.movimiento = movimiento;
        this.hijos = new ArrayList<>();
        this.valor = 0;
    }

    public void agregarHijo(NodoArbol hijo) {
        hijos.add(hijo);
    }

    public List<NodoArbol> getHijos() {
        return hijos;
    }

    public Tablero getEstado() {
        return estado;
    }

    public int[] getMovimiento() {
        return movimiento;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
