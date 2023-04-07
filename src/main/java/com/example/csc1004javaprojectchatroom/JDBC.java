package com.example.csc1004javaprojectchatroom;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class JDBC {

    public static void comparison(String usernameInput, String passwordInput){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafxtest", "root", "@ZZt9981001");
            preparedStatement = connection.prepareStatement("SELECT password FROM javafxuser WHERE username = ?");
            preparedStatement.setString(1, usernameInput);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                System.out.println("User does not exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User does not exist!");
                alert.show();
            }else{
                while(resultSet.next()){
                    String storedPassword = resultSet.getString("password");
                    if(storedPassword.equals(passwordInput)){
                         changeScene("test.fxml");
                        //System.out.println("match");
                    }else{
                        System.out.println("wrong password");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("You entered a wrong password!");
                        alert.show();
                    }
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void changeScene(String targetFile){
        Parent root = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(JDBC.class.getResource(targetFile));
            root = fxmlLoader.load();
        }catch(IOException e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800,400));
        stage.setTitle("test");
        stage.show();
    }
}


