package com.chiruhas.android.memes;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chiruhas.android.memes.Pojo.Templates.Meme;
import com.chiruhas.android.memes.Pojo.Templates.MemeModel;
import com.chiruhas.android.memes.RetrofitApiCall.Api;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MemeTempFragment.OnFragmentInteractionListener {

    List<Meme> list;



    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState==null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            MemeTempFragment memeTempFragment = new MemeTempFragment();
            fragmentTransaction.add(R.id.fragment, memeTempFragment).commit();
        }
    }

    @Override
    public void onMemeTempFragmentClick(Meme m) {

        BlankFragment blankFragment = new BlankFragment();
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
        fragmentTransaction1.addToBackStack(null);
        fragmentTransaction1.replace(R.id.fragment,blankFragment);
        fragmentTransaction1.commit();
    }
}





