package com.chiruhas.android.memes.view.Bookmarks.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.artjimlop.altex.AltexImageDownloader;
import com.chiruhas.android.memes.Model.RoomModel.CacheMemeModel;
import com.chiruhas.android.memes.R;
import com.chiruhas.android.memes.view.Bookmarks.adapters.MemeBookmarksAdapter;
import com.chiruhas.android.memes.viewmodel.MemeViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class MemeBookmarks extends Fragment {

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView;
    MemeViewModel memeViewModel;
    MemeBookmarksAdapter adapter = null;

    public MemeBookmarks() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        memeViewModel = ViewModelProviders.of(this).get(MemeViewModel.class);
        memeViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<CacheMemeModel>>() {
            @Override
            public void onChanged(List<CacheMemeModel> cacheMemeModels) {
                adapter.setData(cacheMemeModels);

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_bookmarks, container, false);
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new MemeBookmarksAdapter(new MemeBookmarksAdapter.ItemListener() {
            @Override
            public void deleteClicked(CacheMemeModel cacheMemeModel) {

                memeViewModel.delete(cacheMemeModel);
                Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }


        });


        recyclerView.setAdapter(adapter);


        return view;
        }


        public void onButtonPressed (Uri uri){
            if (mListener != null) {
                mListener.onFragmentInteraction(uri);
            }
        }

        @Override
        public void onAttach (Context context){
            super.onAttach(context);
            if (context instanceof OnFragmentInteractionListener) {
                mListener = (OnFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }

        @Override
        public void onDetach () {
            super.onDetach();
            mListener = null;
        }


        public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }
    }
