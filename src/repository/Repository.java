/**
 *
 * @author NgTnLoi
 */
package repository;

import core.Database;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Repository <T> extends Database {
    public String table;
    public String order;
    public String key;
    public abstract T createObject(ResultSet data) throws SQLException;
    
    public ArrayList <T> find() {
        ArrayList <T> result = new ArrayList();
        
        try {
            ResultSet data = (ResultSet) this.executeQuery("SELECT * FROM " + table).get(1);
            
            while (data.next()) {
                T object = (T) createObject(data);
                result.add((T)object);
            }
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return result;
    }
    
    public T findByKey(String value) {
        try {
            ResultSet data = (ResultSet) this.executeQuery("SELECT * FROM " + table + " WHERE " + key + "='" + value + "'").get(1);
            
            data.next();
            T object = (T) createObject(data);
            
            return object;
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean create(T object) {
        try {
            return this.executeUpdate("INSERT INTO " + table + "("+ order +") VALUES(" + object.toString() +")");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean remove() {
        try {
            return this.executeUpdate("DELETE FROM " + table);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
