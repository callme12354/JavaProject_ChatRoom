package com.example.csc1004javaprojectchatroom;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginSuccessfulController {
    @FXML
    private Button LoginSuccessfulButton;

    @FXML
    public static void successfulHandler(){
        JDBC.changeScene("test.fxml");
    }
}
