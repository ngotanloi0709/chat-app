import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import view.*;
        
public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(LoginFrame::new);
//        SwingUtilities.invokeLater(() -> new MainFrame(new Client("ngotanloi0709")));

    }
}
