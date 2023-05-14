package com.example.csc1004javaprojectchatroom;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class client {

    @FXML
    private VBox vbox;

    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private Socket socket;
    private ClientController clientController;
    public client client1;


    public client(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            close(socket, bufferedWriter, bufferedReader);
        }
    }

    //username stored

    //send message to buffered zone.
    public void sendMessage(String msgToSend) {
        try {
               // System.out.println("msg is received.");
                bufferedWriter.write(msgToSend);
                bufferedWriter.write("\n");
                bufferedWriter.flush();
               // System.out.println("already in the buffered zone");
//            }
        } catch (IOException e) {
            close(socket, bufferedWriter, bufferedReader);
        }
    }

    public void close(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
