package com.ass2.i190455_i180580;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity8 extends AppCompatActivity {

    ImageView logout;
    TextView username, editprofle, changepassword, messengerhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8);

        logout = findViewById(R.id.logout);
        username = findViewById(R.id.username);
        editprofle = findViewById(R.id.editprofile);
        changepassword = findViewById(R.id.changepassword);
        messengerhome = findViewById(R.id.messengerhome);

        String userFullName = getIntent().getStringExtra("userFullName");
        username.setText(userFullName);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity8.this, activity3.class));
            }
        });

        editprofle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity8.this, activity2.class));
            }
        });

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity8.this, activity2.class));
            }
        });

        messengerhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity8.this, MsgHome.class));
            }
        });
    }
}