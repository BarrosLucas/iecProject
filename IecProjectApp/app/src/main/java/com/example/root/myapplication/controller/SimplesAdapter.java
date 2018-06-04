package com.example.root.myapplication.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.root.myapplication.Model.User;
import com.example.root.myapplication.R;

import java.util.ArrayList;

public class SimplesAdapter extends BaseAdapter {
    private ArrayList<User> user;
    private Context context;
    public SimplesAdapter(Context context){
        super();
        this.context = context;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }

    @Override
    public int getCount() {
        return user.size();
    }

    @Override
    public Object getItem(int position) {
        return user.get(position);
    }

    @Override
    public long getItemId(int position) {
        return user.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getUser().get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_simples,parent,false);
        TextView fullNameTextView = (TextView) view.findViewById(R.id.fullname_view);
        TextView usernameTextView = (TextView) view.findViewById(R.id.username_view);
        fullNameTextView.setText(user.getFirstName()+" "+user.getLastName());
        usernameTextView.setText("@"+user.getUsername());
        return view;
    }
}
