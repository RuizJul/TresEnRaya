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
import javafx.scene.layout.VBox;
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
    private VBox root;

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
        root.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    regresarAPantallaAnterior();
                    break;
                default:
                    break;
            }
        });

        // Para que reciba eventos de teclado, pide el foco:
        root.requestFocus();
    }

    private void regresarAPantallaAnterior() {
        try {
            Parent anterior = FXMLLoader.load(getClass().getResource("/Vistas/Bienvenida.fxml"));
            Stage stage = (Stage) root.getScene().getWindow();
            stage.getScene().setRoot(anterior);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void irASiguienteVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Juego.fxml"));
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
