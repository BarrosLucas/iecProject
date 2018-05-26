package com.example.root.myapplication.service;

import com.example.root.myapplication.Model.Message;
import java.util.ArrayList;
import retrofit2.Call;
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
    @POST("message/send/{sender}/{recipient}")
    Call<Message> sendMessage(@Path("sender") String usernameSender, @Path("recipient") String usernameRecipient);
}
