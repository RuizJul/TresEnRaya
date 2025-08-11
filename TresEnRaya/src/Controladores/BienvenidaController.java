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

    @FXML
    public void initialize() {
        JGVSJG.setOnAction(e -> cargarVista("/Vistas/JvsJ.fxml"));
        JGVSPC.setOnAction(e -> cargarVista("/Vistas/JvsPC.fxml"));
        PCVSPC.setOnAction(e -> cargarVista("/Vistas/PCvsPC.fxml"));
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