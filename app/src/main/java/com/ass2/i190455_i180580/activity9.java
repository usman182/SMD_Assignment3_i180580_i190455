package com.ass2.i190455_i180580;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class activity9 extends AppCompatActivity {

      EditText edtxt1;
      EditText edtxt2;
    ImageButton imgbtn1;
    Button btn1;
    ImageView upload, musicpic;
    StorageReference storageReference;
    MediaPlayer mediaPlayer;
    ProgressDialog progressDialog;
    String audioID = null;
    Task<Uri> task;
    String audioLink = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9);

        edtxt1 = findViewById(R.id.playlistname);
        edtxt2 = findViewById(R.id.songnamefromfirebase);
        imgbtn1 = findViewById(R.id.back);
        btn1 = findViewById(R.id.addplaylist);
        upload = findViewById(R.id.uploadmus);
        musicpic = findViewById(R.id.musicpic);


        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity9.this, activity8.class));
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = audioID;
                String downloadLink = audioLink;
                String playlistname = edtxt1.getText().toString();
                Intent intent = new Intent(activity9.this, activity5.class);
                intent.putExtra("title", title);
                intent.putExtra("link", downloadLink);



                intent.putExtra("playlistname", playlistname);
                setResult(1, intent);
                onActivityResult(100, 1, intent);
                finish();

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("audio/*");
//                startActivityForResult(intent, 100);
                  audioID = edtxt2.getText().toString();
                  progressDialog = new ProgressDialog(activity9.this);
                  progressDialog.setMessage("Fetching audio...");
                  progressDialog.setCancelable(false);
                  progressDialog.show();
                  storageReference = FirebaseStorage.getInstance().getReference("audio/"+audioID+".mp3");

                try {
                    File localFile = File.createTempFile("tempFile", ".mp3");
                    storageReference.getFile(localFile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    task = taskSnapshot.getStorage().getDownloadUrl();
                                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            audioLink = uri.toString();
                                            System.out.println("Song name is " + audioLink);
                                        }
                                    });

                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                    Toast.makeText(activity9.this, "Loaded successfully,",
                                            Toast.LENGTH_SHORT).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                    Toast.makeText(activity9.this, "Failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        //String title = data.getStringExtra("title");
//        //edtxt1.setText(title);
//        if (requestCode == 100 && resultCode == RESULT_OK) {
//            if (data != null) {
//
//            }
//        }
//    }
}