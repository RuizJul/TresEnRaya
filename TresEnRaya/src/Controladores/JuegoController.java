/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

/**
 *
 * @author Julian
 */
import MinMax.Minmax;
import Modelo.Jugador;
import Modelo.Movimiento;
import Modelo.NodoArbol;
import Modelo.Tablero;
import Modelo.Tablero_Utils;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
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

    private Jugador jugadorX;
    private Jugador jugadorO;

    private Tablero tableroLogico;

    private String fichaJugadorX = "X"; // Por defecto X
    private String fichaJugadorO = "O"; // Por defecto O

    private boolean jugadorXHumano = true;
    private boolean jugadorOHumano = true;

    @FXML
    public void initialize() {
        // Inicializar tablero lógico
        tableroLogico = new Tablero();
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
                    if (pane.getChildren().isEmpty() && turnoActualEsHumano()) {
                        Integer filaIndex = GridPane.getRowIndex(pane);
                        int fila = (filaIndex == null) ? 0 : filaIndex;

                        Integer colIndex = GridPane.getColumnIndex(pane);
                        int columna = (colIndex == null) ? 0 : colIndex;

                        Jugador jugadorActual = turnoX ? jugadorX : jugadorO;

                        // Actualizar lógica
                        tableroLogico.colocarFicha(new Movimiento(fila, columna, jugadorActual));

                        // Actualizar interfaz
                        colocarFichaInterfaz(new Movimiento(fila, columna, jugadorActual));

                        if (tableroLogico.verificarGanador()) {
                            mostrarVentanaVictoria(turnoX ? jugadorX.getNombre() : jugadorO.getNombre());
                            return;
                        }

                        if (!tableroLogico.verificarEstructura()) {
                            mostrarVentanaVictoria("Empate");
                            return;
                        }

                        turnoX = !turnoX;
                        actualizarMensajeTurno();

                        //Si el siguiente turno es IA
                        if (!turnoActualEsHumano()) {
                            turnoIA();
                        }
                    }
                });
            }
        }
        actualizarMensajeTurno();
    }

    private void colocarFichaInterfaz(Movimiento movimiento) {
        for (var nodo : tablero.getChildren()) {
            if (nodo instanceof Pane pane) {
                Integer filaIndex = GridPane.getRowIndex(pane);
                int fila = (filaIndex == null) ? 0 : filaIndex;

                Integer colIndex = GridPane.getColumnIndex(pane);
                int columna = (colIndex == null) ? 0 : colIndex;

                if (fila == movimiento.getFila() && columna == movimiento.getColumna()) {
                    ImageView imgView = new ImageView(movimiento.getJugador().getSimbolo() == 'X' ? imagenX : imagenO);
                    imgView.setFitWidth(pane.getWidth());
                    imgView.setFitHeight(pane.getHeight());
                    imgView.setPreserveRatio(true);

                    // Desplazamiento estético
                    imgView.setTranslateX(50);
                    imgView.setTranslateY(3);

                    DropShadow sombra = movimiento.getJugador().getSimbolo() == 'X'
                            ? new DropShadow(15, Color.GREEN)
                            : new DropShadow(15, Color.PURPLE);
                    imgView.setEffect(sombra);

                    pane.getChildren().add(imgView);
                    break;
                }
            }
        }
    }

    private void turnoIA() {
        if (turnoActualEsHumano()) {
            return;
        }

        // Calcular el movimiento pero no lo aplicamos todavía
        Movimiento movIA = calcularMovimientoIA();
        if (movIA == null) {
            return;
        }

        // Pausa de 1 segundo antes de hacer el movimiento visible
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            // Actualizar lógica
            tableroLogico.colocarFicha(movIA);
            // Actualizar interfaz
            colocarFichaInterfaz(movIA);

            if (tableroLogico.verificarGanador()) {
                Platform.runLater(() -> mostrarVentanaVictoria(movIA.getJugador().getNombre()));
                return;
            }
            if (!tableroLogico.verificarEstructura()) {
                Platform.runLater(() -> mostrarVentanaVictoria("Empate"));
                return;
            }

            turnoX = !turnoX;
            actualizarMensajeTurno();

            // Si sigue siendo IA, repetir turno
            if (!turnoActualEsHumano()) {
                turnoIA();
            }
        });
        pause.play();
    }

    private Movimiento calcularMovimientoIA() {
        Tablero estadoActual = tableroLogico.copiar();
        NodoArbol nodoRaiz = new NodoArbol(estadoActual, null);
        char iaSimbolo = turnoX ? jugadorX.getSimbolo() : jugadorO.getSimbolo();

        int mejorValor = Integer.MIN_VALUE;
        Movimiento mejorMovimiento = null;

        for (int[] mov : Tablero_Utils.obtenerMovimientosDisponibles(estadoActual)) {
            Tablero copia = estadoActual.copiar();
            copia.getTablero()[mov[0]][mov[1]] = iaSimbolo;

            NodoArbol hijo = new NodoArbol(copia, mov);
            int valor = Minmax.minimax(hijo, false, iaSimbolo);

            if (valor > mejorValor) {
                mejorValor = valor;
                mejorMovimiento = new Movimiento(mov[0], mov[1], turnoX ? jugadorX : jugadorO);
            }
        }
        return mejorMovimiento;
    }

    private void mostrarVentanaVictoria(String ganador) {
        try {
            tablero.setDisable(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Victoria.fxml"));
            Parent root = loader.load();

            VictoriaController controller = loader.getController();
            if ("Empate".equalsIgnoreCase(ganador)) {
                controller.setMensaje("¡Es un empate!");
            } else {
                controller.setMensaje("El jugador " + ganador + " ha ganado");
            }

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
        mensajeTurno.setText("Turno de " + jugadorX.getNombre());
        tableroLogico = new Tablero(); // Reiniciar lógica
        //la ia da el primer movimiento
        if (!jugadorX.esHumano()) {
            Movimiento primerMov = new Movimiento(1, 1, jugadorX);
            tableroLogico.colocarFicha(primerMov);
            colocarFichaInterfaz(primerMov);
            turnoX = false;
            actualizarMensajeTurno();
        }
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

    private boolean turnoActualEsHumano() {
        return (turnoX ? jugadorX : jugadorO).esHumano();
    }
    // Nuevo método para actualizar el label del turno

    private void actualizarMensajeTurno() {
        if (jugadorX == null || jugadorO == null) {
            mensajeTurno.setText("Turno de X"); // fallback
        } else {
            mensajeTurno.setText("Turno de " + (turnoX ? jugadorX.getNombre() : jugadorO.getNombre()));
        }
    }

    // Método para configurar qué tipo de jugadores son (humano o PC)
    public void configurarJugadores(boolean jugadorXHumano, boolean jugadorOHumano) {
        this.jugadorXHumano = jugadorXHumano;
        this.jugadorOHumano = jugadorOHumano;

        // Resetear instancias para evitar errores al crear jugadores
        Jugador.resetInstancias();

        // Crear jugadores con nombre temporal que luego será actualizado
        jugadorX = new Jugador(jugadorXHumano ? "Jugador X" : "PC X");
        jugadorX.setEsHumano(jugadorXHumano);
        jugadorX.setSimbolo(fichaJugadorX.charAt(0));

        jugadorO = new Jugador(jugadorOHumano ? "Jugador O" : "PC O");
        jugadorO.setEsHumano(jugadorOHumano);
        jugadorO.setSimbolo(fichaJugadorO.charAt(0));

        actualizarMensajeTurno();
        //Primer turno ia
        if (!jugadorX.esHumano()) {
            Platform.runLater(() -> {
                Movimiento primerMov = new Movimiento(1, 1, jugadorX);
                tableroLogico.colocarFicha(primerMov);
                colocarFichaInterfaz(primerMov);
                turnoX = false;
                actualizarMensajeTurno();
            });
        }
    }

    // Establecer nombre de jugador X
    public void setNombreJugadorX(String nombre) {
        if (jugadorX != null && nombre != null && !nombre.isBlank()) {
            jugadorX.setNombre(nombre);
        }
    }

    // Establecer nombre de jugador O
    public void setNombreJugadorO(String nombre) {
        if (jugadorO != null && nombre != null && !nombre.isBlank()) {
            jugadorO.setNombre(nombre);
        }
    }

    // Establecer ficha jugador X
    public void setFichaJugadorX(String ficha) {
        if (ficha != null && (ficha.equalsIgnoreCase("X") || ficha.equalsIgnoreCase("O"))) {
            fichaJugadorX = ficha.toUpperCase();
        }
    }

    // Establecer ficha jugador O
    public void setFichaJugadorO(String ficha) {
        if (ficha != null && (ficha.equalsIgnoreCase("X") || ficha.equalsIgnoreCase("O"))) {
            fichaJugadorO = ficha.toUpperCase();
        }
    }

}
