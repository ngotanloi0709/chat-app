/**
 *
 * @author NgTnLoi
 */
package repository;
import core.Database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

public class MessageRepository extends Repository <Message>{
    public MessageRepository() {
        this.table = "messages";
        this.order = "username,content,date_created";
        this.key = "id";

    }
    
    @Override
    public Message createObject(ResultSet data) throws SQLException {
        try {
            return new Message(
                data.getInt("id"),
                data.getString("username"),
                data.getString("content"),
                data.getString("date_created")
            );
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
