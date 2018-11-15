package com.chiruhas.android.memes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.chiruhas.android.memes.Pojo.Meme;
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


        Call<MemeModel> lst = a.getMemes();
        lst.enqueue(new Callback<MemeModel>() {
            @Override
            public void onResponse(Call<MemeModel> call, Response<MemeModel> response) {

                if (response.isSuccessful()) {
                    MemeModel me = response.body();

                    List<Meme> list = me.getData().getMemes();
                    rv.setAdapter(new MemeTempAdapter(list));
                }
            }

            @Override
            public void onFailure(Call<MemeModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
