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
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JvsPCController {

    @FXML
    private TextField jugadorName;

    @FXML
    private ImageView imagenX;

    @FXML
    private ImageView imagenO;

    @FXML
    private Button jugar;

    private String simboloSeleccionado = null;

    @FXML
    public void initialize() {
        // Carga las imágenes desde resources/img
        imagenX.setImage(new Image(getClass().getResource("/img/imgX.png").toExternalForm()));
        imagenO.setImage(new Image(getClass().getResource("/img/imgO.png").toExternalForm()));

        // Inicialmente botón deshabilitado
        jugar.setDisable(true);

        // Añadir manejadores de click a las imágenes
        imagenX.setOnMouseClicked(e -> seleccionarSimbolo("X"));
        imagenO.setOnMouseClicked(e -> seleccionarSimbolo("O"));

        // Acción botón "Listo!"
        jugar.setOnAction(e -> {
            jugar.setDisable(true);
            jugar.setText("¡Comenzando!");
            jugar.setStyle("-fx-background-color: #2E7D32; -fx-text-fill: white;");
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> irASiguienteVista());
            delay.play();
        });
    }

    private void seleccionarSimbolo(String simbolo) {
        simboloSeleccionado = simbolo;
        jugar.setDisable(false);
        aplicarSombra();
    }

    private void aplicarSombra() {
        DropShadow sombra = new DropShadow(20, Color.GREEN);
        if ("X".equals(simboloSeleccionado)) {
            imagenX.setEffect(sombra);
            imagenO.setEffect(null);
        } else if ("O".equals(simboloSeleccionado)) {
            imagenO.setEffect(sombra);
            imagenX.setEffect(null);
        }
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
