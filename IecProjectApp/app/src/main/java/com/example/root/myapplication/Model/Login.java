package com.example.root.myapplication.Model;

import android.util.Log;

public class Login {
    private String username;
    private String password;
    private int idLogin;

    public Login(String username, String password, int idLogin){
        setPassword(password);
        setUsername(username);
        setIdLogin(idLogin);
    }
    public Login(){}

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public int getIdLogin(){
        return this.idLogin;
    }

    public void setIdLogin(int idLogin){
        this.idLogin = idLogin;
    }
}
