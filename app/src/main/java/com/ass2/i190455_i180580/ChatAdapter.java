package com.ass2.i190455_i180580;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.squircleview.SquircleView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{
    List<ChatMessage> ls;
    Context c;
    String uid,uri;
    ImageHandler imageHandler;
    MediaPlayer mediaPlayer;
    private static final int ITEM_TYPE_SENDER=1;
    private static final int ITEM_TYPE_RECEIVER=0;
    private static final int ITEM_TYPE_SENDER_PIC=2;
    private static final int ITEM_TYPE_RECEIVER_PIC=3;
    private static final int ITEM_TYPE_SENDER_AUDIO=4;
    private static final int ITEM_TYPE_RECEIVER_AUDIO=5;

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
                case ITEM_TYPE_SENDER_AUDIO:
                    Log.d("Viewtype","senderAudio");
                    row = LayoutInflater.from(c).inflate(R.layout.row_sent_audio, parent, false);
                    break;
                case ITEM_TYPE_RECEIVER_AUDIO:
                    Log.d("Viewtype","receiverAudio");
                    row = LayoutInflater.from(c).inflate(R.layout.row_rcvd_audio, parent, false);
                    break;
                default:
                    row = LayoutInflater.from(c).inflate(R.layout.row_sent_msg, parent, false);
            }


        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatMessage details=ls.get(position);
//        if(!details.isHas_uri() && details.getHas_audio().equals("false")) {
//            holder.msg.setText(details.getMessageText());
//        }
//        else if(details.isHas_uri()){
//            Picasso.get().load(details.getUri()).into(holder.image);
//            Log.d("FbUri",details.getUri());
//        }
        if(details.isHas_uri()){
            Picasso.get().load(details.getUri()).into(holder.image);
        }
        else if(details.getHas_audio().equals("true")){
          holder.play.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  try{
                      holder.btnPlayPressed(view,details.getAudio());
                  }catch(IOException e){

                  }

              }
          });
        }
        else{
            try{
                holder.msg.setText(details.getMessageText());
            }
            catch(NullPointerException np){

            }
        }
        holder.timestamp.setText(details.getMessageTime());
    }

    @Override
    public int getItemViewType(int position) {
        if (Objects.equals(ls.get(position).getUid(), uid)) {
            if(ls.get(position).isHas_uri()) {
                return ITEM_TYPE_SENDER_PIC;
            }
            else if(!ls.get(position).getHas_audio().equals("false")){
                return ITEM_TYPE_SENDER_AUDIO;
            }
            else{
                return ITEM_TYPE_SENDER;
            }
        } else {
            if(ls.get(position).isHas_uri()) {
                return ITEM_TYPE_RECEIVER_PIC;
            }
            else if(!ls.get(position).getHas_audio().equals("false")){
                return ITEM_TYPE_RECEIVER_AUDIO;
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
        SquircleView play;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

                msg = itemView.findViewById(R.id.text_message_body);


                image=itemView.findViewById(R.id.image);
                play=itemView.findViewById(R.id.play);

            timestamp=itemView.findViewById(R.id.time_stamp);
        }

        public void btnPlayPressed(View v,String path) throws IOException {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();

            Toast.makeText(c, "Recorded Playing", Toast.LENGTH_SHORT).show();
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
