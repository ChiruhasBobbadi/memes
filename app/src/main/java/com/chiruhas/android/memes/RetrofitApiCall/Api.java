package com.chiruhas.android.memes.RetrofitApiCall;

import com.chiruhas.android.memes.Pojo.MemeTemplates.MemeModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("get_memes")
    Call<MemeModel> getMemes();
}
