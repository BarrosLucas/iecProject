package com.example.root.myapplication.callback;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.Nullable;

import com.example.root.myapplication.DAO.LoginDao;
import com.example.root.myapplication.Model.Login;
import com.example.root.myapplication.Model.Message;
import com.example.root.myapplication.retrofit.RetrofitConfig;
import com.example.root.myapplication.view.InitialScreen;
import com.example.root.myapplication.view.TalkActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackMessage extends IntentService {
    public CallbackMessage() {
        super("CallbackMessage");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Call<ArrayList<Message>> call = new RetrofitConfig().getMessageService().getLastMessagesUser(LoginDao.loginDAO.getUsername());
        call.enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                if (response.isSuccessful()) {

                    for(Message message: response.body()){
                        if(!LoginDao.messages.contains(message)){
                            LoginDao.messages.add(message);
                            Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            long milliseconds = 30;
                            rr.vibrate(30);
                            if(TalkActivity.active){
                                TalkActivity.updateMessage(message);
                            }
                        }
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {

            }
        });
        long endTime = System.currentTimeMillis() + 1000;
        while(System.currentTimeMillis() < endTime){
            synchronized (this){
                try{
                    wait(endTime-System.currentTimeMillis());
                }catch (Exception e){

                }
            }
        }
    }
}