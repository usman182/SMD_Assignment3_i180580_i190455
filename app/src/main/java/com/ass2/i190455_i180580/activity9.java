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

import java.util.ArrayList;
import java.util.List;

public class activity9 extends AppCompatActivity {

      EditText edtxt1;
      EditText edtxt2;
    ImageButton imgbtn1;
    Button btn1;
    ImageView upload, musicpic;
    StorageReference storageReference;
    MediaPlayer mediaPlayer;
    ProgressDialog progressDialog;
    String audioID = null;
    Task<Uri> task;
    String audioLink = null;
    List<Upload> songsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9);

        edtxt1 = findViewById(R.id.playlistname);
        edtxt2 = findViewById(R.id.songnamefromfirebase);
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

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(activity9.this, recyclerView.class));
//                Intent i = new Intent(activity9.this, recyclerView.class);
//                startActivityForResult(i, 100);
//                String title = audioID;
//                String downloadLink = audioLink;
//                String playlistname = edtxt1.getText().toString();
//                Intent intent = new Intent(activity9.this, activity5.class);
//                intent.putExtra("title", title);
//                intent.putExtra("link", downloadLink);
//
//
//
//                intent.putExtra("playlistname", playlistname);
//                setResult(1, intent);
//                onActivityResult(100, 1, intent);
//                finish();

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(activity9.this, recyclerView.class));

            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //songsList = (ArrayList<Upload>) getIntent().getSerializableExtra("list");

    }
}