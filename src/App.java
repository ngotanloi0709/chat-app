import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import view.*;
        
public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            SwingUtilities.invokeLater(LoginFrame::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
