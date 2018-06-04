package com.example.root.myapplication.callback;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.Nullable;

import com.example.root.myapplication.DAO.LoginDao;
import com.example.root.myapplication.Model.Login;
import com.example.root.myapplication.Model.Message;
import com.example.root.myapplication.retrofit.RetrofitConfig;
import com.example.root.myapplication.view.TalkActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackEveryMessageUser{
    public static void syncronize(){
        Call<ArrayList<Message>> call = new RetrofitConfig().getMessageService().getMessagesUser(LoginDao.loginDAO.getUsername());
        call.enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                if (response.isSuccessful()) {
                    LoginDao.messages = response.body();
                } else {
                    LoginDao.messages = new ArrayList<>();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {
                LoginDao.messages = new ArrayList<>();
            }
        });
    }
}
