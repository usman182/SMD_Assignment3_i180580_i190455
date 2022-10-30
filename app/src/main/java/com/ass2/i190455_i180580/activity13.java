package com.ass2.i190455_i180580;

import android.content.ContextWrapper;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

public class activity13 extends AppCompatActivity {

    TextView txt1;
    ImageButton imgbtn1;
    ImageButton playrecording;
    MediaPlayer mediaPlayer;
    MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_13);

        txt1 = findViewById(R.id.next);
        imgbtn1 = findViewById(R.id.back);
        playrecording = findViewById(R.id.playrecording);

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity13.this, activity12.class));
            }
        });

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity13.this, activity14.class));
            }
        });


        playrecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                String mediafilepath = getIntent().getStringExtra("mediafilepath");
                mediaPlayer = new MediaPlayer();

                try {
                    mediaPlayer.setDataSource(mediafilepath);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }


                catch (IOException e) {
                    e.printStackTrace();
                }


                    // print the toast message
                    Toast.makeText(activity13.this, "playing recorded audio", Toast.LENGTH_SHORT).show();




            }
        });


    }

}