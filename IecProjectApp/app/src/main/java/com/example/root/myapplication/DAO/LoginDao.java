package com.example.root.myapplication.DAO;

import com.example.root.myapplication.Model.Login;
import com.example.root.myapplication.Model.Message;
import com.example.root.myapplication.Model.User;

import java.util.ArrayList;

public class LoginDao {
    public static Login loginDAO;
    public static boolean savedLogin = false;
    public static ArrayList<User> users;
    public static ArrayList<Message> messages;
}
