package com.ass2.i190455_i180580;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MsgHome extends AppCompatActivity {
    BottomNavigationView bnv;
    ImageView add;
    MsgrDbHelper helper;
    SQLiteDatabase dbr,dbw;
    Bundle bundle;

//    FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        helper=MsgrDbHelper.getInstance(getApplicationContext());
        dbr=helper.getReadableDatabase();
        dbw=helper.getWritableDatabase();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_home);

        bnv=findViewById(R.id.bnv);
        add=findViewById(R.id.add_bt);

        ChatFragment chatfrag=new ChatFragment(dbr);
        PhoneFragment phonefrag=new PhoneFragment();
        AccountFragment accfrag=new AccountFragment();
        SettingsFragment settingsfrag=new SettingsFragment();
        AddContactFragment addcontact=new AddContactFragment(dbw);


//
//        getSupportFragmentManager().beginTransaction().replace(R.id.view, chatfrag).commit();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.view, addcontact).commit();
            }
        });
        if(savedInstanceState==null){
            MenuItem item=bnv.getMenu().getItem(0);

        }
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.chat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.view, chatfrag).commit();
                        return true;

                    case R.id.call:
                        getSupportFragmentManager().beginTransaction().replace(R.id.view, phonefrag).commit();
                        return true;

                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.view, accfrag).commit();
                        return true;

                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.view, settingsfrag).commit();
                        return true;
                }
                return false;
            }
        });

//        WebAuth.getInstance(MsgHome.this).logContacts(helper,dbr);

    }

    public SQLiteDatabase getReadableDB(){
        return dbr;
    }

    public SQLiteDatabase getWritableDB(){
        return dbw;
    }

    @Override
    protected void onStop() {


        super.onStop();
    }
}