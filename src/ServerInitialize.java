import com.formdev.flatlaf.FlatDarkLaf;
import controller.*;
import java.io.IOException;
import javax.swing.UIManager;

public class ServerInitialize {
    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        new Server();
    }
}
