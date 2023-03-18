/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NgTnLoi
 */
import com.formdev.flatlaf.FlatDarkLaf;
import java.util.ArrayList;
import view.LoginFrame;
import javax.swing.*;
import controller.*;
import repository.*;
import model.*;
        

public class App {
    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(new FlatDarkLaf());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//      
//        SwingUtilities.invokeLater(LoginFrame::new);

        Repository t = new UserRepository();
//        
//        ArrayList<User> list = t.find();
//        for (User user : list) {
//            System.out.println(user.toString());
//        }

//        System.out.println(Login.LoginAuthorization("ngotanloi0709", "loi@23"));
//        System.out.println(Login.Register("loi_vjp_pr0", "1238412321", false, "Dau nanh", "judianchan@gmail.com", "0528050114"));
//        System.out.println(Login.Register("ngotanloi0709", "loi@2003", true, "Ngo Tan Loi", "ngotanloi0709@gmail.com", "0388007914"));
//        
        Repository m = new MessageRepository();
        m.create(new Message("ngotanloi0709", "Hello World"));
        m.create(new Message("ngotanloi0709", "Bye World"));
        m.create(new Message("ngotanloi0709", "Hello World Again"));
        
        ArrayList <Message> lst = m.find();
        for (Message message : lst) {
            System.out.println(message.messageShow() + " " + message.getId());
        }
//        System.out.println(m.remove());
    }
}
