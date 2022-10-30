package com.ass2.i190455_i180580.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.ass2.i190455_i180580.MainActivity;
import com.ass2.i190455_i180580.MsgrContracts;
import com.ass2.i190455_i180580.MsgrDbHelper;
import com.ass2.i190455_i180580.R;
import com.ass2.i190455_i180580.ui.login.RDB_User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateContactFragment extends Fragment {



    EditText name,email;
    Button bt;

    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    MsgrDbHelper helper=new MsgrDbHelper(getContext());
    //SQLiteDatabase db=helper.getWritableDatabase();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_create_contact, container, false);
        name=view.findViewById(R.id.display_name);
        email=view.findViewById(R.id.email);
        bt=view.findViewById(R.id.add_contact);
        mAuth=FirebaseAuth.getInstance();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),"12345678")
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
//                        myRef.child("users").push().setValue(new RDB_User(email.getText().toString(),name.getText().toString(),"l"));
//                        if(mAuth.getCurrentUser()!=null) {
//                            myRef.child("contacts").child(mAuth.getCurrentUser().getEmail()).push().setValue(email.getText().toString());
//                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
//                                myRef.child("users").push().setValue(new RDB_User(email.getText().toString(),name.getText().toString(),"l"));
                                if(mAuth.getCurrentUser()!=null) {
                                    myRef.child("contacts").child(mAuth.getCurrentUser().getUid()).push().setValue(new RDB_User(email.getText().toString(),name.getText().toString(),"l"));
                                    ContentValues cv=new ContentValues();
                                    cv.put(MsgrContracts.MyContacts.DISPLAY_NAME,name.getText().toString());
                                    cv.put(MsgrContracts.MyContacts.EMAIL,email.getText().toString());
                                    cv.put(MsgrContracts.MyContacts.DISPLAY_PIC,"l");
                                    //db.insert(MsgrContracts.MyContacts.TABLE_NAME,null,cv);
                                }

                            }
                        });
                Toast.makeText(view.getContext(),"hello",Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Toast.makeText(view.getContext(),"hello",Toast.LENGTH_SHORT).show();
//        final EditText usernameEditText = binding.username;
//        final EditText passwordEditText = binding.password;
//        final Button loginButton = binding.login;
//        final ProgressBar loadingProgressBar = binding.loading;
//        name=view.findViewById(R.id.display_name);
//        email=view.findViewById(R.id.email);
//        bt=view.findViewById(R.id.add_contact);
//        mAuth=FirebaseAuth.getInstance();
//
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mAuth.createUserWithEmailAndPassword(email.getText().toString(),"12345678");
//                Toast.makeText(view.getContext(),"hello",Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    public void onStop() {
        helper.close();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}