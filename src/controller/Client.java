package controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.net.Socket;
import java.io.*;
import model.*;
import repository.*;

public class Client implements Runnable {
    public ArrayBlockingQueue blockingQueue_send = new ArrayBlockingQueue<Message>(100);
    public ArrayBlockingQueue blockingQueue_receive = new ArrayBlockingQueue<String>(100);
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public String username;

    public Client(String username) {
        try {
            this.username = username;
            new Thread(this).start();
        } catch (Exception e) {
            close(socket, bufferedReader,  bufferedWriter);
        }
    }
    
    public void run() {
        try {
            this.socket = new Socket("localhost" , 1234);
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            receive();
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void send() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
            Repository repository = new MessageRepository();
            
            while (socket.isConnected()) {    
                Message message = (Message) blockingQueue_send.take();
                repository.create(message);
                
                bufferedWriter.write(message.messageShow());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            close(socket, bufferedReader,  bufferedWriter);
        } catch (InterruptedException e) {
            close(socket, bufferedReader,  bufferedWriter);
        }
    }

    public void receive() {
        new Thread(new Runnable() {
            public void run() {
                while (socket.isConnected()) {
                    try {
                        blockingQueue_receive.put(bufferedReader.readLine());
                    } catch (IOException e) {
                        close(socket, bufferedReader,  bufferedWriter);
                    } catch (InterruptedException e) {
                        close(socket, bufferedReader,  bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public static void close(Socket socket , BufferedReader bufferedReader , BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}