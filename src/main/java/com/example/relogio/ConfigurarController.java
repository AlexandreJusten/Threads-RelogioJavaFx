package com.example.relogio;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigurarController {
    @FXML
    private Button sairButton;
    @FXML
    private Button definirButton;
    @FXML
    private Button definirPeloSystemButton;
    @FXML
    private TextField horaTextFild;
    @FXML
    private TextField minTextFild;
    @FXML
    private TextField secTextFild;

    public static int horaConfig=0;

    @FXML
    protected void sair(){
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void definir(){

        sair();
    }
    @FXML
    protected void definirPeloSystem(){
        sair();
    }
}
