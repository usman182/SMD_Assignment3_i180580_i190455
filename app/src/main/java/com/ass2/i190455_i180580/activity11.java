package com.ass2.i190455_i180580;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class activity11 extends AppCompatActivity {

    ImageButton imgbtn1;
    ImageButton startrecording;
    MediaRecorder mediaRecorder;
    String filepath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_11);

        imgbtn1 = findViewById(R.id.back);
        startrecording = findViewById(R.id.startrecordingbutton);

        if (isMicrophonePresent()) {
            getMicrophonePermission();
        }


        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity11.this, activity10.class));
            }
        });

        startrecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                            // print the toast message
                            Toast.makeText(activity11.this, "Recording started", Toast.LENGTH_SHORT).show();



                Intent intent = new Intent(activity11.this, activity12.class);
                intent.putExtra("mediafilepath", filepath);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                    }
                });

    }


    public boolean isMicrophonePresent() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
            return true;

        else
            return false;
    }

    private void getMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) ==
                PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.RECORD_AUDIO}, 200);
        }
    }




}