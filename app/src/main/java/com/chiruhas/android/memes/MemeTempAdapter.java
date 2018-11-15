
package com.chiruhas.android.memes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chiruhas.android.memes.Pojo.Meme;
import com.chiruhas.android.memes.Pojo.MemeModel;


import java.util.List;


public class MemeTempAdapter extends RecyclerView.Adapter<MemeTempAdapter.ViewHolder> {

    private List<Meme> MemeModels;


    public MemeTempAdapter(List<Meme> items) {
        MemeModels = items;

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

        Meme m = MemeModels.get(position);
        //String img=m.getUrl();
        //Glide.with(holder.itemView.getContext()).load(img).into(holder.iv);

        holder.textView.setText(m.getName());
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
                                