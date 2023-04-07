package controller;

import java.util.ArrayList;
import java.net.Socket;
import java.io.*;

public class ClientController implements Runnable {
    public static ArrayList <ClientController> clientAcceptedList = new ArrayList <> ();
    private Socket socket;
    public String username;
    public int client_role = 0;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    
    public ClientController(Socket socket, int client_role) {
        try {
            this.socket = socket;
            this.client_role = client_role;
            ClientController.clientAcceptedList.add(this);
        } catch (Exception e) {
            close();
        }
    }

    public void run() {
        if (client_role == 0) {
            try {
                this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.username = bufferedReader.readLine();
                broadcastMessage("> ######### " + username + " has entered the chat!");
                
                while (socket.isConnected()) {
                    try {
                        broadcastMessage(bufferedReader.readLine());
                    } catch (IOException e) {
                        close();
                        break;
                    }
                }
            } catch (Exception e) {
                close();
            }
        } else if (client_role == 1) {
            receiveFile("src/storage");
        } else if (client_role == 2) {
            sendFile("src/storage");
        }
    }

    public void broadcastMessage(String message) {
        for (ClientController clientHandler : clientAcceptedList) {
            try {
                if (!clientHandler.username.equals(username)) {
                    clientHandler.bufferedWriter.write(message);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (Exception e) {
                close();
                break;
            }
        }
    }
    
    public void receiveFile(String destinationFilePath) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    InputStream inputStream = socket.getInputStream();
                    DataInputStream dataInputStream = new DataInputStream(inputStream);
                    String fileName = dataInputStream.readUTF();

                    FileOutputStream fileOutputStream = new FileOutputStream(new File(destinationFilePath + File.separator + fileName));
                    byte[] buffer = new byte[1024*16];
                    int bytesRead = 0;

                    while ((bytesRead = inputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }

                    close();
                } catch (Exception e) {
                    close();
                } 
            }
        }).start();
    }

    public void sendFile(String sourceFilePath) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    InputStream inputStream = socket.getInputStream();
                    DataInputStream dataInputStream = new DataInputStream(inputStream);
                    String fileName = dataInputStream.readUTF();
                    File file = new File(sourceFilePath + File.separator + fileName);
                    
                    OutputStream outputStream = socket.getOutputStream();
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] buffer = new byte[1024*16];
                    int bytesRead = 0;

                    while ((bytesRead = fileInputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    
                    outputStream.flush();
                } catch (Exception e) {
                    close();
                }
            }
        }).start();
    }
    
    public void close() {        
        Client.close(socket, bufferedReader,  bufferedWriter);
        clientAcceptedList.remove(this);
    }
}
