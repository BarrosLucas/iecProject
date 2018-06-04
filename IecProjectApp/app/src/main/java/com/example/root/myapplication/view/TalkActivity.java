package com.example.root.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.root.myapplication.DAO.LoginDao;
import com.example.root.myapplication.Model.Login;
import com.example.root.myapplication.Model.Message;
import com.example.root.myapplication.Model.User;
import com.example.root.myapplication.R;
import com.example.root.myapplication.controller.SimplesAdapterTalk;
import com.example.root.myapplication.controller.Test;

import java.util.ArrayList;

public class TalkActivity extends AppCompatActivity {

    public static boolean active = false;
    EditText editText;
    private static ListView listView;
    public static ArrayList<Message> messagesTalk = new ArrayList<Message>();
    private static SimplesAdapterTalk simplesAdapterTalk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        generateTalk();

        active = true;

        editText = (EditText) findViewById(R.id.msgedit);
        listView = (ListView) findViewById(R.id.chat);
        simplesAdapterTalk = new SimplesAdapterTalk(this);
        simplesAdapterTalk.setMessages(messagesTalk);
        listView.setAdapter(simplesAdapterTalk);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/

    }
    public void generateTalk(){
        for(Message message: LoginDao.messages){
            if(message.getSender().getUsername().equals(LoginDao.loginDAO.getUsername()) && message.getRecipient().getUsername().equals(InitialScreen.userTalk.getUsername())){
                messagesTalk.add(message);
            }
            if(message.getSender().getUsername().equals(InitialScreen.userTalk.getUsername()) && message.getRecipient().getUsername().equals(LoginDao.loginDAO.getUsername())){
                messagesTalk.add(message);
            }
        }
    }
    public void sendMessage(View view) {
        if (!editText.getText().toString().isEmpty()) {
            Message message = new Message();
            User me = null;
            for (User user : LoginDao.users){
                /*if (user.getUsername().equals(LoginDao.loginDAO.getUsername())) {
                    me = user;
                    break;
                }*/
                if (user.getUsername().equals(Test.userlogin.getUsername())) {
                    me = user;
                    break;
                }
            }
            message.setSender(me);
            message.setRecipient(InitialScreen.userTalk);
            message.setMessage(editText.getText().toString());
            messagesTalk.add(message);
            simplesAdapterTalk.setMessages(InitialScreen.messages);
            listView.setAdapter(null);
            listView.setAdapter(simplesAdapterTalk);
            editText.setText("");



        /*Call<Message> call = new RetrofitConfig().getMessageService().sendMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()){
                    LoginDao.messages.add(response.body());
                    updateMessage(response.body());
                }else{
                    Toast.makeText(getBaseContext(),"Ocorreu alguma falha",Toast.LENGTH_SHORT).show();
                    Log.e("Fail",response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Falha na comunicação",Toast.LENGTH_SHORT);
                t.printStackTrace();
            }
        });*/
        }
    }
    public void sendMessageFromDevice(String msg){
        Message message = new Message();
        User me = null;
        for (User user : LoginDao.users){
                /*if (user.getUsername().equals(LoginDao.loginDAO.getUsername())) {
                    me = user;
                    break;
                }*/
            if (user.getUsername().equals(Test.userlogin.getUsername())) {
                me = user;
                break;
            }
        }
        message.setSender(me);
        message.setRecipient(InitialScreen.userTalk);
        message.setMessage(msg);
        InitialScreen.messages.add(message);
        simplesAdapterTalk.setMessages(InitialScreen.messages);
        listView.setAdapter(null);
        listView.setAdapter(simplesAdapterTalk);
        editText.setText("");
        /*Call<Message> call = new RetrofitConfig().getMessageService().sendMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()){
                    LoginDao.messages.add(response.body());
                    updateMessage(response.body());
                }else{
                    Toast.makeText(getBaseContext(),"Ocorreu alguma falha",Toast.LENGTH_SHORT).show();
                    Log.e("Fail",response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Falha na comunicação",Toast.LENGTH_SHORT);
                t.printStackTrace();
            }
        });*/

    }

    public static void updateMessage(Message msg){
        if(!messagesTalk.contains(msg)){
            messagesTalk.add(msg);
            simplesAdapterTalk.setMessages(InitialScreen.messages);
            listView.setAdapter(null);
            listView.setAdapter(simplesAdapterTalk);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        active = false;
        messagesTalk = null;
        simplesAdapterTalk = null;
        listView = null;
    }
}
