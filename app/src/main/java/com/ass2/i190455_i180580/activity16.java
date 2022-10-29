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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class activity16 extends AppCompatActivity {

    ImageButton imgbtn1;
    ImageView resumeplay;
    MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
    List<Upload> songsList;
    Upload currentSong;
    ImageView previoussong, nextsong;
    SeekBar zerseekbar;
    TextView timeofsong;


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


                mediaPlayer = new MediaPlayer();
                try {
                    songsList = (List<Upload>) getIntent().getSerializableExtra("list3");
                    mediaPlayer.setDataSource(songsList.get(MyMediaPlayer.currentIndex).getSongUrl());

                    try {
                        setResourcesWithMusic();
                        activity16.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mediaPlayer != null) {
                                    zerseekbar.setProgress(mediaPlayer.getCurrentPosition());
                                    timeofsong.setText(convertToMMSS(mediaPlayer.getCurrentPosition() + ""));
                                    if (mediaPlayer.isPlaying()) {
                                        resumeplay.setImageResource(R.drawable.ic_baseline_pause_24);
                                    } else {
                                        resumeplay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                                    }
                                }
                                new Handler().postDelayed(this, 100);
                            }
                        });


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
                if (mediaPlayer != null && fromUser) {
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

public void setResourcesWithMusic() throws IOException {
        currentSong = songsList.get(MyMediaPlayer.currentIndex);


        resumeplay.setOnClickListener(v-> pausePlay());
        nextsong.setOnClickListener(v-> {
        try {
        playNextSong();
        } catch (IOException e) {
        e.printStackTrace();
        }
        });
        previoussong.setOnClickListener(v-> {
        try {
        playPreviousSong();
        } catch (IOException e) {
        e.printStackTrace();
        }
        });

        playMusic();
        }

private void playMusic() throws IOException {
        mediaPlayer.reset();
        mediaPlayer.setDataSource(currentSong.getSongUrl());
        mediaPlayer.prepare();
        mediaPlayer.start();
        zerseekbar.setProgress(0);
        zerseekbar.setMax(mediaPlayer.getDuration());

        }

private void playNextSong() throws IOException {
        if (MyMediaPlayer.currentIndex == songsList.size()-1)
        return;

        MyMediaPlayer.currentIndex +=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
        }

private void playPreviousSong() throws IOException {
        if (MyMediaPlayer.currentIndex == 0)
        return;

        MyMediaPlayer.currentIndex -=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
        }

private void pausePlay(){
        if (mediaPlayer.isPlaying())
        mediaPlayer.pause();

        else
        mediaPlayer.start();
        }

    public static String convertToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }


}
