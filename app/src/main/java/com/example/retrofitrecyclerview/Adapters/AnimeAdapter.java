package com.example.retrofitrecyclerview.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.retrofitrecyclerview.Activities.AnimeActivity;
import com.example.retrofitrecyclerview.Model.Anime;
import com.example.retrofitrecyclerview.R;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.MyViewHolder> {

    private Context mContext;
    private List<Anime> mData;
    RequestOptions option;


    public AnimeAdapter(Context mContext, List<Anime> mData) {
        this.mContext = mContext;
        this.mData = mData;
        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.custom_list_layout, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, AnimeActivity.class);
                i.putExtra("name", mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("description", mData.get(viewHolder.getAdapterPosition()).getBio());
                i.putExtra("team", mData.get(viewHolder.getAdapterPosition()).getTeam());
                i.putExtra("img", mData.get(viewHolder.getAdapterPosition()).getImageurl());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_rating.setText(mData.get(position).getRealname());
        holder.tv_studio.setText(mData.get(position).getTeam());
        Glide.with(mContext).load(mData.get(position).getImageurl()).apply(option).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_rating;
        TextView tv_studio;
        TextView tv_category;
        ImageView img_thumbnail;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.anime_name);
            tv_category = itemView.findViewById(R.id.categorie);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_studio = itemView.findViewById(R.id.studio);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }
}
