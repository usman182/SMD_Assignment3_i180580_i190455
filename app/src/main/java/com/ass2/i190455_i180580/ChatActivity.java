package com.ass2.i190455_i180580;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {
    EditText msgBox;
    ImageView sendMsg,camera;
    String message;
    TextView display_name;


    ChatAdapter adapter;
    List<ChatMessage> ls;
    RecyclerView rv;
    boolean retrieve_old_msgs;

    MsgrDbHelper helper=new MsgrDbHelper(ChatActivity.this);
    SQLiteDatabase db=helper.getWritableDatabase();
    SQLiteDatabase dbr=helper.getReadableDatabase();

    WebAuth webAuth=WebAuth.getInstance(ChatActivity.this);
    MsgHandler msgHandler=MsgHandler.getInstance(ChatActivity.this);

    String contact_name,contact_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        msgBox=findViewById(R.id.msg_box);
        sendMsg=findViewById(R.id.send_msg);
        rv=findViewById(R.id.chat_history);
        camera=findViewById(R.id.take_pic);
        display_name=findViewById(R.id.name);

        contact_name=getIntent().getStringExtra("display name");
        contact_email=getIntent().getStringExtra("email");
        display_name.setText(contact_name);



        ls=new ArrayList<>();
        RecyclerView.LayoutManager lm=new LinearLayoutManager(ChatActivity.this);
        adapter=new ChatAdapter(ls,ChatActivity.this);
        rv.setLayoutManager(lm);

        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                message=msgBox.getText().toString();
                ChatMessage temp=new ChatMessage(webAuth.getCurrentUser().getEmail(),contact_email,message);

                ContentValues cv=new ContentValues();
                cv.put(MsgrContracts.MyMessages.MESSAGE,message);
                cv.put(MsgrContracts.MyMessages.SNDR,webAuth.getCurrentUser().getEmail());
                cv.put(MsgrContracts.MyMessages.RCVR,contact_email);
                cv.put(MsgrContracts.MyMessages.TIME,temp.getMessageTime());
                cv.put(MsgrContracts.MyMessages.HAS_URI,temp.getHas_img());
//                Send msg to server,then get generated msgID from there
                msgHandler.sendMsg(temp);
                cv.put(MsgrContracts.MyMessages.MSG_ID,temp.getMsgId());
                db.insert(MsgrContracts.MyMessages.TABLE_NAME,null,cv);

                ls.add(temp);

                msgBox.setText("");
                retrieve_old_msgs=false;
            }
        });
    }

    public boolean returnChat(String uid,String rcvr){
//        For only retrieving mesages between user and specific contact
        String userEmail=webAuth.getCurrentUser().getEmail();
        if(Objects.equals(rcvr, contact_email) && Objects.equals(uid, userEmail)){
            return true;
        }
        else{
            return Objects.equals(userEmail, rcvr) && Objects.equals(uid, contact_email);
        }
    }
}