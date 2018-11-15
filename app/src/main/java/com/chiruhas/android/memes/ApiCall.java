package com.chiruhas.android.memes;

import com.chiruhas.android.memes.Pojo.MemeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCall {
    List<MemeModel> list;

    public List<MemeModel> getList() {
        return list;
    }

    public  void call()
    {
        Retrofit r = new Retrofit.Builder().baseUrl("https://api.imgflip.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Api a = r.create(Api.class);
        Call<List<MemeModel>> lst = a.getMemes();
        lst.enqueue(new Callback<List<MemeModel>>() {
            @Override
            public void onResponse(Call<List<MemeModel>> call, Response<List<MemeModel>> response) {
                if(!response.isSuccessful())
                {
                    return;
                }

                 list= response.body();


            }

            @Override
            public void onFailure(Call<List<MemeModel>> call, Throwable t) {

            }
        });

    }
}
