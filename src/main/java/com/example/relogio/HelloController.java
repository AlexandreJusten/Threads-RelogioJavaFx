package com.example.relogio;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

public class HelloController {
    //Main relogio
    @FXML
    private Label horaLabel;
    @FXML
    private Label minutosLabel;
    @FXML
    public Label segundoLabel;
    @FXML
    private Button sairButton;
    @FXML
    private Button configButton;
    //config
    @FXML
    private AnchorPane anchorPaneConfig;
    @FXML
    private Button sairnConfig;
    @FXML
    private Button definirConfig;
    @FXML
    private Button definirPeloSistemaConfig;
    @FXML
    private TextField horaConfig;
    @FXML
    private TextField minConfig;
    @FXML
    private TextField secConfig;

    //Alarme
    @FXML
    private TextField alarmeHoraTextField;
    @FXML
    private TextField alarmeMinTextField;
    @FXML
    private ListView alarmesListView;
    @FXML
    private Button definirAlarmeButton;
    @FXML
    private Button removerAlarmeButton;


    //Cronometro

    @FXML
    private Label horaCronometroLabel;
    @FXML
    private Label minutosCronometroLabel;
    @FXML
    private Label segundoCronometroLabel;
    @FXML
    private Label miliSecCronometroLabel;
    @FXML
    private Button iniciarCronometroButton;
    @FXML
    private Button pararCronometroButton;
    @FXML
    private Button marcarCronometroButton;
    @FXML
    private Button zerarCronometroButton;

    @FXML
    private ListView cronometroListView;


   public static int contadorSec =0;
    public static int contadorMin =0;
    public static int contadorHora =0;


    public static  int contadorHoraCronometro=0;
    public static int contadorMinCronometro=0;
    public static int contadorSecCronometro=0;
    public static int contadorMilCronometro=0;

    public Thread tCronometro;



@FXML
public void initialize() throws IOException {
    anchorPaneConfig.setVisible(false);
    relogio();
    dispararAlarme();
}
    @FXML
    public void relogio() throws IOException {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
                contadorSec++;
                relogio();

            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {
                if (contadorSec==60){
                  contadorSec=0;
                    segundoLabel.setText("00");
                   // return;
                }
                if (Integer.parseInt(segundoLabel.getText())<9){
                    segundoLabel.setText("0"+ contadorSec);
                   // return;
                }
                else {
                    segundoLabel.setText("" + contadorSec);
                }
                if (contadorSec==0){
                    contadorMin++;
                    if (contadorMin==60){
                        contadorMin=0;
                        minutosLabel.setText("00");
                        //return;
                    }
                    if (Integer.parseInt(minutosLabel.getText())<9){
                        minutosLabel.setText("0"+contadorMin);
                    }else {
                        minutosLabel.setText(""+contadorMin);
                    }
                    if (contadorMin==0){
                        contadorHora++;
                        if (contadorHora>23){
                            contadorHora=0;
                            horaLabel.setText("00");
                           // return;
                        }
                        if (Integer.parseInt(horaLabel.getText())<9){
                            horaLabel.setText("0"+contadorHora);
                        }else {
                            horaLabel.setText(""+contadorHora);
                        }
                    }}
                });
            });
            t.start();
        }

    @FXML
    public void cronometroStart(){

        tCronometro = new Thread(() -> {
            try {
                Thread.sleep(1);
                contadorMilCronometro++;
                if (contadorMilCronometro==1000){
                contadorSecCronometro++;}
                cronometroStart();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {
                if (contadorMilCronometro>999){
                    miliSecCronometroLabel.setText("000");
                    contadorMilCronometro=0;
                }else {miliSecCronometroLabel.setText(String.valueOf(contadorMilCronometro));}
                if (contadorSecCronometro>59){
                    contadorSecCronometro=0;
                    segundoCronometroLabel.setText("00");
                    return;
                }
                if (Integer.parseInt(segundoCronometroLabel.getText())<=9){
                    segundoCronometroLabel.setText("0"+ contadorSecCronometro);
                    return;
                }
                else {
                    segundoCronometroLabel.setText("" + contadorSecCronometro);
                }
                if (contadorSecCronometro>=59){
                    contadorMinCronometro++;
                    if (contadorMinCronometro>59){
                        contadorMinCronometro=0;
                        minutosCronometroLabel.setText("00");
                        return;
                    }
                    if (Integer.parseInt(minutosCronometroLabel.getText())<9){
                        minutosCronometroLabel.setText("0"+contadorMinCronometro);
                    }else {
                        minutosCronometroLabel.setText(""+contadorMinCronometro);
                    }
                    if (contadorMinCronometro>=59){
                        contadorHoraCronometro++;
                        if (contadorHoraCronometro>23 ){
                            contadorHoraCronometro=0;
                            horaCronometroLabel.setText("00");
                            return;
                        }
                        if (Integer.parseInt(horaCronometroLabel.getText())<9){
                            horaCronometroLabel.setText("0"+contadorHoraCronometro);
                        }else {
                            horaCronometroLabel.setText(""+contadorHoraCronometro);
                        }
                    }}
            });
        });
        tCronometro.start();
    }
    @FXML
    public void marcarCronometro(){
        cronometroListView.getItems().addAll(horaCronometroLabel.getText()+":"+minutosCronometroLabel.getText()+":"+segundoCronometroLabel.getText()+":"+miliSecCronometroLabel.getText());
    }

   @FXML
   public void stopCronometro(){
    tCronometro.stop();
   }
   @FXML
   public void zerarCronometro(){
        contadorHoraCronometro=0;
       contadorMinCronometro=0;
       contadorSecCronometro=0;
       contadorMilCronometro=0;
       horaCronometroLabel.setText("00");
       minutosCronometroLabel.setText("00");
       segundoCronometroLabel.setText("00");
       miliSecCronometroLabel.setText("000");
   }
    @FXML
    public void removeCronometro(){
    cronometroListView.getItems().remove(cronometroListView.getFocusModel().getFocusedIndex());
}
    @FXML
    protected void sair(){
        Platform.exit();
    }

    //config
    @FXML
    protected void configurar() throws IOException {
        anchorPaneConfig.setVisible(true);
    }

    @FXML
    public void sairConfig(){
    anchorPaneConfig.setVisible(false);
    }
    @FXML
    private void definirConfig(){
        if(Objects.equals(horaConfig.getText(), "") | Objects.equals(minConfig.getText(), "") | !horaConfig.getText().matches("[+-]?\\d*(\\.\\d+)?") | !minConfig.getText().matches("[+-]?\\d*(\\.\\d+)?") | !secConfig.getText().matches("[+-]?\\d*(\\.\\d+)?")){
            JOptionPane.showMessageDialog(null,"Hora ou Minuto Não informado,Relogio não definido!","Configurar",JOptionPane.INFORMATION_MESSAGE);
            horaConfig.setText("");
            minConfig.setText("");
            secConfig.setText("");
            return;
        }
        if(Integer.parseInt(horaConfig.getText()) > 23 | Integer.parseInt(minConfig.getText())>59 | Integer.parseInt(secConfig.getText())>59){
            JOptionPane.showMessageDialog(null,"Valor invalido","Configurar",JOptionPane.INFORMATION_MESSAGE);
            horaConfig.setText("");
            minConfig.setText("");
            secConfig.setText("");
            return;
        }
        String hora="";
        String min="";
        String sec="";
        if (Integer.parseInt(horaConfig.getText())<=9 ){
            hora= "0";
        }
        if (Integer.parseInt(minConfig.getText())<=9){
            min= "0";
        }
        if (Integer.parseInt(secConfig.getText())<=9){
            sec= "0";
        }
        String formatHora = horaConfig.getText();
        formatHora = Integer.valueOf(formatHora).toString();
        String formatMin = minConfig.getText();
        formatMin = Integer.valueOf(formatMin).toString();
        String formatSec = secConfig.getText();
        formatSec = Integer.valueOf(formatSec).toString();

        //setar
        contadorHora= Integer.parseInt(formatHora);
        contadorMin= Integer.parseInt(formatMin);
        contadorSec= Integer.parseInt(formatSec);
        horaLabel.setText(hora+formatHora);
        minutosLabel.setText(min+formatMin);
        segundoLabel.setText(sec+formatSec);
        horaConfig.setText("");
        minConfig.setText("");
        secConfig.setText("");
        sairConfig();
    }
    @FXML
    private void definirPeloSistema(){
        Calendar data = Calendar.getInstance();
        int hora = data.get(Calendar.HOUR_OF_DAY);
        int min = data.get(Calendar.MINUTE);
        int seg = data.get(Calendar.SECOND);


        String horaAux="";
        String minAux="";
        String secAux="";
        if (hora<=9 ){
            horaAux= "0";
        }
        if (min<=9){
            minAux= "0";
        }
        if (seg<=9){
            secAux= "0";
        }
        //setar
        contadorHora= hora;
        contadorMin= min;
        contadorSec= seg;
        horaLabel.setText(horaAux+hora);
        minutosLabel.setText(minAux+min);
        segundoLabel.setText(secAux+seg);
        sairConfig();
    }
    //alarme

   @FXML
    protected void alarme() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(Objects.equals(alarmeHoraTextField.getText(), "") | Objects.equals(alarmeMinTextField.getText(), "") | !alarmeHoraTextField.getText().matches("[+-]?\\d*(\\.\\d+)?") | !alarmeMinTextField.getText().matches("[+-]?\\d*(\\.\\d+)?")){
            JOptionPane.showMessageDialog(null,"Hora ou Minuto Não informado,Alarme não definido!","Alarme",JOptionPane.INFORMATION_MESSAGE);
            alarmeHoraTextField.setText("");
            alarmeMinTextField.setText("");
            return;
        }
        if(Integer.parseInt(alarmeHoraTextField.getText()) > 23 | Integer.parseInt(alarmeMinTextField.getText())>59){
            JOptionPane.showMessageDialog(null,"Valor invalido","Alarme",JOptionPane.INFORMATION_MESSAGE);
            alarmeHoraTextField.setText("");
            alarmeMinTextField.setText("");
            return;
        }
        String hora="";
        String min="";
        if (Integer.parseInt(alarmeHoraTextField.getText())<=9 ){
           hora= "0";
        }
       if (Integer.parseInt(alarmeMinTextField.getText())<=9){
           min= "0";
       }
       String formatHora = alarmeHoraTextField.getText();
       formatHora = Integer.valueOf(formatHora).toString();
       String formatMin = alarmeMinTextField.getText();
       formatMin = Integer.valueOf(formatMin).toString();

       alarmesListView.getItems().addAll(hora+formatHora+":"+min+formatMin);
       alarmeHoraTextField.setText("");
       alarmeMinTextField.setText("");

   }
   @FXML
   protected void remover(){

       alarmesListView.getItems().remove(alarmesListView.getFocusModel().getFocusedIndex());
   }

   public void dispararAlarme(){

       Thread disparar = new Thread(() -> {
           try {
               Thread.sleep(1000);
               String a2 = horaLabel.getText()+":"+minutosLabel.getText();
               for (Object a :alarmesListView.getItems()){

                   if (Objects.equals(a.toString(), a2)){
                       java.awt.Toolkit.getDefaultToolkit().beep();

                   }
               }
               dispararAlarme();

           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }

       });
       disparar.start();
   }

   }



