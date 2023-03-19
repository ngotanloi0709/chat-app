package repository;

import core.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

public class UserRepository extends Repository <User> {
    public UserRepository() {
        this.table = "users";
        this.order = "username,password,role,name,email,phone_number,date_created";
        this.key = "username";
    }
    
    @Override
    public User createObject(ResultSet data) throws SQLException {
        try {
            return new User(
                data.getString("username"),
                data.getString("password"),
                data.getBoolean("role"),
                data.getString("name"),
                data.getString("email"),
                data.getString("phone_number"),
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
