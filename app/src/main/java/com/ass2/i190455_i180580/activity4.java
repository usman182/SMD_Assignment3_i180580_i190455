package com.ass2.i190455_i180580;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity4 extends AppCompatActivity {

    TextView txt1;
    Button btn1;
    Button btn2;
    Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        txt1 = findViewById(R.id.signin);

        btn1 = findViewById(R.id.continuewithfacebook);
        btn2 = findViewById(R.id.continuewithgoogle);
        btn3 = findViewById(R.id.createaccount);

        // toasts when clicking the create account button
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    startActivity(new Intent(activity4.this, activity2.class));


            }
        });


        // for sign in
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(activity4.this, activity3.class));
            }
        });


    }
}