package model;

public class Message extends Model {
    private int id = -1;
    private String username = "";
    private String content = "";
    private String key = "";

    public Message() {
    }

    public Message(int id, String username, String content, String key, String date_created) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.key = key;
        this.date_created = date_created;
    }
    
    public Message(String username, String content, String key) {
        this.username = username;
        this.content = content;
        this.key = key;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String messageShow() {
        return "[" + date_created + "] " + username + ": " + content;
    }
    
    public String messageEncryptShow() {
        return "[" + date_created + "]---" + username + ":---" + content + "---" + key;
    }
    
    @Override
    public String toString() {
        return "'" + username + "','" + content + "','" + this.key + "','" + date_created + "'";
    }
}
