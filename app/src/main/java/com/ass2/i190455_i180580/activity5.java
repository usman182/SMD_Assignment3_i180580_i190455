package com.ass2.i190455_i180580;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class activity5 extends AppCompatActivity {

    ImageView img1, img2;
    ImageButton imgbtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        img1 = findViewById(R.id.hamburger1);
        img2 = findViewById(R.id.personicon);

//        imgbtn1 = findViewById(R.id.play);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity5.this, activity8.class));
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity5.this, activity6.class));
            }
        });

//        imgbtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(activity5.this, activity9.class));
//            }
//        });

    }
}