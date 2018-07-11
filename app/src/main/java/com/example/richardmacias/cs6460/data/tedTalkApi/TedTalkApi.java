package com.example.richardmacias.cs6460.data.tedTalkApi;

import android.util.Log;

import com.example.richardmacias.cs6460.data.tedTalkApi.models.TedTalk;
import com.example.richardmacias.cs6460.data.tedTalkApi.models.TedTalkResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TedTalkApi {

    public static final String TAG = TedTalkApi.class.getSimpleName();
    public static final String BASE_URL = "idkyet";
    public TedTalkService service;
    private List<TedTalk> tedTalks;
    private RequestListener mListener;

    public interface RequestListener<T> {

        void onSuccess(T response);

        void onFailure();
    }

    public void removeListener(){
        mListener = null;
    }

    public void addListener(RequestListener listener){
        mListener = listener;
    }

    public TedTalkApi(RequestListener listener) {
        mListener = listener;
        GsonBuilder gsonBuilder = new GsonBuilder();
        String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
        gsonBuilder.setDateFormat(ISO_FORMAT);
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(TedTalkService.class);
    }

    public void getTalks() {
        Call<TedTalkResponse> call = service.getTalks("paratmenter");
        call.enqueue(new Callback<TedTalkResponse>() {
            @Override
            public void onResponse(Call<TedTalkResponse> call, Response<TedTalkResponse> response) {
                //if status code is good. and api returns success
                if (response.code() == 200) {
//                    if (response.body().getSuccess()) {
//                        tedTalks = response.body().getMessages();
//                        mListener.onSuccess(tedTalks);
//                    }
                    Log.d(TAG, "SUCCESS!" + tedTalks.get(0).toString());
                }
            }

            //4/10/18 I was able to fix my LEEco phone to Log! I may cry... dial *#*#76937#*#*
            @Override
            public void onFailure(Call<TedTalkResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                mListener.onFailure();
            }
        });

    }

    public void getMessage(int id) {
        Call<TedTalkResponse> call = service.getTalk(id);
        call.enqueue(new Callback<TedTalkResponse>() {
            @Override
            public void onResponse(Call<TedTalkResponse> call, Response<TedTalkResponse> response) {
                if (response.code() == 200) {
//                    if (response.body().getSuccess()) {
//                        TedTalk tedTalk = response.body().getMessages();
//                        mListener.onSuccess(tedTalk);
//                    }
                    //Log.d(TAG, "SUCCESS!" + tedTalk.get(0).getMessage());
                }
            }

            @Override
            public void onFailure(Call<TedTalkResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

    }
}
