/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tresenraya;

import Logica.Juego;
import Modelo.*;
import java.util.Scanner;

/**
 *
 * @author Julian
 */
public class TresEnRaya {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre jugador 1: ");
        String nombre1 = sc.nextLine();
        Jugador jugador1 = new Jugador(nombre1);
        jugador1.setSimbolo('X');
        jugador1.setEsHumano(true);

        System.out.print("Nombre jugador 2: ");
        String nombre2 = sc.nextLine();
        Jugador jugador2 = new Jugador(nombre2);
        jugador2.setSimbolo('O');
        jugador2.setEsHumano(true);  // Ambos humanos

        Juego juego = new Juego(jugador1, jugador2);
        juego.iniciar();

        sc.close();
    }
}
