package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SwingUtilities;
import view.*;

public class Server implements Runnable {
    private ServerSocket serverSocket = new ServerSocket(1234);

    public Server() throws IOException {
        new Thread(this).start();
        System.out.println("Server is on!");
        SwingUtilities.invokeLater(() -> new MainFrame(new Client("Server")));
    }

    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}