package com.ass2.i190455_i180580;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    EditText msgBox;
    ImageView sendMsg;
    String message;

    ChatAdapter adapter;
    List<ChatMessage> ls;
    RecyclerView rv;
    boolean retrieve_old_msgs;


    FirebaseDatabase rdb=FirebaseDatabase.getInstance();
    DatabaseReference mRef=rdb.getReference();
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    FirebaseUser user;
    UserProfileChangeRequest profileUpdates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        msgBox=findViewById(R.id.msg_box);
        sendMsg=findViewById(R.id.send_msg);
        rv=findViewById(R.id.chat_history);
        ls=new ArrayList<>();
        RecyclerView.LayoutManager lm=new LinearLayoutManager(ChatActivity.this);

        adapter=new ChatAdapter(ls,ChatActivity.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        retrieve_old_msgs=false;

        mAuth.signInWithEmailAndPassword("hasanriaz121@gmail.com","12345678");
        profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Hassan").build();
        user=mAuth.getCurrentUser();
        user.updateProfile(profileUpdates);

        mRef.child("list").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(retrieve_old_msgs==false){
                    String name=snapshot.child("messageUser").getValue(String.class);
                    String msg=snapshot.child("messageText").getValue(String.class);
                    String time=snapshot.child("messageTime").getValue(String.class);
                    String uid=snapshot.child("uid").getValue(String.class);
                    Log.d("name",name);
                    Log.d("msg",msg);
                    Log.d("time",time);
                    ChatMessage temp=new ChatMessage(msg,name);
                    temp.setMessageTime(time);
                    temp.setUid(uid);
                    Collections.reverse(ls);
                    ls.add(temp);

                    adapter=new ChatAdapter(ls,ChatActivity.this);
                    rv.setAdapter(adapter);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieve_old_msgs=true;
                message=msgBox.getText().toString();
                ChatMessage temp=new ChatMessage(message,user.getDisplayName());
                temp.setUid(mAuth.getUid());
                ls.add(temp);

//                Toast.makeText(ChatActivity.this,"Added to list",Toast.LENGTH_SHORT).show();
                mRef.child("list").push().setValue(temp);
//


//                Toast.makeText(ChatActivity.this,"Added to RDB",Toast.LENGTH_SHORT).show();
                adapter=new ChatAdapter(ls,ChatActivity.this);
                rv.setAdapter(adapter);
//                Toast.makeText(ChatActivity.this,"Added to adapter",Toast.LENGTH_SHORT).show();
                msgBox.setText("");
            }
        });
    }
}