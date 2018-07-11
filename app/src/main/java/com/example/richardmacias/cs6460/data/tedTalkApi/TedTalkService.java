package com.example.richardmacias.cs6460.data.tedTalkApi;

import com.example.richardmacias.cs6460.data.tedTalkApi.models.TedTalkResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TedTalkService {

    @GET("users/{user}/repos")
    Call<TedTalkResponse> getTalks(@Path("IDKYET") String paramter);

    @GET("users/{user}/repos")
    Call<TedTalkResponse> getTalk(@Path("IDKYET") int id);

//    @GET("message")
//    Call<MessageResponse> getMessages();
//
//    @GET("message/{id}")
//    Call<MessageResponse> getMessage(@Path("id") int messageID);
//
//    @POST("message")
//    Call<MessageResponse> createMessage(@Body Message message);
//
//    @DELETE("message/{id}")
//    Call<MessageResponse> deleteMessage(@Path("id") int messageID);
//
//    @PUT("message/{id}")
//    Call<MessageResponse> updateMessage(@Path("id") int messageID,@Body Message message);


}
