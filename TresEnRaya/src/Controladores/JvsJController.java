/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

/**
 *
 * @author Julian
 */
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class JvsJController {

    @FXML
    private TextField jugador1;

    @FXML
    private TextField jugador2;

    @FXML
    private ImageView imgX;

    @FXML
    private ImageView imgO;

    @FXML
    private Button jugarX;  // botón "Listo!" jugador 1

    @FXML
    private Button jugarO;  // botón "Listo!" jugador 2

    private boolean listoX = false;
    private boolean listoO = false;

    @FXML
    public void initialize() {
        // Cargar imagen X
        Image imageX = new Image(getClass().getResource("/img/imgX.png").toExternalForm());
        imgX.setImage(imageX);
        imgX.setFitWidth(200);
        imgX.setPreserveRatio(true);

        // Cargar imagen O
        Image imageO = new Image(getClass().getResource("/img/imgO.png").toExternalForm());
        imgO.setImage(imageO);
        imgO.setFitWidth(200);
        imgO.setPreserveRatio(true);

        // Botones Listo!
        jugarX.setOnAction(e -> {
            listoX = true;
            jugarX.setDisable(true);
            jugarX.setText("Listo ✓");
            jugarX.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            verificarSiAmbosListos();
        });

        jugarO.setOnAction(e -> {
            listoO = true;
            jugarO.setDisable(true);
            jugarO.setText("Listo ✓");
            jugarO.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            verificarSiAmbosListos();
        });
    }

    private void verificarSiAmbosListos() {
        if (listoX && listoO) {
            System.out.println("Ambos jugadores están listos, cargando siguiente vista en 1 segundo...");
            
            // Cambia texto y color de ambos botones para efecto visual
            jugarX.setText("¡Comenzando!");
            jugarO.setText("¡Comenzando!");
            jugarX.setStyle("-fx-background-color: #2E7D32; -fx-text-fill: white;");
            jugarO.setStyle("-fx-background-color: #2E7D32; -fx-text-fill: white;");
            
            // Espera 1 segundo antes de cambiar de vista
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> irASiguienteVista());
            delay.play();
        }
    }

    private void irASiguienteVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/SiguienteVista.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) jugarX.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
