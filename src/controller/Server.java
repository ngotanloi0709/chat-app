package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SwingUtilities;
import view.*;

public class Server implements Runnable {
    private static ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(1234);
        new Thread(this).start();
        System.out.println("Server is on!");
        
        SwingUtilities.invokeLater(() -> new MainFrame(new Client("Server"), true));
    }

    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                new Thread(new ClientController(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}