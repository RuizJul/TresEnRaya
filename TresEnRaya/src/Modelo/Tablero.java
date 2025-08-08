/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Julian Ruiz, Jose Mora
 */
public class Tablero {
    private char[][] tabla;
    private final int TAM = 3;
    private String estructura;

    public Tablero(){
        tabla = new char[3][3];
        agregarTablero();
        estructura = armarEstructura();
    }

    public String armarEstructura(){
        return estructura = String.format(
        "      |        |       \n" + 
        "   %c  |    %c   |  %c  \n" + 
        "      |        |       \n" + 
        "-----------------------\n" + 
        "      |        |       \n" + 
        "   %c  |    %c   |  %c  \n" + 
        "      |        |       \n" + 
        "-----------------------\n" +
        "      |        |       \n" + 
        "   %c  |    %c   |  %c  \n" +
        "      |        |       \n",tabla[0][0],tabla[0][1],tabla[0][2],tabla[1][0],tabla[1][1],tabla[1][2],tabla[2][0],tabla[2][1],tabla[2][2]);
    }
    public void agregarTablero(){
        for(int i = 0;i<TAM;i++){
            for(int o = 0;o<TAM;o++){
                tabla[i][o] = ' ';
            }
        }
        armarEstructura();   
    }

    public void colocarFicha(Movimiento move){
        if(casillaVacia(move.getFila(), move.getColumna())){
            tabla[move.getFila()][move.getColumna()] = move.getSimbolo();
        }
        else{
            move.jugadaInvalida();
        }
        
    }

    public boolean casillaVacia(int fila, int columna){
        return tabla[fila][columna] == ' ';
    }

    public Boolean verificarEstructura(){
        //Analisis por filas para saber si hay casillas vacias
        for(int i = 0; i < tabla.length; i++){ // Filas
            for(int j = 0; j<tabla.length ;j++){ // Columnas
                if(casillaVacia(i, j)) return true;
            }
        }
        return false;
    }

    public Boolean verificarGanador(){
        //Por Filas
        for(int i = 0; i < tabla.length; i++){
            if(tabla[i][0] != ' ' && tabla[i][0] == tabla[i][1] && tabla[i][1] == tabla[i][2]) return true;
        }
        //Por columnas
        for(int i = 0; i < tabla.length; i++){ // Filas
            if(tabla[0][i] != ' ' && tabla[0][i] == tabla[1][i] && tabla[1][i] == tabla[2][i]) return true;
        }
        //Por diagonales
        if(tabla[0][0] != ' ' && tabla[0][0] == tabla[1][1] && tabla[1][1] == tabla[2][2]) return true;
        if(tabla[0][2] != ' ' && tabla[0][2] == tabla[1][1] && tabla[1][1] == tabla[2][0]) return true;
        return false;
    }

    public char[][] getTablero(){
        return tabla;
    }
    public void verificarEstadoJuego(){
        if (verificarGanador()) {
            System.out.println("¡Hay un ganador!");
        } else if (verificarEstructura() && !verificarGanador()) {
            System.out.println("¡Hay empate!");
        } else {
            System.out.println("Aún no hay ganador.");
        }
    }
    
    public Tablero copiar() {
        Tablero copia = new Tablero();
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                copia.tabla[i][j] = this.tabla[i][j];
            }
        }
        return copia;
    }

    public String getEstructura(){
        return estructura;
    }
}
