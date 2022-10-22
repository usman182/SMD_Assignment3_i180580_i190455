package com.ass2.i190455_i180580;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

public class activity5 extends AppCompatActivity {

    ImageView img1, img2;
    TextView song, playlistname;
    ImageView plalist1;
    ImageButton imgbtn1, addbutton;
    String title;
    String downloadLink;
    String playlistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        img1 = findViewById(R.id.hamburger1);
        img2 = findViewById(R.id.personicon);
        plalist1 = findViewById(R.id.playlist1);
        song = findViewById(R.id.song);
        addbutton = findViewById(R.id.addbutton);
        playlistname = findViewById(R.id.playlistname);

//        imgbtn1 = findViewById(R.id.play);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity5.this, activity8.class));
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity5.this, activity6.class));
            }
        });

//        imgbtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(activity5.this, activity9.class));
//            }
//        });



        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity5.this, activity9.class);
                startActivityForResult(intent, 100);
            }
        });

        plalist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(downloadLink);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        title = data.getStringExtra("title");
        downloadLink = data.getStringExtra("link");
        playlistName = data.getStringExtra("playlistname");
        song.setText(title);
        playlistname.setText(playlistName);
        System.out.println("playlist is " + playlistName);
    }

}