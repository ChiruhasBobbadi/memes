package com.chiruhas.android.memes.view.Home.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.artjimlop.altex.AltexImageDownloader;
import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.Meme;
import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.MemeModel;
import com.chiruhas.android.memes.Model.RoomModel.CacheMemeModel;
import com.chiruhas.android.memes.R;

import com.chiruhas.android.memes.view.Home.adapter.MemeTempAdapter;
import com.chiruhas.android.memes.viewmodel.MemeViewModel;

import java.util.ArrayList;
import java.util.List;


public class MemeTempFragment extends Fragment {

    OnFragmentInteractionListener mListener;
    MemeViewModel viewModel;

    private ProgressBar progressBar;

    private MemeTempAdapter adapter;
    private RecyclerView recyclerView;
    List<Meme> query = new ArrayList<>();



    public MemeTempFragment() {
        // Required empty public constructor
    }

    public void queryData(List<Meme> list) {
        query = list;
        Log.d("received",list.size()+"");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MemeTempFragment.OnFragmentInteractionListener) {
            mListener = (MemeTempFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(MemeViewModel.class);
        viewModel.getMemes().observe(getViewLifecycleOwner(), new Observer<MemeModel>() {
            @Override
            public void onChanged(MemeModel memeModel) {
                adapter.setData(memeModel.getData().getMemes());
                progressBar.setVisibility(View.GONE);
                passDataToActivity(memeModel.getData().getMemes());
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_meme_temp, container, false);
        //instantiating our views
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.pbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapter = new MemeTempAdapter(getActivity(), new MemeTempAdapter.ItemListener() {
            @Override
            public void onLikeClicked(Meme m) {
                viewModel.insert(new CacheMemeModel(m.getName(), m.getUrl(), m.getWidth(), m.getHeight()));

                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDislikeClicked(Meme m) {
                viewModel.delete(new CacheMemeModel(m.getName(), m.getUrl(), m.getWidth(), m.getHeight()));
                // Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });
        if(!query.isEmpty())
        {
            adapter.setData(query);
        }


        recyclerView.setAdapter(adapter);

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void passDataToActivity(List<Meme> memes) {
        if (mListener != null) {
            mListener.memeCallback(memes);
        }

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void memeCallback(List<Meme> memes);
    }

}

