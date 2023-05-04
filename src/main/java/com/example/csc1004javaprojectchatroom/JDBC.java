package com.example.csc1004javaprojectchatroom;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.sql.*;


public class JDBC {

    public static ActionEvent event;
    private static BufferedWriter bufferedWriter;

    public JDBC(Socket socket){
        try{
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void comparison(ActionEvent event, String usernameInput, String passwordInput){
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
                        changeScene(event,"ChatRoom.fxml",usernameInput);
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

    public static void changeScene(ActionEvent event, String targetFile, String username){
        Parent root = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(JDBC.class.getResource(targetFile));
            root = fxmlLoader.load();
            //The following step is used to change scenes in the same window instead of starting a new window.
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("test");
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void storeUsernameInBuffer(ActionEvent event, String usernameInput, String passwordInput){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafxtest", "root", "@ZZt9981001");
            preparedStatement = connection.prepareStatement("SELECT password FROM javafxuser WHERE username = ?");
            preparedStatement.setString(1, usernameInput);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()){
                while(resultSet.next()){
                    String storedPassword = resultSet.getString("password");
                    if(storedPassword.equals(passwordInput)){
                        try {
                            System.out.println(usernameInput);
                            bufferedWriter.write(usernameInput);
                            bufferedWriter.write("\n");
                            bufferedWriter.flush();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //store new user into dbs
    public static void storeNewUser(String newUsername, String newPassword, String newAge, String newGender, String newAddress,ActionEvent event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        /*
        System.out.println(newUsername);
        System.out.println(newPassword);
        System.out.println(newAge);
        System.out.println(newGender);
        System.out.println(newAddress);
         */

        if (!newUsername.equals("") && !newPassword.equals("")) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafxtest", "root", "@ZZt9981001");
                preparedStatement = connection.prepareStatement("SELECT password FROM javafxuser WHERE username = ?");
                preparedStatement.setString(1, newUsername);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    preparedStatement = connection.prepareStatement("INSERT into javafxuser(username, password, age, gender, address)  " +
                            "VALUES(?,?,?,?,?)");
                    preparedStatement.setString(1, newUsername);
                    preparedStatement.setString(2, newPassword);
                    preparedStatement.setString(3, newAge);
                    preparedStatement.setString(4, newGender);
                    preparedStatement.setString(5, newAddress);
                    preparedStatement.executeUpdate();
                    System.out.println("insert successfully");
                    changeScene(event, "congrats.fxml", null);
                } else {
                    System.out.println("repeated username");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The username already exists. Please enter another one.");
                    alert.show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else{
            if(newUsername.equals("")){
                System.out.println("Didn't input username");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please enter your username.");
                alert.show();
            }
            if(newPassword.equals("")){
                System.out.println("Didn't input password");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please enter your password.");
                alert.show();
            }
        }
    }
}


