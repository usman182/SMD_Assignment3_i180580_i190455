package com.ass2.i190455_i180580;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MusicListAdaptor extends RecyclerView.Adapter<MusicListAdaptor.ViewHolder> {

    ArrayList<AudioModel> songsList;
    Context context;

    public MusicListAdaptor(ArrayList<AudioModel> songsList, Context context) {
        this.songsList = songsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new MusicListAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AudioModel songsData = songsList.get(position);
        holder.title.setText(songsData.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to another activity

                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex = position;
                Intent i = new Intent(context, activity5.class);
                i.putExtra("list", songsList);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.music_title_text);
        }
    }
}
