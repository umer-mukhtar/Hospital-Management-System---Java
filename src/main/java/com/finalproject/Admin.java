package com.finalproject;
public class Admin {
    public String username;
    public String password;
    private String name;

    public Admin(String username, String password, String name) {
        this.username = username;this.password = password;this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
