package com.example.btl_foodapp_2_7.Project.Model;

public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String userRole;


    public User(String name, String username, String password, String userRole){
        this.name = name;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
