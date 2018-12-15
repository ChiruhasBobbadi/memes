
package com.chiruhas.android.memes.view.Bookmarks.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.artjimlop.altex.AltexImageDownloader;
import com.bumptech.glide.Glide;
import com.chiruhas.android.memes.Model.RoomModel.CacheMemeModel;
import com.chiruhas.android.memes.R;



import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MemeBookmarksAdapter extends RecyclerView.Adapter<MemeBookmarksAdapter.ViewHolder> {

    private List<CacheMemeModel> CacheMemeModels = new ArrayList<>();
    private ItemListener myListener;

    public MemeBookmarksAdapter( ItemListener listener) {

        myListener = listener;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meme_bookmark_layout, parent, false)); // TODO
    }

    public void setData(List<CacheMemeModel> list)
    {
        CacheMemeModels = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return CacheMemeModels.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CacheMemeModel c = CacheMemeModels.get(position);
        Glide.with(holder.iv.getContext()).load(c.getUrl()).centerCrop().into(holder.iv);
        holder.title.setText(c.getName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.deleteClicked(c);
                notifyDataSetChanged();
            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.download.setVisibility(View.GONE);
                AltexImageDownloader.writeToDisk(holder.itemView.getContext(), c.getUrl(), "Memes/"+c.getName()+"");
                holder.pb.setVisibility(View.VISIBLE);

//
//                   new  Thread(new Runnable() {
//                       @Override
//                       public void run() {
//                           try {
//                               Thread.sleep(1000);
//                           }
//                        catch(Exception e)
//                           {
//
//                           }
//                       }
//                   });
//                }
                try
                {   holder.pb.setVisibility(View.VISIBLE);
                    Thread.sleep(5000);
                }
                catch (Exception e)
                {

                }

                holder.pb.setVisibility(View.GONE);
                holder.download.setVisibility(View.VISIBLE);
            }
        });


    }

    public interface ItemListener {
       void deleteClicked(CacheMemeModel cacheMemeModel);

    }

    public class ViewHolder extends RecyclerView.ViewHolder  {


       ImageView iv,download,delete;
       TextView title;
       ProgressBar pb ;

        public ViewHolder(View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.image_view);
            title = itemView.findViewById(R.id.title);
            download = itemView.findViewById(R.id.download);
            delete = itemView.findViewById(R.id.delete);
            pb =itemView.findViewById(R.id.pb);
            pb.setVisibility(View.GONE);
        }


    }


}
                                