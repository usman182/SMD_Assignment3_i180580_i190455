package com.ass2.i190455_i180580;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

public class activity11 extends AppCompatActivity {

    ImageButton imgbtn1, startrecordingbutton;
    List<Upload> songsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_11);

        imgbtn1 = findViewById(R.id.back);
        startrecordingbutton = findViewById(R.id.startrecording);

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity11.this, activity10.class));
            }
        });

        startrecordingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(activity11.this, activity12.class));

                Intent intent = new Intent(activity11.this, activity12.class);
                songsList = (List<Upload>) getIntent().getSerializableExtra("list");

                intent.putExtra("list2", (Serializable) songsList);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}