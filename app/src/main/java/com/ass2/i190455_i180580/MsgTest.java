package com.ass2.i190455_i180580;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MsgTest extends AppCompatActivity {
    TextView sent,rcvd;
    EditText msg;
    Button send;

    MsgrDbHelper helper=new MsgrDbHelper(MsgTest.this);
    SQLiteDatabase db=helper.getWritableDatabase();
    SQLiteDatabase dbr=helper.getReadableDatabase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MsgHandler msgHandler=MsgHandler.getInstance(MsgTest.this);
        setContentView(R.layout.activity_msg_test);
        sent=findViewById(R.id.sent);
        rcvd=findViewById(R.id.recieved);
        send=findViewById(R.id.send);
        msg=findViewById(R.id.msg);
        rcvd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CLick","YES");
                msgHandler.getMsgTest("hasanriaz121@gmail.com");
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChatMessage temp=new ChatMessage("f1@gmail.com",WebAuth.getInstance(MsgTest.this).getCurrentUser().getEmail(),
                        msg.getText().toString());

                msgHandler.sendMsg(temp);
            }
        });
        String cols[]={
                MsgrContracts.MyMessages.MESSAGE,
                MsgrContracts.MyMessages.RCVR,
                MsgrContracts.MyMessages.TIME,
                MsgrContracts.MyMessages.HAS_URI,
        };
        String[] args={
                "f1@gmail.com",
                "hasanriaz121@gmail.com"
        };
        String[] selectionArgs={
                MsgrContracts.MyMessages.RCVR,
                MsgrContracts.MyMessages.SNDR
        };
        Cursor c=db.query("SELECT * FROM "+MsgrContracts.MyMessages.TABLE_NAME,
                cols, MsgrContracts.MyMessages.RCVR,args,null,null,null);
    }
}