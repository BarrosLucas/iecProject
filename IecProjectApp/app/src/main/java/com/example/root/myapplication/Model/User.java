package com.example.root.myapplication.Model;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User(int id, String firstName, String lastName, String username, String password){
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
    }
    public User(){}

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
