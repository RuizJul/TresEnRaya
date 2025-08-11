module com.mycompany.TresEnRaya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens Controladores to javafx.fxml;
    exports Controladores;
}
