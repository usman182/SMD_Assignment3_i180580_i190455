package com.ass2.i190455_i180580;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class activity16 extends AppCompatActivity {

    ImageButton imgbtn1;
    ImageView resumeplay;

    List<Upload> songsList;
    Upload currentSong;
    ImageView previoussong, nextsong;
    SeekBar zerseekbar;
    TextView timeofsong;
    MediaPlayer mediaPlayer = new MediaPlayer();
    int currentIndexofSong = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_16);

        imgbtn1 = findViewById(R.id.dropdown);
        resumeplay = findViewById(R.id.resumeplay);
        previoussong = findViewById(R.id.previoussong);
        nextsong = findViewById(R.id.nextsong);
        zerseekbar = findViewById(R.id.zeroseekbar);
        timeofsong = findViewById(R.id.timeofsong);


        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity16.this, activity17.class));
            }
        });

        resumeplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    songsList = (List<Upload>) getIntent().getSerializableExtra("list3");
                    mediaPlayer.setDataSource(songsList.get(currentIndexofSong).getSongUrl());

                    try {
                                setMusicComponents();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


        });

        zerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser == true) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }

public void setMusicComponents() throws IOException {
        currentSong = songsList.get(currentIndexofSong);

        resumeplay.setImageResource(R.drawable.ic_baseline_pause_24);
        resumeplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    resumeplay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }

                else {
                    mediaPlayer.start();
                    resumeplay.setImageResource(R.drawable.ic_baseline_pause_24);
                }
            }
        });

        nextsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (currentIndexofSong == songsList.size()-1)
                        return;


                    currentIndexofSong++;
                    mediaPlayer.reset();
                    setMusicComponents();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        previoussong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (currentIndexofSong == 0)
                        return;

                    currentIndexofSong--;
                    mediaPlayer.reset();
                    setMusicComponents();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });




        }

void playMusic() throws IOException {
        mediaPlayer.reset();
        mediaPlayer.setDataSource(currentSong.getSongUrl());
        mediaPlayer.prepare();
        mediaPlayer.start();
        zerseekbar.setProgress(0);
        zerseekbar.setMax(mediaPlayer.getDuration());

        }




}
