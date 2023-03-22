package core;

import java.sql.*;
import java.sql.SQLException;
import static java.sql.DriverManager.getConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {
    protected Connection connection;
    
    public Database() {
        try {
            this.connection = getConnection("jdbc:mysql://localhost:3306/chat_app", "root", "");
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public ArrayList executeQuery(String query) {
        ArrayList result = new ArrayList();
        ResultSet data = null;
        
        // Default return data [false, null]         
        result.add(false);
        result.add(data);
                
        try {
            Statement statement = this.connection.createStatement();
            data = statement.executeQuery(query);
            result.set(1, data);
            System.out.println("Query Execution Successful");
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
            result.set(0, true);
            System.out.println("Query Execution Failed");
        }
        
        return result;
    }
    
    public boolean executeUpdate(String query) {
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Update Execution Successful");
            
            return true;
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Update Execution Failed");
            return false;
        }
    }
}
