package com.chiruhas.android.memes.Data;

import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.Giphs.GiphPojo;
import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.MemeModel;
import com.chiruhas.android.memes.Data.RetrofitApiCall.Api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository {
    MutableLiveData<MemeModel> memeData;
   // MutableLiveData<GiphPojo> giph;
    Retrofit retrofit;

    public LiveData<MemeModel> getMemeData()
    {
        retrofit =  new Retrofit.Builder().baseUrl("https://api.imgflip.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Api a = retrofit.create(Api.class);
        Call<MemeModel> lst = a.getMemes();
        lst.enqueue(new Callback<MemeModel>() {
            @Override
            public void onResponse(Call<MemeModel> call, Response<MemeModel> response) {
                memeData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MemeModel> call, Throwable t) {

                // error handling
            }
        });
        return memeData;
    }

//    public LiveData<GiphPojo> getGiphs()
//    {
//
//    }
}
