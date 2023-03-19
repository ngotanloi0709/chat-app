package controller;

import java.util.ArrayList;
import java.net.Socket;
import java.io.*;
import model.Message;

public class ClientHandler implements Runnable {
    static ArrayList <ClientHandler> clientHandlers = new ArrayList <> ();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = bufferedReader.readLine();
            ClientHandler.clientHandlers.add(this);
            broadcastMessage("######### " + username + " has entered the chat!"); 
        } catch (IOException e) {
            close();
        }
    }

    public void run() {
        while (socket.isConnected()) {
            try {
                broadcastMessage(bufferedReader.readLine());
            } catch (IOException e) {
                close();
                break;
            }
        }
    }

    public void broadcastMessage(String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.username.equals(username)) {
                    clientHandler.bufferedWriter.write(message);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (Exception e) {
                close();
            }
        }
    }

    public void close() {
        clientHandlers.remove(this);
        broadcastMessage("######### " + username + "has left the chat!");
        Client.close(socket, bufferedReader,  bufferedWriter);
    }
}
