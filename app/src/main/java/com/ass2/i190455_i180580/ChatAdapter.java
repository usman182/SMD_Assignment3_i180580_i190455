package com.ass2.i190455_i180580;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.squircleview.SquircleView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{
    List<ChatMessage> ls;
    Context c;
    String uid,uri;
    ImageHandler imageHandler;
    private static final int ITEM_TYPE_SENDER=1;
    private static final int ITEM_TYPE_RECEIVER=0;
    private static final int ITEM_TYPE_SENDER_PIC=2;
    private static final int ITEM_TYPE_RECEIVER_PIC=3;

    public ChatAdapter(List<ChatMessage> ls, Context c) {
        this.ls = ls;
        this.c = c;
        this.uid= WebAuth.getInstance(c).getCurrentUser().getEmail();
        this.imageHandler=ImageHandler.getInstance(c);
    }

    public ChatAdapter(List<ChatMessage>ls,Context c,String uri){
        this.ls = ls;
        this.c = c;
        this.uid= WebAuth.getInstance(c).getCurrentUser().getEmail();
        this.uri=uri;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row;

            switch(viewType){
                case ITEM_TYPE_SENDER:
                    Log.d("Viewtype","sender");
                    row = LayoutInflater.from(c).inflate(R.layout.row_sent_msg, parent, false);
                    break;
                case ITEM_TYPE_RECEIVER:
                    Log.d("Viewtype","receiver");
                    row = LayoutInflater.from(c).inflate(R.layout.row_rcvd_msg, parent, false);
                    break;
                case ITEM_TYPE_SENDER_PIC:
                    Log.d("Viewtype","senderPic");
                    row = LayoutInflater.from(c).inflate(R.layout.row_sent_pic, parent, false);
                    break;
                case ITEM_TYPE_RECEIVER_PIC:
                    Log.d("Viewtype","receiver");
                    row = LayoutInflater.from(c).inflate(R.layout.row_rcvd_pic, parent, false);
                    break;
                default:
                    row = LayoutInflater.from(c).inflate(R.layout.row_sent_msg, parent, false);
            }


        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatMessage details=ls.get(position);
        if(!details.isHas_uri()) {
            holder.msg.setText(details.getMessageText());
        }
        else{
            Picasso.get().load(details.getUri()).into(holder.image);
            Log.d("FbUri",details.getUri());
        }
        holder.timestamp.setText(details.getMessageTime());
    }

    @Override
    public int getItemViewType(int position) {
        if (Objects.equals(ls.get(position).getUid(), uid)) {
            if(ls.get(position).isHas_uri()) {
                return ITEM_TYPE_SENDER_PIC;
            }
            else{
                return ITEM_TYPE_SENDER;
            }
        } else {
            if(ls.get(position).isHas_uri()) {
                return ITEM_TYPE_RECEIVER_PIC;
            }
            else{
                return ITEM_TYPE_RECEIVER;
            }
        }
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView msg,timestamp;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

                msg = itemView.findViewById(R.id.text_message_body);


                image=itemView.findViewById(R.id.image);

            timestamp=itemView.findViewById(R.id.time_stamp);
        }
    }

    public class RcvrViewHolder extends RecyclerView.ViewHolder{
        TextView msg,timestamp;
        public RcvrViewHolder(@NonNull View itemView,String uri) {
            super(itemView);
            if(uri==null) {
                msg = itemView.findViewById(R.id.text_message_body);
            }
            else{

            }
            timestamp=itemView.findViewById(R.id.time_stamp);
        }
    }
}
