package com.example.root.myapplication.service;

import com.example.root.myapplication.Model.User;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    //Return a user
    @GET("user/{username}")
    Call<User> getUser(@Path("username") String username);

    //Return users
    @GET("user")
    Call<ArrayList<User>> getUsers();

    //Create User
    @POST("user/new")
    Call<User> createPiecePost(@Body User user);


}
