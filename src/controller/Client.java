package controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;
import model.*;
import repository.*;

public class Client implements Runnable {
    public ArrayBlockingQueue blockingQueue_send = new ArrayBlockingQueue<Message>(100);
    public ArrayBlockingQueue blockingQueue_receive = new ArrayBlockingQueue<String>(100);
    public Socket socket;
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
            findServer();
        } catch (Exception e) {
            e.printStackTrace();
            close(socket, bufferedReader,  bufferedWriter);
        }
    }
    
    public void findServer() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        if (socket == null || socket.isClosed()) {

                            socket = null;
                            close(socket, bufferedReader,  bufferedWriter);
                            System.out.println("Try to find Server");
                            socket = new Socket("localhost" , 1234);
                            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            receive();
                            send();
                        } else {
                            System.out.println("Connected to a Server");
                        }
                    } catch (UnknownHostException e) {
                        close(socket, bufferedReader,  bufferedWriter);
                    } catch (IOException e) {
                        close(socket, bufferedReader,  bufferedWriter);
                    } 
                    
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
    public void send() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    bufferedWriter.write(username);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    Repository repository = new MessageRepository();

                    while (socket != null) {    
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
        }).start();
    }

    public void receive() {
        new Thread(new Runnable() {
            public void run() {
                while (socket != null) {
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