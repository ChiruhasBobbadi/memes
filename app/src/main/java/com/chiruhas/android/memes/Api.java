package com.chiruhas.android.memes;

import com.chiruhas.android.memes.Pojo.MemeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("get_memes")
    Call<MemeModel> getMemes();
}
