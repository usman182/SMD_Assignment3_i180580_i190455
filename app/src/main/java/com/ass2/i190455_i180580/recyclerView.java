package com.ass2.i190455_i180580;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

public class recyclerView extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Upload> mUploads;
    DatabaseReference mDatabaseRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();



            String playlistname = getIntent().getStringExtra("playlistname");


            mDatabaseRef = FirebaseDatabase.getInstance().getReference("audios");
            mDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Upload upload1 = postSnapshot.getValue(Upload.class);
                        mUploads.add(upload1);
                        System.out.println("Song2 is : --> " + mUploads.get(0).getSongUrl());
                        recyclerView.setAdapter(new MusicListAdaptor(mUploads, recyclerView.this, playlistname));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });





    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(recyclerView!=null){
//            recyclerView.setAdapter(new MusicListAdaptor(songsList,getApplicationContext()));
//        }
  //  }

}