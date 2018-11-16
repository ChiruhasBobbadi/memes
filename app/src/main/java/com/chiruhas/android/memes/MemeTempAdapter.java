
package com.chiruhas.android.memes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.artjimlop.altex.AltexImageDownloader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.chiruhas.android.memes.Pojo.Templates.Meme;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;


public class MemeTempAdapter extends RecyclerView.Adapter<MemeTempAdapter.ViewHolder> {

    private List<Meme> MemeModels;
    Context context;

    public MemeTempAdapter(List<Meme> items,Context context) {
        MemeModels = items;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meme_temp_layout, parent, false));
    }

    @Override
    public int getItemCount() {
        return MemeModels.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Meme m = MemeModels.get(position);
        //String img=m.getUrl();
        //Glide.with(holder.itemView.getContext()).load(img).into(holder.iv);

        holder.textView.setText(m.getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();

                // request permission
                AltexImageDownloader.writeToDisk(context, m.getUrl(),"Memes");

            }
        });
    }




    public class ViewHolder extends RecyclerView.ViewHolder  {

       // ImageView iv;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            //iv=itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
        }

    }



}
                                