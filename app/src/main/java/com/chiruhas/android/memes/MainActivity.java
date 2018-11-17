package com.chiruhas.android.memes;

import android.Manifest;
import android.content.Intent;

import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chiruhas.android.memes.AsyncTasks.ImageDownloadingAsync;
import com.chiruhas.android.memes.Pojo.Templates.Meme;
import com.chiruhas.android.memes.Pojo.Templates.MemeModel;
import com.chiruhas.android.memes.RetrofitApiCall.Api;

import java.util.List;


import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private static final int PERMISSION_REQUEST_CODE = 1;
    RecyclerView rv;
   public static ProgressBar pb;

    MemeModel me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
    pb=findViewById(R.id.progress);
    pb.setVisibility(View.GONE);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this, 2));

        // fetching api response using retrofit
        Retrofit r = new Retrofit.Builder().baseUrl("https://api.imgflip.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Api a = r.create(Api.class);
        Call<MemeModel> lst = a.getMemes();
        lst.enqueue(new Callback<MemeModel>() {
            @Override
            public void onResponse(Call<MemeModel> call, Response<MemeModel> response) {

                if (response.isSuccessful()) {
                    me = response.body();

                    List<Meme> list = me.getData().getMemes();
                    MemeTempAdapter m = new MemeTempAdapter(list, MainActivity.this, new MemeTempAdapter.ItemListener() {
                        @Override
                        public void onItemClicked(final Meme m) {
                            Intent i = new Intent(MainActivity.this,ImageViewActivity.class);
                            i.putExtra("url",m.getUrl());
                            i.putExtra("height",m.getHeight());
                            i.putExtra("width",m.getWidth());
                            i.putExtra("name",m.getName());
                            startActivity(i);
                            downloadImage(m);
                        }
                    });
                    rv.setAdapter(m);

                }
            }

            @Override
            public void onFailure(Call<MemeModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error...", Toast.LENGTH_SHORT).show();
            }
        });
        // search view
   }

    // image downloading  code

    private void downloadImage(final Meme m) {
        String perms[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(MainActivity.this, perms)) {

            new ImageDownloadingAsync(MainActivity.this,m).execute();


        }
        else
        {
            EasyPermissions.requestPermissions(MainActivity.this,"We need permission for downloading file",PERMISSION_REQUEST_CODE,perms);
        }
    }
    // end of image downloading code

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
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // Search view
    }





