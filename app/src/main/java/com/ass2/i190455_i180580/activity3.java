package com.ass2.i190455_i180580;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
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

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);


        txt1 = findViewById(R.id.signup);

        edtxt1 = findViewById(R.id.email);
        edtxt2 = findViewById(R.id.password);

        txt2 = findViewById(R.id.show);

        btn1 = findViewById(R.id.signinbutton);
        mAuth = FirebaseAuth.getInstance();

        // toasts when clicking the sign in button
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtxt1.getText().toString();
                String password = edtxt2.getText().toString();

                if (edtxt1.length() == 0) {
                    Toast.makeText(activity3.this, "Email not entered! Please enter it first!!", Toast.LENGTH_SHORT).show();
                }

                if (edtxt2.length() == 0) {
                    Toast.makeText(activity3.this, "Password not entered! Please enter it first!!", Toast.LENGTH_SHORT).show();
                }

                else {
                    mAuth.signInWithEmailAndPassword(email, password)

                                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                        //FirebaseUser user = mAuth.getCurrentUser();
                                        @Override
                                        public void onSuccess(AuthResult authResult) {
                                            startActivity(new Intent(activity3.this, activity4.class));
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(
                                                    activity3.this,
                                                    "Failed",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });

                }

            }
        });

        // for showing the password
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtxt2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        // for sign up
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(activity3.this, activity2.class));
            }
        });


    }

}