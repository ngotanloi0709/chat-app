package model;

import java.time.LocalDateTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NgTnLoi
 */


public class User extends Model {
    private String username = "Not Set";
    private String password = "Not Set";
    private boolean role = false;
    private String name = "Not Set";
    private String email = "Not Set";
    private String phoneNumber = "Not Set";
    
    public User() {
    }
    
    public User(String username, String password, boolean role, String name, String email, String phoneNumber, String date_created) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.date_created = date_created;
    }
    
        public User(String username, String password, boolean role, String name, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }    
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(String username, String password, boolean role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean getRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "'" + username + "','" + password + "'," + role + ",'" + name
                + "','" + email + "','" + phoneNumber + "','" + date_created + "'";
    }
}

