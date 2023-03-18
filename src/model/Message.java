/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NgTnLoi
 */
public class Message extends Model {
    private int id = -1;
    private String username = "Unknown";
    private String content = "";

    public Message() {
    }

    public Message(int id, String username, String content, String date_created) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.date_created = date_created;
    }
    
    public Message(String username, String content) {
        this.username = username;
        this.content = content;
        this.date_created = date_created;
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
    
    public String messageShow() {
        return "> [" + date_created + "] " + username + ": " + content;
    }
    
    @Override
    public String toString() {
        return "'" + username + "','" + content + "','" + date_created + "'";
    }
}
