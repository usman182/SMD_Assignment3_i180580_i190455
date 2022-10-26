package com.ass2.i190455_i180580;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class activity10 extends AppCompatActivity {

    TextView txt1;
    Button btn1, btn2;
    ImageButton imgbtn1;
    ImageView upload, musicpic;
    MediaPlayer mediaPlayer;
    FirebaseAuth mAuth;
    EditText title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_10);

        imgbtn1 = findViewById(R.id.back);
        txt1 = findViewById(R.id.next);
        btn1 = findViewById(R.id.upload);
        btn2 = findViewById(R.id.record);
        upload = findViewById(R.id.uploadmus);
        musicpic = findViewById(R.id.musicpic);
        mAuth=FirebaseAuth.getInstance();
        title = findViewById(R.id.title);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (upload == null) {
                    Toast.makeText(
                            activity10.this,
                            "Please select an audio file",
                            Toast.LENGTH_LONG
                    ).show();
                }
                else {
                    startActivity(new Intent(activity10.this, activity5.class));
                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("audio/*");
                startActivityForResult(intent, 100);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity10.this, activity11.class));
            }
        });

        // back button
        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity10.this, activity5.class));
            }
        });

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity10.this, activity11.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (data != null) {

                Uri uri = data.getData();
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference = storage.getReference().child("audio/"+title.getText().toString()+".mp3");
                storageReference.putFile(uri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                                task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Picasso.get().load(uri.toString()).into(upload);
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(
                                        activity10.this, "Failed to upload audio",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

            }
        }
    }
}