package com.ass2.i190455_i180580;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {
    EditText msgBox;
    ImageView sendMsg,camera;
    String message;
    TextView display_name;


    ChatAdapter adapter;
    ChatImgAdapter imgAdapter;
    List<ChatMessage> ls;
    RecyclerView rv;
    boolean retrieve_old_msgs;

    String contact_name;


    FirebaseDatabase rdb=FirebaseDatabase.getInstance();
    DatabaseReference mRef=rdb.getReference();
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    FirebaseUser user;
    UserProfileChangeRequest profileUpdates;

    String contact_email;
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

        ls=new ArrayList<>();
        RecyclerView.LayoutManager lm=new LinearLayoutManager(ChatActivity.this);
        adapter=new ChatAdapter(ls,ChatActivity.this);
        rv.setLayoutManager(lm);
        retrieve_old_msgs=false;

//        mAuth.signInWithEmailAndPassword("hasanriaz121@gmail.com","12345678");
        profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Hassan").build();
        user=mAuth.getCurrentUser();
        user.updateProfile(profileUpdates);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                try {
//                    startActivityForResult(takePictureIntent, 32);
//                } catch (ActivityNotFoundException e) {
//                    // display error state to the user
//                }

                Intent getPictureIntent=new Intent();
                getPictureIntent.setAction(Intent.ACTION_GET_CONTENT);
                getPictureIntent.setType("image/*");
                startActivityForResult(getPictureIntent,33);
            }
        });

        mRef.child("messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    String rcvr=snapshot.child("rcvr").getValue(String.class);
                    String uid = snapshot.child("uid").getValue(String.class);
                    if(returnChat(uid,rcvr)) {

                            String msg=null;
                            String time = snapshot.child("messageTime").getValue(String.class);
                            boolean has_uri=snapshot.child("has_uri").getValue(boolean.class);

                            Log.d("name", rcvr);

                            Log.d("time", time);
                            ChatMessage temp = new ChatMessage(uid, rcvr, msg);
                            temp.setMessageTime(time);
                            if(has_uri){
                                temp.setUri(snapshot.child("uri").getValue(String.class));
                            }
                            else{
                                temp.setMessageText(snapshot.child("messageText").getValue(String.class));
                            }


                            Collections.sort(ls);
//                            Collections.reverse(ls);
                            ls.add(temp);

                            adapter = new ChatAdapter(ls, ChatActivity.this);
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

                message=msgBox.getText().toString();
                ChatMessage temp=new ChatMessage(mAuth.getCurrentUser().getEmail(),contact_email,message);


//                Toast.makeText(ChatActivity.this,"Added to list",Toast.LENGTH_SHORT).show();
                mRef.child("messages").push().setValue(temp);
//


//                Toast.makeText(ChatActivity.this,"Added to RDB",Toast.LENGTH_SHORT).show();
//                adapter=new ChatAdapter(ls,ChatActivity.this);
//                rv.setAdapter(adapter);
//                Toast.makeText(ChatActivity.this,"Added to adapter",Toast.LENGTH_SHORT).show();
                msgBox.setText("");
                retrieve_old_msgs=false;
            }
        });
    }

    public boolean returnChat(String uid,String rcvr){
        String userEmail=mAuth.getCurrentUser().getEmail();
        if(Objects.equals(rcvr, contact_email) && Objects.equals(uid, userEmail)){
            return true;
        }
        else{
            return Objects.equals(userEmail, rcvr) && Objects.equals(uid, contact_email);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==32 && resultCode==RESULT_OK){
            Log.d("ImageTag",data.getData().toString());
        }
        if(requestCode==33 && resultCode==RESULT_OK){
            Uri image=data.getData();
            Calendar c=Calendar.getInstance();
            FirebaseStorage st=FirebaseStorage.getInstance();
            StorageReference stRef=st.getReference().child("images/"+mAuth.getCurrentUser().getEmail()+"/"+contact_email+"/"+c.getTimeInMillis()+".jpg");

            stRef.putFile(image)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Toast.makeText(MainActivity.this,"Upload success", Toast.LENGTH_SHORT).show();
                            Task<Uri> task=taskSnapshot.getStorage().getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    ChatMessage temp=new ChatMessage(mAuth.getCurrentUser().getEmail(),contact_email,null);
                                    temp.setUri(uri.toString());
                                    mRef.child("messages").push().setValue(temp);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });


        }
    }
}