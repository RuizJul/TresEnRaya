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
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JuegoController {

    @FXML
    private GridPane tablero;

    @FXML
    private Label mensajeTurno;

    private boolean turnoX = true; // true = turno X, false = turno O

    private Image imagenX;
    private Image imagenO;

    @FXML
    public void initialize() {
        // Cargar imágenes
        imagenX = new Image(getClass().getResource("/img/imgX.png").toExternalForm());
        imagenO = new Image(getClass().getResource("/img/imgO.png").toExternalForm());

        // Efecto sombra verde
        DropShadow sombraVerde = new DropShadow();
        sombraVerde.setRadius(15);
        sombraVerde.setColor(Color.GREEN);
        // Efecto sombra morada
        DropShadow sombraMorada = new DropShadow();
        sombraMorada.setRadius(15);
        sombraMorada.setColor(Color.PURPLE);

        // Configurar clics en cada celda
        for (var nodo : tablero.getChildren()) {
            if (nodo instanceof Pane pane) {
                pane.setOnMouseClicked(e -> {
                    if (pane.getChildren().isEmpty()) {
                        ImageView imgView = new ImageView(turnoX ? imagenX : imagenO);
                        imgView.setFitWidth(pane.getWidth());
                        imgView.setFitHeight(pane.getHeight());
                        imgView.setPreserveRatio(true);

                        // Desplazamiento estético
                        imgView.setTranslateX(50);
                        imgView.setTranslateY(3);

                        // Aplicar sombra según turno
                        imgView.setEffect(turnoX ? sombraVerde : sombraMorada);

                        pane.getChildren().add(imgView);

                        // Verificar si alguien ganó
                        if (hayGanador()) {
                            mostrarVentanaVictoria(turnoX ? "X" : "O");
                            return;
                        }

                        // Cambiar turno
                        turnoX = !turnoX;
                        mensajeTurno.setText("Turno de " + (turnoX ? "X" : "O"));
                    }
                });
            }
        }
    }

    private boolean hayGanador() {
        //LOGICA DE IA
        return false;
    }

    private void mostrarVentanaVictoria(String ganador) {
        try {
            tablero.setDisable(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Victoria.fxml"));
            Parent root = loader.load();

            VictoriaController controller = loader.getController();
            controller.setMensaje("El jugador " + ganador + " ha ganado");

            Stage popup = new Stage();
            popup.setScene(new Scene(root));
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initOwner(tablero.getScene().getWindow());
            popup.setResizable(false);
            popup.setTitle("¡Victoria!");
            popup.showAndWait();

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                if (controller.isJugarOtraVez()) {
                    reiniciarTablero();
                } else {
                    volverAPantallaBienvenida();
                }
            });
            pause.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reiniciarTablero() {
        tablero.getChildren().forEach(nodo -> {
            if (nodo instanceof Pane pane) {
                pane.getChildren().clear();
            }
        });
        tablero.setDisable(false);
        turnoX = true;
        mensajeTurno.setText("Turno de X");
    }

    private void volverAPantallaBienvenida() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Bienvenida.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) tablero.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Pantalla de Bienvenida");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
