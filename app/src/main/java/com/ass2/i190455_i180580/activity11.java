package com.ass2.i190455_i180580;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class activity11 extends AppCompatActivity {

    ImageButton imgbtn1, imgbtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_11);

        imgbtn1 = findViewById(R.id.back);
        imgbtn2 = findViewById(R.id.pause1);

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity11.this, activity10.class));
            }
        });

        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity11.this, activity12.class));
            }
        });
    }
}