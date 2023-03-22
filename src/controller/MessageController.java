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
                result.add(message.messageShow());
            }
        } catch (Exception e) {
        }
        
        return result;
    }
    
    
    public static boolean deleteMessages() {
        return message_repository.remove();
    }
}
