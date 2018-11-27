package com.chiruhas.android.memes;

import android.Manifest;
import android.content.Intent;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chiruhas.android.memes.Pojo.Templates.Meme;
import com.chiruhas.android.memes.Pojo.Templates.MemeModel;
import com.chiruhas.android.memes.RetrofitApiCall.Api;

import java.util.ArrayList;
import java.util.List;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MemeTempFragment.OnFragmentInteractionListener {

    List<Meme> list;
    public static ProgressBar pb;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MemeModel me;
    ArrayList<Meme> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction  = fragmentManager.beginTransaction();
        MemeTempFragment memeTempFragment = new MemeTempFragment();
        fragmentTransaction.add(R.id.fragment,memeTempFragment).commit();
    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}





