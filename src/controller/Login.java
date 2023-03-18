/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author NgTnLoi
 */
import repository.UserRepository;
import repository.Repository;
import model.*;

public class Login {
    public static boolean LoginAuthorization (String username, String password) {
        try {
            Repository user_repository = new UserRepository();
            User data = (User) user_repository.findByKey(username);
            
            if ((data.getUsername().compareTo(username)) == 0 &&
                (data.getPassword().compareTo(password)) == 0) {
                return true;
            }
            
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean Register(String username ,String password, boolean role, String name, String email, String phoneNumber) {
        try {
            Repository user_repository = new UserRepository();
        
            return user_repository.create(new User(username, password, role, name, email, phoneNumber));
        } catch (Exception e) {
            return false;
        }
    }
}
