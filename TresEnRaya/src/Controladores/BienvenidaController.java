/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

/**
 *
 * @author Julian
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class BienvenidaController {

    @FXML
    private Button JGVSJG;  // Jugador vs Jugador

    @FXML
    private Button JGVSPC;  // Jugador vs PC

    @FXML
    private Button PCVSPC;  // PC vs PC

    private boolean jugador1Humano;
    private boolean jugador2Humano;

    @FXML
    public void initialize() {
        JGVSJG.setOnAction(e -> {
            jugador1Humano = true;
            jugador2Humano = true;
            cargarVista("/Vistas/JvsJ.fxml");
        });
        JGVSPC.setOnAction(e -> {
            jugador1Humano = true;
            jugador2Humano = false;
            cargarVista("/Vistas/JvsPC.fxml");
        });

        
    }

    private void cargarVista(String rutaFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(rutaFXML));
            Stage stage = (Stage) JGVSJG.getScene().getWindow(); // Obtener ventana actual
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
