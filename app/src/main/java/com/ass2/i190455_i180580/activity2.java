package com.ass2.i190455_i180580;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity2 extends AppCompatActivity {

    TextView txt1;
    EditText edtxt1;
    EditText edtxt2;
    EditText edtxt3;
    TextView txt2;
    ImageButton imgbt1;
    ImageButton imgbt2;
    ImageButton imgbt3;
    CheckBox checkBox1;
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        txt1 = findViewById(R.id.signin);

        edtxt1 = findViewById(R.id.name);
        edtxt2 = findViewById(R.id.email);
        edtxt3 = findViewById(R.id.password);

        txt2 = findViewById(R.id.show);

        imgbt1 = findViewById(R.id.male);
        imgbt1 = findViewById(R.id.female);
        imgbt1 = findViewById(R.id.unknown);

        checkBox1 = findViewById(R.id.checkbox);

        btn1 = findViewById(R.id.signupbutton);


    }
}