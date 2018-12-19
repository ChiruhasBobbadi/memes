
package com.chiruhas.android.memes.view.Home.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artjimlop.altex.AltexImageDownloader;
import com.bumptech.glide.Glide;
import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.Meme;
import com.chiruhas.android.memes.R;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;


public class MemeTempAdapter extends RecyclerView.Adapter<MemeTempAdapter.ViewHolder> {

    private List<Meme> MemeModels = new ArrayList<>();
    Context context;
    private ItemListener myListener;


    public MemeTempAdapter(Context conte, ItemListener item) {

        this.context = conte;
        myListener = item;

    }

    public void setData(List<Meme> items) {
        MemeModels = items;
        notifyDataSetChanged();
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Meme m = MemeModels.get(position);
        holder.textView.setText(m.getName());

        Glide.with(holder.iv.getContext()).load(m.getUrl()).centerCrop().into(holder.iv);
        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                myListener.onLikeClicked(m);
            }

            @Override
            public void unLiked(LikeButton likeButton) {

                myListener.onDislikeClicked(m);
            }
        });

        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AltexImageDownloader.writeToDisk(holder.itemView.getContext(), m.getUrl(), "Memes/" + m.getName() + "");
                try {
                    Thread.sleep(1000);
                    Toast.makeText(context, "Download Completed..", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
            }
        });

    }

    public interface ItemListener {
        void onLikeClicked(Meme m);

        void onDislikeClicked(Meme m);
    }


    static public class ViewHolder extends RecyclerView.ViewHolder {


        TextView textView;
        ImageView iv;
        ImageView download;
        LikeButton likeButton;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text);
            iv = itemView.findViewById(R.id.iv);
            likeButton = itemView.findViewById(R.id.star_button);
            download = itemView.findViewById(R.id.download);

//            YoYo.with(Techniques.FadeInUp)
//                    .duration(1000)
//                    .repeat(0)
//                    .playOn(itemView);

        }

    }


}
                                