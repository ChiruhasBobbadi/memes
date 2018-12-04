
package com.chiruhas.android.memes.view.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.Meme;
import com.chiruhas.android.memes.R;

import java.util.ArrayList;
import java.util.List;


public class MemeTempAdapter extends RecyclerView.Adapter<MemeTempAdapter.ViewHolder> {

    private List<Meme> MemeModels = new ArrayList<>();
    Context context;
    private ItemListener myListener;

    public MemeTempAdapter(Context context,ItemListener item) {

        this.context=context;
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Meme m = MemeModels.get(position);
        holder.textView.setText(m.getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.onItemClicked(m);
            }
        });
    }
    public interface ItemListener {
        void onItemClicked(Meme m);
    }



  static  public class ViewHolder extends RecyclerView.ViewHolder  {

       // ImageView iv;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            //iv=itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);


        }

    }




}
                                