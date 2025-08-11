/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

/**
 *
 * @author Julian
 */
import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PCvsPCController {

    @FXML
    private ImageView imagenX;

    @FXML
    private ImageView imagenO;

    @FXML
    private Button jugar;

    @FXML
    public void initialize() {
        // Carga las imágenes desde resources/img
        imagenX.setImage(new Image(getClass().getResource("/img/imgX.png").toExternalForm()));
        imagenO.setImage(new Image(getClass().getResource("/img/imgO.png").toExternalForm()));

        // Acción del botón jugar
        jugar.setOnAction(e -> {
            jugar.setDisable(true);
            jugar.setText("¡Comenzando!");
            jugar.setStyle("-fx-background-color: #2E7D32; -fx-text-fill: white;");
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> irASiguienteVista());
            delay.play();
        });
    }
     private void irASiguienteVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/SiguienteVista.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) jugar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}