package com.example.root.myapplication.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.root.myapplication.Model.Message;
import com.example.root.myapplication.R;

import java.util.ArrayList;

public class SimplesAdapterTalk extends BaseAdapter {

    private Context context;
    private ArrayList<Message> messages;

    public SimplesAdapterTalk(Context context){
        super();
        this.context = context;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
    public void setMessages(ArrayList<Message> messages){
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messages.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = messages.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_simples_talk,parent,false);
        TextView sender = (TextView) view.findViewById(R.id.sender);
        TextView text = (TextView) view.findViewById(R.id.text);

        if(message.getSender().getUsername().equals(Test.userlogin.getUsername())){
            sender.setText("EU: ");
        }else{
            sender.setText(message.getSender().getFirstName().toUpperCase()+" "+message.getSender().getLastName().toUpperCase()+": ");
        }
        /*if(message.getRecipient().getUsername().equals(LoginDao.loginDAO.getUsername())){
            sender.setText("EU: ");
        }else{
            sender.setText(message.getSender().getFirstName().toUpperCase()+" "+message.getSender().getLastName().toUpperCase()+": ");
        }*/
        text.setText(message.getMessage());
        return view;
    }
}
