package com.ass2.i190455_i180580;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class activity9 extends AppCompatActivity {

      EditText edtxt1;
    ImageButton imgbtn1;
    Button btn1;
    ImageView upload, musicpic;
    StorageReference storageReference;
    MediaPlayer mediaPlayer;
    ProgressDialog progressDialog;
    String audioID = null;
    Task<Uri> task;
    String audioLink = null;
    List<SongInfo> songsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9);

        edtxt1 = findViewById(R.id.playlistname);
        imgbtn1 = findViewById(R.id.back);
        btn1 = findViewById(R.id.addplaylist);
        upload = findViewById(R.id.uploadmus);
        musicpic = findViewById(R.id.musicpic);


        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity9.this, activity5.class));
            }
        });

        // when the user clicks on the add playlist button
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivity(new Intent(activity9.this, recyclerView.class));

                Intent intent = new Intent(activity9.this, recyclerView.class);
                intent.putExtra("playlistname", edtxt1.getText().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(activity9.this, recyclerView.class));

            }
        });


    }

}