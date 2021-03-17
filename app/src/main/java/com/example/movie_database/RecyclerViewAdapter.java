package com.example.movie_database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //array create for getItem
    Context context;
    List<ResultsItem> resultsItems;

    public RecyclerViewAdapter(Context context, List<ResultsItem> resultsItems) {
        this.context = context;
        this.resultsItems = resultsItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.getmovieitem, parent, false);
//        return new ViewHolder(view);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w342" + resultsItems.get(position).getPosterPath()).into(holder.img_item);
        holder.img_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,MovieDetailsActivity.class).putExtra("movie_id",resultsItems.get(position).getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_item = itemView.findViewById(R.id.img_item);
        }
    }
}
