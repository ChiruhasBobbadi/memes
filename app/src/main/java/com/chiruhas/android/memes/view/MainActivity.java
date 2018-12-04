package com.chiruhas.android.memes.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.Meme;
import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.MemeModel;
import com.chiruhas.android.memes.R;
import com.chiruhas.android.memes.viewmodel.MemeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MemeTempFragment.OnFragmentInteractionListener,GiphFragment.OnFragmentInteractionListener {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    MemeViewModel viewModel;
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





