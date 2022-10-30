package com.ass2.i190455_i180580;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class activity16 extends AppCompatActivity {

    ImageButton imgbtn1;
    ImageView resumeplay;

    List<SongInfo> songsList;
    SongInfo currentSong;
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

        songsList = (List<SongInfo>) getIntent().getSerializableExtra("list3");


        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity16.this, activity17.class));
            }
        });

        resumeplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mediaPlayer.isPlaying()) {
                    resumeplay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayer.pause();
                } else {
                    resumeplay.setImageResource(R.drawable.ic_baseline_pause_24);
                    mediaPlayer.start();
                }

                    try {
                        if (mediaPlayer.getCurrentPosition() >= 0) {
                                setMusicComponents();
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

        nextsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    mediaPlayer.reset();

                    // for last song
                    if (currentIndexofSong == songsList.size()-1) {
                        mediaPlayer.reset();
                        Toast.makeText(getApplicationContext(), "There is no next song", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    currentIndexofSong++;

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

                    mediaPlayer.reset();
                    // for previous song before any previous
                    if (currentIndexofSong == 0) {
                        mediaPlayer.reset();
                        Toast.makeText(getApplicationContext(), "There is no previous song", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    currentIndexofSong--;

                    setMusicComponents();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
    }

void setMusicComponents() throws IOException {

    currentSong = songsList.get(currentIndexofSong);
    mediaPlayer.reset();
    mediaPlayer.setDataSource(currentSong.getSongUrl());


    activity16.this.runOnUiThread(new Runnable() {
        @Override
        public void run() {
            zerseekbar.setProgress(mediaPlayer.getCurrentPosition());
            timeofsong.setText(converttoMMSS(String.valueOf(mediaPlayer.getCurrentPosition())));



            new Handler().postDelayed(this, 50);
        }
    });

    resumeplay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mediaPlayer.isPlaying()) {
                resumeplay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                mediaPlayer.pause();
            } else {
                resumeplay.setImageResource(R.drawable.ic_baseline_pause_24);
                mediaPlayer.start();
            }
        }
    });


    // play the msuic now
    try {

        mediaPlayer.prepare();
        mediaPlayer.start();
        zerseekbar.setProgress(0);
        zerseekbar.setMax(mediaPlayer.getDuration());
    } catch (IOException e) {
        e.printStackTrace();
    }
}



        String converttoMMSS(String duration) {
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
        }




}
