/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tresenraya;

import Logica.Juego;
import Modelo.Jugador;
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

        System.out.println("/////Configuracion Jugador 1 /////");
        System.out.print("Nombre: ");
        String nombre1 = sc.nextLine();
        System.out.print("Símbolo (X u O): ");
        char simbolo1 = sc.nextLine().toUpperCase().charAt(0);
        System.out.print("¿Es humano? (s/n): ");
        boolean humano1 = sc.nextLine().equalsIgnoreCase("s");

        System.out.println("\n/////Configuracion Jugador 2/////");
        System.out.print("Nombre: ");
        String nombre2 = sc.nextLine();
        // El segundo jugador recibe el símbolo que queda
        char simbolo2 = (simbolo1 == 'X') ? 'O' : 'X';
        System.out.println("Tu simbolo será: " + simbolo2);
        System.out.print("¿Es humano? (s/n): ");
        boolean humano2 = sc.nextLine().equalsIgnoreCase("s");

        // Crear jugadores con la configuración ingresada
        Jugador jugador1 = new Jugador(nombre1, simbolo1, humano1);
        Jugador jugador2 = new Jugador(nombre2, simbolo2, humano2);
        //Inicia el juego
        Juego juego = new Juego(jugador1, jugador2);
        juego.iniciar();
    }
}
