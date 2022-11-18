package com.ass2.i190455_i180580;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.squircleview.SquircleView;

import java.util.List;

//import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    Context c;
    List<Contact> ls;

    public ContactAdapter(Context c, List<Contact> ls) {
        this.c = c;
        this.ls = ls;
    }

    public ContactAdapter(List<Contact> ls) {
        this.ls = ls;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row=LayoutInflater.from(c).inflate(R.layout.contact_row,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contact details=ls.get(position);
        holder.display_name.setText(details.getName());
        holder.msg.setText(details.getLatestMsg());
//        holder.dp.setImageURI(Uri.parse(details.getDp()));

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(c,ChatActivity.class);
                i.putExtra("display name",details.getName());
                i.putExtra("email",details.getEmail());

//                i.putExtra("dp",details.getDp());

                c.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout parent;
        TextView display_name,msg,date_time;
        SquircleView dp;
//        CircleImageView alert;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            display_name=itemView.findViewById(R.id.display_name);
            msg=itemView.findViewById(R.id.latest_msg);
            date_time=itemView.findViewById(R.id.date_time);
            dp=itemView.findViewById(R.id.dp);
//            alert=itemView.findViewById(R.id.alert);
        }
    }
}
