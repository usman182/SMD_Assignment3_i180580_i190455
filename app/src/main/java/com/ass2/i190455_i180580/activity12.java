package com.ass2.i190455_i180580;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class activity12 extends AppCompatActivity {

    ImageButton imgbtn1, stoprecording;
    TextView recordingtimer;
    String mediafilepath;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_12);

        imgbtn1 = findViewById(R.id.back);
        recordingtimer = findViewById(R.id.recordingtimer);
        stoprecording = findViewById(R.id.stoprecordingbutton);


        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity12.this, activity11.class));
            }
        });



        stoprecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // print the toast message
                Toast.makeText(activity12.this, "Recording stopped", Toast.LENGTH_SHORT).show();

                String mediafilepath = getIntent().getStringExtra("mediafilepath");


                Intent intent = new Intent(activity12.this, activity13.class);
                intent.putExtra("mediafilepath", mediafilepath);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

    }



}