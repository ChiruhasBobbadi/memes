package com.chiruhas.android.memes.Data.RetrofitApiCall;

import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.Giphs.GiphPojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GiphyAPI {
    @GET("/v1/gifs/trending")
    Call<GiphPojo> getGiphs();
}
