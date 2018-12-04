package com.chiruhas.android.memes.Data.RetrofitApiCall;

import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.MemeModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("get_memes")
    Call<MemeModel> getMemes();
}
