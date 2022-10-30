package com.ass2.i190455_i180580;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class recyclerView extends AppCompatActivity {

    RecyclerView recyclerView;
    List<SongInfo> mSongInfos;
    DatabaseReference mDatabaseRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSongInfos = new ArrayList<>();



            String playlistname = getIntent().getStringExtra("playlistname");


            mDatabaseRef = FirebaseDatabase.getInstance().getReference("audios");
            mDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        SongInfo songInfo1 = postSnapshot.getValue(SongInfo.class);
                        mSongInfos.add(songInfo1);
                        System.out.println("Song2 is : --> " + mSongInfos.get(0).getSongUrl());
                        recyclerView.setAdapter(new MusicListAdaptor(mSongInfos, recyclerView.this, playlistname));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });





    }

}