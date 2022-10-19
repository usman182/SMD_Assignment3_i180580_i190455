package com.ass2.i190455_i180580;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity19 extends AppCompatActivity {

    ImageButton imgbtn1, imgbtn2;
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_19);

        imgbtn1 = findViewById(R.id.back);
        imgbtn2 = findViewById(R.id.listenlater1);
        txt1 = findViewById(R.id.listenlater2);

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity19.this, activity18.class));
            }
        });


        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity19.this, activity20.class));
            }
        });

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity19.this, activity20.class));
            }
        });

    }
}