package com.chiruhas.android.memes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chiruhas.android.memes.Pojo.MemeTemplates.Giphs.GiphPojo;
import com.chiruhas.android.memes.RetrofitApiCall.GiphyAPI;


public class GiphFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private final String url = "http://api.giphy.com";
    View view;
    private OnFragmentInteractionListener mListener;
     GiphPojo giphPojo;
    public GiphFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_giph, container, false);
        loadGiphs();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onGiphFragCallBack();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onGiphFragCallBack();
    }

    public void loadGiphs() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        GiphyAPI giphyAPI = retrofit.create(GiphyAPI.class);
        Call<GiphPojo> call = giphyAPI.getGiphs();
        call.enqueue(new Callback<GiphPojo>() {
            @Override
            public void onResponse(Call<GiphPojo> call, Response<GiphPojo> response) {

                if(response.isSuccessful())
                {
                    giphPojo = response.body();
                    Toast.makeText(getContext(), "sucess", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getContext(), "error "+response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GiphPojo> call, Throwable t) {

            }
        });
    }
}
