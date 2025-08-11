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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VictoriaController {

    @FXML
    private Label mensajeVictoria;

    @FXML
    private Button confirmacion;

    @FXML
    private Button negacion;

    private boolean jugarOtraVez = false;

    @FXML
    public void initialize() {
        confirmacion.setOnAction(e -> {
            jugarOtraVez = true;
            cerrarVentana();
        });

        negacion.setOnAction(e -> {
            jugarOtraVez = false;
            cerrarVentana();
        });
    }

    public void setMensaje(String mensaje) {
        mensajeVictoria.setText(mensaje);
    }

    public boolean isJugarOtraVez() {
        return jugarOtraVez;
    }

    private void cerrarVentana() {
        Stage stage = (Stage) confirmacion.getScene().getWindow();
        stage.close();
    }
}
