package com.ass2.i190455_i180580;

import static com.ass2.i190455_i180580.R.drawable.ic_baseline_play_arrow_24;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class activity5 extends AppCompatActivity {

    ImageView img1, img2;
    TextView song, playlistname;
    ImageView plalist1;
    ImageView plusimage;
    ImageButton imgbtn1, addbutton, play, resume_play1;
    String title;
    String downloadLink;
    String playlistName;
    Intent i;
    MediaPlayer mediaPlayer = new MediaPlayer();
    @SuppressLint("MissingInflatedId")
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
        plusimage = findViewById(R.id.plusimage);
        play = findViewById(R.id.play);


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

//        plusimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(activity5.this, activity10.class));
//            }
//        });


        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(activity5.this, activity9.class);
                startActivityForResult(i, 100);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                String uri = "@drawable/ic_baseline_play_arrow_24";
                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                play.setImageDrawable(res);

                play.setId(R.id.resume_play);
                resume_play1 = findViewById(R.id.resume_play);
//                play.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mediaPlayer.pause();
//                        String uri = "@drawable/ic_baseline_pause_24";
//                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//                        Drawable res = getResources().getDrawable(imageResource);
//                        play.setImageDrawable(res);
//                    }
//                });

//                resume_play.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mediaPlayer.start();
//                        Toast.makeText(
//                                activity5.this, "upload audio",
//                                Toast.LENGTH_LONG).show();
//
//                    }
//                });

            }
        });






        plalist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(activity5.this, activity17.class));
//                Intent intent = new Intent(activity5.this, activity17.class);
//                intent.putExtra("link2", downloadLink);
//
//                setResult(1, intent);
//                onActivityResult(100, 1, intent);
//                finish();
//                Intent intent = new Intent(activity5.this, activity16.class);
//                onActivityResult(100, 1, i);
//                intent.putExtra("link2", downloadLink);
//
//                setResult(1, intent);
//                onActivityResult(100, 1, intent);
//                finish();

                String uri = "@drawable/ic_baseline_pause_24";
                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                play.setImageDrawable(res);

                mediaPlayer = new MediaPlayer();
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

//        resume_play1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaPlayer.start();
//                        Toast.makeText(
//                                activity5.this, "upload audio",
//                                Toast.LENGTH_LONG).show();
//
//            }
//        });



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