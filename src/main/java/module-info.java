module com.example.relogio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.relogio to javafx.fxml;
    exports com.example.relogio;
}