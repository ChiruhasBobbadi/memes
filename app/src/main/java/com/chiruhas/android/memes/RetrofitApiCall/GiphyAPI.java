package com.chiruhas.android.memes.RetrofitApiCall;

import com.chiruhas.android.memes.Pojo.MemeTemplates.Giphs.GiphPojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GiphyAPI {
    @GET("/v1/gifs/trending")
    Call<GiphPojo> getGiphs();
}
