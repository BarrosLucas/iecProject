package com.example.root.myapplication.retrofit;

import com.example.root.myapplication.service.LoginService;
import com.example.root.myapplication.service.MessageService;
import com.example.root.myapplication.service.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.matheuscastiglioni.com.br/ws/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public LoginService getLoginService() {
        return this.retrofit.create(LoginService.class);
    }

    public MessageService getMessageService(){
        return this.retrofit.create(MessageService.class);
    }

    public UserService getUserService(){
        return this.retrofit.create(UserService.class);
    }

}
