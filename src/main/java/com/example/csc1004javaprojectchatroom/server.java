package com.example.csc1004javaprojectchatroom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server{
    private ServerSocket serverSocket;

    public server(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
    }

    public void run() {
        try {
            //int c = 1;
            while (!serverSocket.isClosed()) {
                //System.out.println(c);
                Socket socket = serverSocket.accept();
                System.out.println("A client has connected.");
                TransferStation transfer = new TransferStation(socket);
                Thread thread = new Thread(transfer);
                thread.start();
                //c++;
            }
        } catch (IOException e) {
        }
    }

    public void close(ServerSocket serverSocket){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(9875);
            server server1 = new server(serverSocket);
            server1.run();
        }catch(IOException e){
        }
    }

}
