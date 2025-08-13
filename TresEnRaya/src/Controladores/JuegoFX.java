/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JuegoFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Cargar el archivo FXML desde resources
        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/Bienvenida.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Tres En Raya EPICO LEGENDARIO ATOMICO DIMENSIONAL");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
