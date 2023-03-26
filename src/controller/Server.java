package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SwingUtilities;
import view.*;

public class Server {
    private static ServerSocket chatServerSocket;
    private static ServerSocket fileReceiverServerSocket;
    private static ServerSocket fileSenderServerSocket;

    public Server() throws IOException {
        chatServerSocket = new ServerSocket(1111);
        fileReceiverServerSocket = new ServerSocket(2222);
        fileSenderServerSocket = new ServerSocket(3333);
        
        ChatServerListening();
        FileReceiverServerListening();
        FileSenderServerListening();
        
        System.out.println("Server is on!");
        SwingUtilities.invokeLater(() -> new MainFrame(new Client("Server"), true));
    }
    
    public void ChatServerListening() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (!chatServerSocket.isClosed()) {
                        Socket socket = chatServerSocket.accept();
                        System.out.println("A new client has connected");
                        new Thread(new ClientController(socket, 0)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    public void FileReceiverServerListening() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (!fileReceiverServerSocket.isClosed()) {
                        Socket socket = fileReceiverServerSocket.accept();
                        System.out.println("A new file receiver client has connected to File Server");
                        new Thread(new ClientController(socket, 1)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    public void FileSenderServerListening() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (!fileSenderServerSocket.isClosed()) {
                        Socket socket = fileSenderServerSocket.accept();
                        System.out.println("A new file sender client has connected to File Server");
                        new Thread(new ClientController(socket, 2)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}