package com.chiruhas.android.memes.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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

import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.Meme;
import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.MemeModel;
import com.chiruhas.android.memes.R;
import com.chiruhas.android.memes.Data.RetrofitApiCall.Api;
import com.chiruhas.android.memes.databinding.FragmentMemeTempBinding;
import com.chiruhas.android.memes.view.adapter.MemeTempAdapter;
import com.chiruhas.android.memes.viewmodel.MemeViewModel;

import java.util.ArrayList;
import java.util.List;


public class MemeTempFragment extends Fragment implements EasyPermissions.PermissionCallbacks {


    private static final int PERMISSION_REQUEST_CODE = 1;

    private MemeModel memeModel;
    private List<Meme> meme = new ArrayList<>();

    OnFragmentInteractionListener mListener;
    MemeViewModel viewModel;
    FragmentMemeTempBinding binding;


    MemeTempAdapter adapter;



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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MemeViewModel.class);
        viewModel.getMemes().observe(getViewLifecycleOwner(), new Observer<MemeModel>() {
            @Override
            public void onChanged(MemeModel memeModel) {
                adapter.setData(memeModel.getData().getMemes());
                Toast.makeText(getActivity(), "Items observed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meme_temp, container, false);
        View view = binding.getRoot();
        //instantiating our views
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapter = new MemeTempAdapter(getActivity(), new MemeTempAdapter.ItemListener() {
            @Override
            public void onItemClicked(Meme m) {
                //
            }
        });
        recyclerView.setAdapter(adapter);

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

//   private void downloadImage(final Meme m) {
//        String perms[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
//        if (EasyPermissions.hasPermissions(getActivity(), perms)) {
//
//            new ImageDownloader(m).execute();
//
//
//        } else {
//            EasyPermissions.requestPermissions(getActivity(), "We need permission for downloading file", PERMISSION_REQUEST_CODE, perms);
//        }
//    }

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


//    public class ImageDownloader extends AsyncTask<Void, Integer, Void> {
//        Meme meme;
//
//        ImageDownloader(Meme m) {
//            meme = m;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pb.setVisibility(View.VISIBLE);
//            pb.setMax(100);
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            pb.setVisibility(View.GONE);
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            pb.setProgress(values[0]);
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            AltexImageDownloader.writeToDisk(getActivity(), meme.getUrl(), meme.getName() + "");
//            return null;
//        }
//    }

    class ClickHandlers {
        Context context;

        ClickHandlers(Context c) {
            context = c;
        }


    }
}

