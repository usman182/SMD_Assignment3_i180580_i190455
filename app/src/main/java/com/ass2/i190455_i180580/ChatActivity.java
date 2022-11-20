package com.ass2.i190455_i180580;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {
    EditText msgBox;
    ImageView sendMsg,camera;
    String message;
    TextView display_name;


    ChatAdapter adapter;
    List<ChatMessage> ls;
    RecyclerView rv;
    boolean retrieve_old_msgs;

    WebAuth webAuth=WebAuth.getInstance(ChatActivity.this);

    String contact_name,contact_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        msgBox=findViewById(R.id.msg_box);
        sendMsg=findViewById(R.id.send_msg);
        rv=findViewById(R.id.chat_history);
        camera=findViewById(R.id.take_pic);
        display_name=findViewById(R.id.name);

        contact_name=getIntent().getStringExtra("display name");
        contact_email=getIntent().getStringExtra("email");
        //Log.d("rcvr",contact_email);
        display_name.setText(contact_name);
    }

    public boolean returnChat(String uid,String rcvr){
        String userEmail=webAuth.getCurrentUser().getEmail();
        if(Objects.equals(rcvr, contact_email) && Objects.equals(uid, userEmail)){
            return true;
        }
        else{
            return Objects.equals(userEmail, rcvr) && Objects.equals(uid, contact_email);
        }
    }
}