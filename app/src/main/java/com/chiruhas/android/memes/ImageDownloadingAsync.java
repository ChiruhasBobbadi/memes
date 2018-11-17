package com.chiruhas.android.memes;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.artjimlop.altex.AltexImageDownloader;
import com.chiruhas.android.memes.Pojo.Templates.Meme;

public class ImageDownloadingAsync extends AsyncTask<Void,Void,Void> {
  private  Context context=null;

   private  Meme m;

    public ImageDownloadingAsync(Context context, Meme m) {
        this.context = context;
        this.m = m;
    }



    @Override
    protected void onPostExecute(Void aVoid) {
        MainActivity.pb.setVisibility(View.GONE);
        
    }

    @Override
    protected Void doInBackground(Void... voids) {
        AltexImageDownloader.writeToDisk(context,m.getUrl(),m.getName()+"");
        return null;
    }

    @Override
    protected void onPreExecute() {
        MainActivity.pb.setVisibility(View.VISIBLE);
    }
}
