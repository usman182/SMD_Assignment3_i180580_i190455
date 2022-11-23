package com.ass2.i190455_i180580;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HoldandReleaseTest extends AppCompatActivity {
    TextView tv;
    Button bt;
    Boolean held=false;
    Boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holdand_release_test);
        tv=findViewById(R.id.tv);
        bt=findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("Clicked");
                clicked=true;
            }
        });

        bt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    //do something when let go
                    if(!held) {
                        tv.setText("Goodbye");
                        held=true;
                        return held;
                    }
                }
                return false;
            }

        });
        bt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                tv.setText("LongCLick");
                held=false;
                return held;
            }
        });
    }
}