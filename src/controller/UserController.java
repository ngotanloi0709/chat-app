package controller;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import repository.*;
import controller.*;
import model.*;

public class UserController {
    private static Repository user_repository = new UserRepository();
    
    public static DefaultTableModel getUsersTable() {
        DefaultTableModel result = new DefaultTableModel();
        String[] columnNames = {"username", "password", "role", "name", "email", "phone_number", "date_created"};
        
        try {
            Repository repository = new UserRepository();
            ArrayList <User> data = repository.find();
            result.setColumnIdentifiers(columnNames);
            
            for (User user : data) {
                Object[] row = new Object[]{
                    user.getUsername(),
                    user.getPassword(),
                    user.getRole(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getDateCreated()
                };
            
                result.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static User LoginAuthorization (String username, String password) {
        try {
            User user = (User) user_repository.findByKey(username);
            
            if ((user.getUsername().compareTo(username)) == 0 &&
                (user.getPassword().compareTo(password)) == 0) {
                return user;
            }
            
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean createUser(String username ,String password, boolean role, String name, String email, String phoneNumber) {
        try {
            Repository user_repository = new UserRepository();
        
            return user_repository.create(new User(username, password, role, name, email, phoneNumber));
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean removeUser(String username) {
        return user_repository.removeByKey(username);
    }
    
    public static boolean isExist(String username) {
            if (user_repository.findByKey(username) != null) {
                return true;
            }

            return false;
    }
    
    public static boolean isOnline(String username) {
        for (ClientController i : ClientController.clientAcceptedList) {
            if (i.username.equals(username)) {
                return true;
            }
        }
        
        return false;
    }
}
