package com.ass2.i190455_i180580;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ass2.i190455_i180580.ui.login.CreateContactFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.IOException;

public class MsgHome extends AppCompatActivity {
    BottomNavigationView bnv;
    ImageView add;
    FirebaseAuth mAuth;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_home);

        bnv=findViewById(R.id.bnv);
        add=findViewById(R.id.add_bt);


        ChatFragment chatfrag=new ChatFragment();
        PhoneFragment phonefrag=new PhoneFragment();
        AccountFragment accfrag=new AccountFragment();
        SettingsFragment settingsfrag=new SettingsFragment();
        CreateContactFragment addcontactfrag=new CreateContactFragment();
        bnv.setSelectedItemId(R.id.chat);

        //mAuth=FirebaseAuth.getInstance();
        //mAuth.signInWithEmailAndPassword("hasanriaz121@gmail.com","12345678");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.view, addcontactfrag).commit();
            }
        });
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.chat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.view, chatfrag).commit();
                        return true;

                    case R.id.call:
                        getSupportFragmentManager().beginTransaction().replace(R.id.view, phonefrag).commit();
                        return true;

                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.view, accfrag).commit();
                        return true;

                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.view, settingsfrag).commit();
                        return true;
                }
                return false;
            }
        });

        // if the microphone is present then call the getMicrophone permission
        if (isMicrophonePresent()) {
            getMicrophonePermission();
        }


    }


    // when record button is pressed
    public void btnRecordPressed(View v) {

        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(getRecordingFilePath());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
            mediaRecorder.start();

            Toast.makeText(this, "Recorded Started", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // when stop button is pressed
    public void btnStopPressed(View v) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

        Toast.makeText(this, "Recorded Stopped", Toast.LENGTH_SHORT).show();
    }

    // when play button is pressed
    public void btnPlayPressed(View v) throws IOException {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getRecordingFilePath());
            mediaPlayer.prepare();
            mediaPlayer.start();

        Toast.makeText(this, "Recorded Playing", Toast.LENGTH_SHORT).show();
    }

    // check for the microphone presence
    private boolean isMicrophonePresent() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            return true;
        }

        else {
            return false;
        }
    }

    // get the mircrophone permission
    private void getMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.RECORD_AUDIO}, 100);
        }
    }


    // get the path for storing the audio file
    private String getRecordingFilePath() {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "abc" + ".mp3");
        return file.getPath();
    }


}