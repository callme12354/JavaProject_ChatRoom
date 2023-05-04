package com.example.csc1004javaprojectchatroom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;

public class login_Controller {
    @FXML
    private Button button_Login;
    @FXML
    private Button button_Signup;

    @FXML
    private TextField textfield_Username;
    @FXML
    private TextField textfield_Password;
    @FXML
    private TextField signup_textfield_Username;
    @FXML
    private  TextField signup_textfield_Password;
    @FXML
    private  TextField signup_textfield_Age;
    @FXML
    private  TextField signup_textfield_Gender;
    @FXML
    private  TextField signup_textfield_Address;
    @FXML
    private  Button signup_button_signup;
    @FXML
    private Label label_congrats;
    @FXML
    private Button button_clickback;
    private BufferedWriter bufferedWriter;


    @FXML
    public void LoginHandler(ActionEvent event){
        //System.out.println("I'm login button.");
       JDBC.comparison (event, textfield_Username.getText(), textfield_Password.getText());
       JDBC.storeUsernameInBuffer(event,textfield_Username.getText(), textfield_Password.getText());
    }
    @FXML
    public void SignupHandler_homepage(ActionEvent event){
        //System.out.println("I'm signup button.");
        JDBC.changeScene(event,"signup.fxml",null);
    }
    @FXML
    public void SignupHandler(ActionEvent event) {
        JDBC.storeNewUser(signup_textfield_Username.getText(), signup_textfield_Password.getText(), signup_textfield_Age.getText(), signup_textfield_Gender.getText(), signup_textfield_Address.getText(),event);
        //JDBC.changeScene(event, "congrats.fxml", null);
    }

        @FXML
        public void ClickBackHandler (ActionEvent event){
            //System.out.println("I'm clickback button.");
            JDBC.changeScene(event, "login.fxml", null);
        }
}

