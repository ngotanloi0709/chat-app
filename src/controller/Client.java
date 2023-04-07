package controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;
import model.*;
import repository.*;

public class Client {
    public ArrayBlockingQueue blockingQueue_send = new ArrayBlockingQueue<Message>(100);
    public ArrayBlockingQueue blockingQueue_receive = new ArrayBlockingQueue<String>(100);
    public Socket socket;
    public String username;
    public int client_role = 0;
    public BufferedReader bufferedReader;
    public BufferedWriter bufferedWriter;
    
    public Client(int client_role) {
        try {
            this.client_role = client_role;
            findServer();
        } catch (Exception e) {
        }
    }
    
    public Client(String username) {
        try {
            this.client_role = 0;
            this.username = username;
            findServer();
        } catch (Exception e) {
            close(socket, bufferedReader,  bufferedWriter);
        }
    }
    
    public void findServer() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        if (socket == null || socket.isClosed()) {
                            System.out.println("Try to find Server");
                            
                            close(socket, bufferedReader,  bufferedWriter);
                            socket = null;
                            
                            if (client_role == 0) {
                                socket = new Socket("localhost" , 1111);
                                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                receiveMessage();
                                sendMessage();
                            } else if (client_role == 1) {
                                socket = new Socket("localhost" , 2222);
                            } else if (client_role == 2) {
                                socket = new Socket("localhost" , 3333);
                            }
                        } else if (client_role == 0) {
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
    
    public void sendMessage() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    bufferedWriter.write(username);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    Repository repository = new MessageRepository();

                    while (socket != null) {    
                        Message message = (Message) blockingQueue_send.take();
                        message.setContent(AESEncryptor.encrypt(message.getContent(), message.getKey()));
                        repository.create(message);

                        bufferedWriter.write(message.messageEncryptShow());
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

    public void receiveMessage() {
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
    
    public boolean sendFile(File file) {
        try {
            // Important
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
            
            OutputStream outputStream = socket.getOutputStream();
            
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(file.getName());
            
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024*16];
            int bytesRead = 0;
            
            while ((bytesRead = fileInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
            
            outputStream.flush();
            fileInputStream.close();
            
            return true;
        } catch (Exception e) {
            close(socket, bufferedReader, bufferedWriter);
            return false;
        }
    }
    
    public void receiveFile(String destinationFilePath, String fileName) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    // Important
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                    
                    OutputStream outputStream = socket.getOutputStream();
                    
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                    dataOutputStream.writeUTF(fileName);
                    outputStream.flush();
                    
                    InputStream inputStream = socket.getInputStream();
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(destinationFilePath + File.separator + fileName));
                    byte[] buffer = new byte[1024*16];
                    int bytesRead = 0;

                    while ((bytesRead = inputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                    
                    close(socket, bufferedReader, bufferedWriter);
                } catch (Exception e) {
                    e.printStackTrace();
                    close(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }
    
    public static void close(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}