package com.ass2.i190455_i180580;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class activity7 extends AppCompatActivity {

    EditText edtxt1;
    ImageButton imgbtn1, imgbtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);

        edtxt1 = findViewById(R.id.postacomment);
        imgbtn1 = findViewById(R.id.back);
        imgbtn2 = findViewById(R.id.comment);

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity7.this, activity6.class));
            }
        });

        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity7.this, activity9.class));
            }
        });
    }
}