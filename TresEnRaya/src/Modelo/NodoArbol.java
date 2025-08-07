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
public class NodoArbol {

    Tablero tableroActual;
    List<NodoArbol> hijos;
    int utilidad;
    boolean turnoJugador;

    public NodoArbol() {
        this.tableroActual = new Tablero();
        this.hijos = null;
        this.utilidad = 0;
        this.turnoJugador = true;

    }

    public void agregarHijo() {
        if (hijos == null) {
            hijos = new ArrayList<>();
        }

        // Definir símbolo según turno
        char simbolo = turnoJugador ? 'X' : 'O';

        // Obtener estado actual
        char[][] estado = tableroActual.getEstado();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (estado[i][j] == ' ') { // Casilla libre
                    // Clonar el tablero actual
                    Tablero nuevoTablero = tableroActual.copiar();
                    // Colocar la ficha en la copia
                    nuevoTablero.colocarFicha(i, j, simbolo);
                    // Crear el nodo hijo
                    NodoArbol hijo = new NodoArbol();
                    hijo.setTableroActual(nuevoTablero);
                    hijo.setTurnoJugador(!turnoJugador); // Cambiar turno
                    // Agregar a la lista de hijos
                    hijos.add(hijo);
                }
            }
        }
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }

    public Tablero getTableroActual() {
        return tableroActual;
    }

    public void setTableroActual(Tablero tableroActual) {
        this.tableroActual = tableroActual;
    }

    public List<NodoArbol> getHijos() {
        return hijos;
    }

    public void setHijos(List<NodoArbol> hijos) {
        this.hijos = hijos;
    }

    public boolean isTurnoJugador() {
        return turnoJugador;
    }

    public void setTurnoJugador(boolean turnoJugador) {
        this.turnoJugador = turnoJugador;
    }

}
