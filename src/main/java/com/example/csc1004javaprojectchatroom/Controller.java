package com.example.csc1004javaprojectchatroom;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller{
    @FXML
    public Button button;

    @FXML
    private TextField textfield_Username;
    @FXML
    private TextField textfield_Password;

    @FXML
    public void buttonHandler(){
        JDBC.comparison (textfield_Username.getText(), textfield_Password.getText());

    }


}
