package controller;

import java.util.ArrayList;
import java.net.Socket;
import java.io.*;

public class ClientController implements Runnable {
    static ArrayList <ClientController> clientHandlers = new ArrayList <> ();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    String username;

    public ClientController(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = bufferedReader.readLine();
            ClientController.clientHandlers.add(this);
            broadcastMessage("> ######### " + username + " has entered the chat!"); 
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
        for (ClientController clientHandler : clientHandlers) {
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
        broadcastMessage("> ######### " + username + "has left the chat!");
        Client.close(socket, bufferedReader,  bufferedWriter);
    }
}
