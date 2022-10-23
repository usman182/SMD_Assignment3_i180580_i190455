package com.ass2.i190455_i180580;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.squircleview.SquircleView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class ChatImgAdapter extends RecyclerView.Adapter<ChatImgAdapter.MyViewHolder> {

    List<ChatMessage> ls;
    Context c;
    String uid;

    private static final int ITEM_TYPE_SENDER=1;
    private static final int ITEM_TYPE_RECEIVER=0;

    public ChatImgAdapter(List<ChatMessage> ls, Context c) {
        this.ls = ls;
        this.c = c;
        this.uid= FirebaseAuth.getInstance().getCurrentUser().getEmail();

    }

    @NonNull
    @Override
    public ChatImgAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row;
        if(viewType==ITEM_TYPE_SENDER){
            row= LayoutInflater.from(c).inflate(R.layout.row_sent_pic,parent,false);

        }
        else{
            row= LayoutInflater.from(c).inflate(R.layout.row_rcvd_pic,parent,false);

        }
        return new ChatImgAdapter.MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatImgAdapter.MyViewHolder holder, int position) {
        ChatMessage details=ls.get(position);
        Picasso.get().load(details.getUri()).into(holder.image);
        holder.time_stamp.setText(details.getMessageTime());
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (Objects.equals(ls.get(position).getUid(), uid)) {
            return ITEM_TYPE_SENDER;
        } else {
            return ITEM_TYPE_RECEIVER;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SquircleView image;
        TextView time_stamp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            time_stamp=itemView.findViewById(R.id.time_stamp);
        }
    }
}
