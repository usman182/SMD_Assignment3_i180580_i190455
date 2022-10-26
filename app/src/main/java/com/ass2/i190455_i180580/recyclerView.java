package com.ass2.i190455_i180580;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

public class recyclerView extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<AudioModel> songsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recycler_view);

        if(checkPermission() == false){
            requestPermission();
            return;
        }


        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC +" != 0";


            Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, null);


            while (cursor.moveToNext()) {
                AudioModel songsData = new AudioModel(cursor.getString(1), cursor.getString(0), cursor.getString(2));
                if (new File(songsData.getPath()).exists())
                    songsList.add(songsData);
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MusicListAdaptor(songsList, getApplicationContext()));


    }

    boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(recyclerView.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(recyclerView.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(recyclerView.this,"READ PERMISSION IS REQUIRED,PLEASE ALLOW FROM SETTTINGS",Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(recyclerView.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(recyclerView!=null){
            recyclerView.setAdapter(new MusicListAdaptor(songsList,getApplicationContext()));
        }
    }

}