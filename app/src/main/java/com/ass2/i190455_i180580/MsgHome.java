package com.ass2.i190455_i180580;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MsgHome extends AppCompatActivity {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_home);

        bnv=findViewById(R.id.bnv);

        ChatFragment chatfrag=new ChatFragment();
        PhoneFragment phonefrag=new PhoneFragment();
        AccountFragment accfrag=new AccountFragment();
        SettingsFragment settingsfrag=new SettingsFragment();
        bnv.setSelectedItemId(R.id.chat);
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

    }


}