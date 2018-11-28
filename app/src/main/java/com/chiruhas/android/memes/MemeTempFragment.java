package com.chiruhas.android.memes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.artjimlop.altex.AltexImageDownloader;

import com.chiruhas.android.memes.Pojo.MemeTemplates.Meme;
import com.chiruhas.android.memes.Pojo.MemeTemplates.MemeModel;
import com.chiruhas.android.memes.RetrofitApiCall.Api;

import java.util.ArrayList;
import java.util.List;


public class MemeTempFragment extends Fragment implements EasyPermissions.PermissionCallbacks {


    private static final int PERMISSION_REQUEST_CODE = 1;

    private MemeModel memeModel;
    private List<Meme> meme = new ArrayList<>();
    public ProgressBar pb;
    OnFragmentInteractionListener mListener;

    RecyclerView rv;

    public MemeTempFragment() {
        // Required empty public constructor
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meme_temp, container, false);

        //instantiating our views
        pb = view.findViewById(R.id.progress_bar);
        pb.setVisibility(View.GONE);
        rv = view.findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        loadImages();
        if (!meme.isEmpty()) {
            Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "List is empty", Toast.LENGTH_SHORT).show();
        }

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Meme m) {
        if (mListener != null) {
            mListener.onMemeTempFragmentClick(m);
        }

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
        void onMemeTempFragmentClick(Meme m);
    }

    private void downloadImage(final Meme m) {
        String perms[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {

            new ImageDownloader(m).execute();


        } else {
            EasyPermissions.requestPermissions(getActivity(), "We need permission for downloading file", PERMISSION_REQUEST_CODE, perms);
        }
    }

    // !! Permisssion check code //
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(getActivity(), perms)) {
            new AppSettingsDialog.Builder(getActivity()).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(getActivity(), "Welcome back", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    public void loadImages() {
        pb.setVisibility(View.VISIBLE);
        Retrofit r = new Retrofit.Builder().baseUrl("https://api.imgflip.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Api a = r.create(Api.class);
        Call<MemeModel> lst = a.getMemes();
        lst.enqueue(new Callback<MemeModel>() {
            @Override
            public void onResponse(Call<MemeModel> call, Response<MemeModel> response) {

                if (response.isSuccessful()) {

                    pb.setVisibility(View.GONE);
                    memeModel = response.body();
                    meme = memeModel.getData().getMemes();
                    Toast.makeText(getActivity(), "Images loaded", Toast.LENGTH_SHORT).show();

                    MemeTempAdapter memeTempAdapter = new MemeTempAdapter(meme, getContext(), new MemeTempAdapter.ItemListener() {

                        @Override
                        public void onItemClicked(Meme m) {
                            // handle click events oF RECYCLER VIEW on fragment
                            pb.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "clicked " + m.getName(), Toast.LENGTH_SHORT).show();
                            downloadImage(m);
                            onButtonPressed(m);
                        }
                    });
                    rv.setAdapter(memeTempAdapter);
                }
            }

            @Override
            public void onFailure(Call<MemeModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Error...", Toast.LENGTH_SHORT).show();

                // inflate a error message view
            }
        });

    }

    public class ImageDownloader extends AsyncTask<Void, Integer, Void> {
        Meme meme;

        ImageDownloader(Meme m) {
            meme = m;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
            pb.setMax(100);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pb.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            AltexImageDownloader.writeToDisk(getActivity(), meme.getUrl(), meme.getName() + "");
            return null;
        }
    }
}