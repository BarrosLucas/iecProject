package com.example.root.myapplication.view;

import com.example.root.myapplication.Model.Message;
import com.example.root.myapplication.Model.User;

import java.util.ArrayList;

public class Test {
    public static ArrayList<Message> messageArrayList;
    public static ArrayList<User> users;
    public static User userlogin;

    public Test(){
        completeUsers();
        completeMessages();
        generateUserLogin();
    }

    public static void completeUsers(){
        users = new ArrayList<User>();
        for(int i = 0; i < 50; i++){
            users.add(new User(i,"user",""+i,"username"+i,"password"+i));
        }
    }
    public static void completeMessages(){
        messageArrayList = new ArrayList<Message>();
        for(int i = 0; i < users.size();i++){
            for(int j = 0; j < users.size();j++){
                messageArrayList.add(new Message(i,users.get(i),users.get(j),"message "+i,"00-00-0000"));
            }
        }
    }
    public static void generateUserLogin(){
        userlogin = users.get(8);
    }
}
