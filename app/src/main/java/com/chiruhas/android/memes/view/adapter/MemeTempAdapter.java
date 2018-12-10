
package com.chiruhas.android.memes.view.adapter;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.Meme;
import com.chiruhas.android.memes.Model.RoomModel.CacheMemeModel;
import com.chiruhas.android.memes.R;
import com.chiruhas.android.memes.viewmodel.MemeViewModel;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.reflect.TypeToken.get;


public class MemeTempAdapter extends RecyclerView.Adapter<MemeTempAdapter.ViewHolder> {

    private List<Meme> MemeModels = new ArrayList<>();
    Context context;
    private ItemListener myListener;



    public MemeTempAdapter(Context conte,ItemListener item) {

        this.context=conte;
        myListener=item;

    }
    public void setData(List<Meme> items)
    {
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
       holder.love.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (holder.love.getDrawable().getConstantState().toString() ==  holder.itemView.getResources().getDrawable(R.drawable.ic_favorite).getConstantState().toString()) {

                   holder.love.setImageResource(R.drawable.ic_favorite_border);
                   // delete bookmark
               } else {
                   Log.d("lhs",holder.love.getDrawable().getConstantState()+"");
                   Log.d("RHS",holder.itemView.getResources().getDrawable(R.drawable.ic_favorite).getConstantState()+"");
                   holder.love.setImageResource(R.drawable.ic_favorite);
                   Log.d("lhs1",holder.love.getDrawable().getConstantState()+"");
                   myListener.onLoveClicked(m);
               }
           }
       });

    }
    public interface ItemListener {
        void onLoveClicked(Meme m);
    }



  static  public class ViewHolder extends RecyclerView.ViewHolder  {

       // ImageView iv;
        TextView textView;
        ImageView iv;
        ImageView love;
        public ViewHolder(View itemView) {
            super(itemView);
            //iv=itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
            iv = itemView.findViewById(R.id.iv);
            love = itemView.findViewById(R.id.love);

//            YoYo.with(Techniques.FadeInUp)
//                    .duration(1000)
//                    .repeat(0)
//                    .playOn(itemView);

        }

    }




}
                                