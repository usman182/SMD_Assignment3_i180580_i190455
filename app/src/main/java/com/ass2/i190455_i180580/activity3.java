package com.ass2.i190455_i180580;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity3 extends AppCompatActivity {

    TextView txt1;
    EditText edtxt1;
    EditText edtxt2;
    TextView txt2;
    Button btn1;
    String email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);


        txt1 = findViewById(R.id.signup);

        edtxt1 = findViewById(R.id.email);
        edtxt2 = findViewById(R.id.password);

        txt2 = findViewById(R.id.show);

        btn1 = findViewById(R.id.signinbutton);


        // toasts when clicking the sign in button
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button Press","Pressed");
                email=edtxt1.getText().toString();
                password=edtxt2.getText().toString();
                WebAuth wAuth=WebAuth.getInstance(activity3.this);
                Log.d("email",email);
                Log.d("passsword",password);
                wAuth.SignIn(email,password);
//                if(wAuth.signedIn) {
//                    Intent i = new Intent(activity3.this, MsgHome.class);
//                    startActivity(i);
//                    finish();
//                }
//                WebAuth.User u=wAuth.getCurrentUser();
//                Log.d("User",u.email);
            }
        });

        // for showing the password



    }

}