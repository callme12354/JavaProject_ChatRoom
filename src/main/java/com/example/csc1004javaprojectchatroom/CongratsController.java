package com.example.csc1004javaprojectchatroom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CongratsController implements Initializable{
    @FXML
    private Label label_congrats;

    @FXML
    public void setLabel_congrats (){
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabel_congrats();
    }
    @FXML
    public void ClickBackHandler(ActionEvent event){
        //System.out.println("I'm clickback button.");
        JDBC.changeScene(event,"login.fxml", null);
    }
}