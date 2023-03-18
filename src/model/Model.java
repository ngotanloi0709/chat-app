/**
 *
 * @author NgTnLoi
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Model {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    protected String date_created = LocalDateTime.now().format(formatter);
    
    public String getDateCreated() {
        return this.date_created;
    }
    
    public void setDateCreated(String date_created) {
        this.date_created = date_created;
    }
}
