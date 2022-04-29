package com.example.perpectday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<NewPost> newPostArrayList;

    public MyAdapter(Context context, ArrayList<NewPost> newPostArrayList) {
        this.context = context;
        this.newPostArrayList = newPostArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.newcard,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        NewPost newpost = newPostArrayList.get(position);

        holder.title_card.setText(newpost.getTitle());
        holder.subtitle_card.setText(newpost.getSubtitle());
        holder.content_card.setText(newpost.getContent());

        holder.time_card.setText(newpost.getTime());
        holder.tag.setText(newpost.getTag());
    }

    @Override
    public int getItemCount() {
        return newPostArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title_card;
        TextView subtitle_card,content_card;
        TextView time_card;
        TextView tag;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_card = itemView.findViewById(R.id.title_card);
            subtitle_card = itemView.findViewById(R.id.subtitle_card);
            content_card = itemView.findViewById(R.id.content_card);
            tag = itemView.findViewById(R.id.tag);
            time_card = itemView.findViewById(R.id.content_time);

        }
    }
}
