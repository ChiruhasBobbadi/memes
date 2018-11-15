package com.chiruhas.android.memes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chiruhas.android.memes.Pojo.MemeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this,2));


        Retrofit r = new Retrofit.Builder().baseUrl("https://api.imgflip.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Api a = r.create(Api.class);
        final Call<List<MemeModel>> lst = a.getMemes();
        lst.enqueue(new Callback<List<MemeModel>>() {
            @Override
            public void onResponse(Call<List<MemeModel>> call, Response<List<MemeModel>> response) {
                if(!response.isSuccessful())
                {
                    return;
                }

               List<MemeModel> list= response.body();

                rv.setAdapter(new MemeTempAdapter(list));

            }

            @Override
            public void onFailure(Call<List<MemeModel>> call, Throwable t) {

            }
        });




    }
}
