package com.ass2.i190455_i180580;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

public class activity12 extends AppCompatActivity {

    ImageButton imgbtn1, startrecording;
    TextView recordingtimer;
    List<Upload> songsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_12);

        imgbtn1 = findViewById(R.id.back);
        recordingtimer = findViewById(R.id.recordingtimer);
        startrecording = findViewById(R.id.startrecordingbutton);

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity12.this, activity11.class));
            }
        });

        startrecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(activity12.this, activity13.class));

                starttherecording();

            }
        });
    }

    void starttherecording() {
        //System.out.println("Hello");


        Intent intent = new Intent(activity12.this, activity13.class);
        songsList = (List<Upload>) getIntent().getSerializableExtra("list2");
        intent.putExtra("list2", (Serializable) songsList);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}