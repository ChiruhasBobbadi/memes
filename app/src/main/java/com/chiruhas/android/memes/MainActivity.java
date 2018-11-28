package com.chiruhas.android.memes;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.chiruhas.android.memes.Pojo.MemeTemplates.Meme;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MemeTempFragment.OnFragmentInteractionListener,GiphFragment.OnFragmentInteractionListener {

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


        GiphFragment giphFragment = new GiphFragment();
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
        fragmentTransaction1.addToBackStack(null);
        fragmentTransaction1.replace(R.id.fragment,giphFragment);
        fragmentTransaction1.commit();
    }


    @Override
    public void onGiphFragCallBack() {

    }
}





