package com.example.csc1004javaprojectchatroom;

//read username, and store it into an arraylist
//read user input, and broadcast it to the others
//if exception occurs, close the current socket and the corresponding items

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TransferStation implements Runnable {

    //"The static keyword is used to construct methods that will exist regardless of whether or not any instances of the class are generated."
    //Here we must use a static array list, for we need to add the new elements in the same array lists where the previous ones stored.
    public static ArrayList<TransferStation> Users = new ArrayList<>();

    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String username;

    //broadcast when a new user enter
    public TransferStation(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = bufferedReader.readLine();
            Users.add(this);
            // System.out.println(Users.size());
            broadcast("NOTICE: " + username +" has entered the group chat!");

        } catch (IOException e) {
            close(socket, bufferedWriter, bufferedReader);
        }
    }

    //use the thread to keep listening the user input
    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                String str = bufferedReader.readLine();
                //System.out.println("reader received");
                broadcast(str);
            } catch (IOException e) {
                close(socket, bufferedWriter, bufferedReader);
                break;
            }
        }
    }

    //broadcast user input to the others
    public void broadcast(String str){
        for(TransferStation usernameStored : Users) {
            try {
                if (!usernameStored.username.equals(username)) {
                    usernameStored.bufferedWriter.write(username + ": " + str);
                    usernameStored.bufferedWriter.write("\n");
                    usernameStored.bufferedWriter.flush();
                    //System.out.println("passed to broadcast");
                }
            } catch (IOException e) {
                close(socket, bufferedWriter, bufferedReader);
            }
        }
    }

    public void leave(){
        Users.remove(this);
        broadcast("NOTICE: " + this.username + " has left the group chat!");
    }


    //close if something goes wrong
    public void close(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader){
        leave();
        try{
            if(socket!= null){
                socket.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(bufferedReader != null){
                bufferedReader.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}














