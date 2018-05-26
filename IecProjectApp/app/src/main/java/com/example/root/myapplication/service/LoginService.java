package com.example.root.myapplication.service;

import com.example.root.myapplication.Model.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    //Create User
    @POST("login")
    Call<Login> login(@Body Login login);
}
