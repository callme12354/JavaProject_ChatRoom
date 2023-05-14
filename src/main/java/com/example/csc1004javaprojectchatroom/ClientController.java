package com.example.csc1004javaprojectchatroom;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable{

    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private TextField textfield_Text;
    private client client1;
    private JDBC jdbc;
    private BufferedReader bufferedReader;
    private Socket socket;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            socket = new Socket("localhost", 9875);
            client1 = new client(socket);
            jdbc = new JDBC(socket);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            broadcaster();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void adjustHeight(){
     vbox.heightProperty().addListener(new ChangeListener<Number>() {
         @Override
         public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
             scrollpane.setVvalue((Double) t1);
         }
     });
    }

    // Send message. Show it at right in green.
    @FXML
    public void sendHandler(ActionEvent event){
        adjustHeight();
        String msg = textfield_Text.getText();
        //Show the message in the screen in green.
        if(!msg.isEmpty()) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5, 5, 5, 5));
            Text text = new Text(msg);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setPadding(new Insets(5, 5, 5, 5));
            textFlow.setStyle("-fx-background-color:#a8cfa8");
            hBox.getChildren().add(textFlow);
            vbox.getChildren().add(hBox);
            /*The client will send the message into the buffered zone, and the bufferedReader in transfer station will read it and pass it to the broadcast method.
            In the broadcast method, we'll write the message in to the buffered zone of the rest of the clients.4
            Then, the client in client class will read it.*/
            client1.sendMessage(msg);
            System.out.println("passed");
            textfield_Text.clear();
        }
    }

    //broadcast msg
    //NOTICE:The code is not completed yet. Need to connect to the array list and broadcast the message to the rest.
    @FXML
    public void broadcaster(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (socket.isConnected()) {
                        String msgToBroadcast = bufferedReader.readLine();
                        if (!msgToBroadcast.isEmpty()) {
                            HBox hBox = new HBox();
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 5, 5, 5));
                            Text text = new Text(msgToBroadcast);
                            TextFlow textFlow = new TextFlow(text);
                            textFlow.setPadding(new Insets(5, 5, 5, 5));
                            textFlow.setStyle("-fx-background-color: white");
                            hBox.getChildren().add(textFlow);

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    vbox.getChildren().add(hBox);
                                }
                            });

                        }
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}