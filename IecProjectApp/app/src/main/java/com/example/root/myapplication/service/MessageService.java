package com.example.root.myapplication.service;

import com.example.root.myapplication.Model.Message;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageService {
    //Get messages user
    @GET("message/{username}")
    Call<ArrayList<Message>> getMessagesUser(@Path("username") String username);

    //Get messages into dialog
    @GET("message/{sender}/{recipient}")
    Call<ArrayList<Message>> getMessagesDialog(@Path("sender") String usernameSender, @Path("recipient") String usernameRecipient);

    //Create message
    @POST("message/send")
    Call<Message> sendMessage(@Body Message message);

    //GET last messages of user
    @GET("messages/last/{username}")
    Call<ArrayList<Message>> getLastMessagesUser(@Path("username") String username);
}
