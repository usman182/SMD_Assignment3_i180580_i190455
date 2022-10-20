package com.ass2.i190455_i180580;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{
    List<ChatMessage> ls;
    Context c;
    String uid;
    private static final int ITEM_TYPE_SENDER=1;
    private static final int ITEM_TYPE_RECEIVER=0;

    public ChatAdapter(List<ChatMessage> ls, Context c) {
        this.ls = ls;
        this.c = c;
        this.uid= FirebaseAuth.getInstance().getUid();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row;
        if(viewType==ITEM_TYPE_SENDER){
            row= LayoutInflater.from(c).inflate(R.layout.row_sent_msg,parent,false);

        }
        else{
            row= LayoutInflater.from(c).inflate(R.layout.row_rcvd_msg,parent,false);

        }
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatMessage details=ls.get(position);
        holder.msg.setText(details.getMessageText());
        holder.timestamp.setText(details.getMessageTime());
    }

    @Override
    public int getItemViewType(int position) {
        if (Objects.equals(ls.get(position).getUid(), uid)) {
            return ITEM_TYPE_SENDER;
        } else {
            return ITEM_TYPE_RECEIVER;
        }
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView msg,timestamp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            msg=itemView.findViewById(R.id.text_message_body);
            timestamp=itemView.findViewById(R.id.time_stamp);
        }
    }

    public class RcvrViewHolder extends RecyclerView.ViewHolder{
        TextView msg,timestamp;
        public RcvrViewHolder(@NonNull View itemView) {
            super(itemView);
            msg=itemView.findViewById(R.id.text_message_body);
            timestamp=itemView.findViewById(R.id.time_stamp);
        }
    }
}
