/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MinMax;

import Modelo.NodoArbol;
import Modelo.Tablero;
import Modelo.Tablero_Utils;

/**
 *
 * @author Julian
 */
public class Minmax {

    public static int minimax(NodoArbol nodo, boolean esMax, char iaSimbolo) {
        Tablero estado = nodo.getEstado();

        // Actualizar la copia estática en Tablero_Utils para evaluar el estado actual
        Tablero_Utils.copiarTableros(estado);
        int puntaje = Tablero_Utils.evaluarEstados();

        // Caso base: si ya hay un ganador o empate
        if (puntaje == 1) return (iaSimbolo == 'X') ? 10 : -10;
        if (puntaje == -1) return (iaSimbolo == 'O') ? 10 : -10;
        if (Tablero_Utils.obtenerMovimientosDisponibles(estado).isEmpty()) return 0;

        if (esMax) {
            int mejor = Integer.MIN_VALUE;
            for (int[] mov : Tablero_Utils.obtenerMovimientosDisponibles(estado)) {
                Tablero copia = estado.copiar();
                copia.getTablero()[mov[0]][mov[1]] = iaSimbolo;

                NodoArbol hijo = new NodoArbol(copia, mov);
                nodo.agregarHijo(hijo);

                int valor = minimax(hijo, false, iaSimbolo);
                mejor = Math.max(mejor, valor);
            }
            nodo.setValor(mejor);
            return mejor;
        } else {
            int mejor = Integer.MAX_VALUE;
            char oponente = (iaSimbolo == 'X') ? 'O' : 'X';
            for (int[] mov : Tablero_Utils.obtenerMovimientosDisponibles(estado)) {
                Tablero copia = estado.copiar();
                copia.getTablero()[mov[0]][mov[1]] = oponente;

                NodoArbol hijo = new NodoArbol(copia, mov);
                nodo.agregarHijo(hijo);

                int valor = minimax(hijo, true, iaSimbolo);
                mejor = Math.min(mejor, valor);
            }
            nodo.setValor(mejor);
            return mejor;
        }
    }
}
