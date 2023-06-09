package controller;

import java.util.ArrayList;
import repository.*;
import model.*;

public class MessageController {
    private static Repository message_repository = new MessageRepository();
    
    public static ArrayList <String> getMessages() {
        ArrayList <String> result = new ArrayList<>();
        
        try {
            ArrayList <Message> data =  message_repository.find();
            
            for (Message message : data) {
                message.setContent(AESEncryptor.decrypt(message.getContent(), message.getKey()));
            }
            
            for (Message message : data) {
                result.add(message.messageShow());
            }
        } catch (Exception e) {
        }
        
        return result;
    }
    
    
    public static boolean deleteMessages() {
        return message_repository.remove();
    }
    
    public static String messageDecrypt(String message) {
        String[] parts = message.split("---");
        
        if (parts.length == 4) {
            String content = AESEncryptor.decrypt(parts[2], parts[3]);
            return parts[0] + " " + parts[1] + " " + AESEncryptor.decrypt(parts[2], parts[3]) + "[" + content.length() + "]";
        } else if (parts.length == 3) {
            String content = AESEncryptor.decrypt(parts[2], "");
            return parts[0] + " " + parts[1] + " " + content + " [" + content.length() + "]";
        } else {
            return message;
        }
    }
}
