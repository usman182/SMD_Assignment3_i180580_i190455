package com.ass2.i190455_i180580;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity13 extends AppCompatActivity {

    TextView txt1;
    ImageButton imgbtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_13);

        txt1 = findViewById(R.id.next);
        imgbtn1 = findViewById(R.id.back);

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity13.this, activity12.class));
            }
        });

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity13.this, activity14.class));
            }
        });


    }
}