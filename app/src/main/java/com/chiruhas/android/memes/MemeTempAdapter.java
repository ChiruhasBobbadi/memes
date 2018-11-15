
package com.chiruhas.android.memes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chiruhas.android.memes.Pojo.MemeModel;

import java.util.List;


public class MemeTempAdapter extends RecyclerView.Adapter<MemeTempAdapter.ViewHolder> {

    private List<MemeModel> MemeModels;


    public MemeTempAdapter(List<MemeModel> items) {
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

        MemeModel m = MemeModels.get(position);
        String img=m.getUrl();
        Glide.with(holder.itemView.getContext()).load(img).into(holder.iv);
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {

        ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.image);
        }

    }



}
                                