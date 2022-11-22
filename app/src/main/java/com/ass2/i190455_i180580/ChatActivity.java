package com.ass2.i190455_i180580;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {
    EditText msgBox;
    ImageView sendMsg,camera,profile_pic;
    String message;
    TextView display_name;


    ChatAdapter adapter;
    List<ChatMessage> ls;
    RecyclerView rv;
    boolean retrieve_old_msgs;

    MsgrDbHelper helper=MsgrDbHelper.getInstance(ChatActivity.this);
    SQLiteDatabase db=helper.getWritableDatabase();
//    SQLiteDatabase dbr=helper.getReadableDatabase();

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
        profile_pic=findViewById(R.id.profile_pic);

        contact_name=getIntent().getStringExtra("display name");
        contact_email=getIntent().getStringExtra("email");
        display_name.setText(contact_name);

        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeScreenShot(getWindow().getDecorView().getRootView(), "abc");
            }
        });
        if(helper==null){
            Log.d("helper","is null");
        }
        else{
            Log.d("helper","is not null");

        }
        ls=new ArrayList<>();
        getChatHistory();
        RecyclerView.LayoutManager lm=new LinearLayoutManager(ChatActivity.this);
        adapter=new ChatAdapter(ls,ChatActivity.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i,"Browse Images"),33);
            }
        });

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
                cv.put(MsgrContracts.MyMessages.URI,"no");
//                Send msg to server,then get generated msgID from there
                msgHandler.sendMsg(temp);
                cv.put(MsgrContracts.MyMessages.MSG_ID,temp.getMsgId());
                db.insert(MsgrContracts.MyMessages.TABLE_NAME,null,cv);

                ls.add(temp);
                adapter.notifyDataSetChanged();

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
    public void getChatHistory(){
        ChatMessage temp;
        String message,rcvr,uri,has_uri,time,msg_id;
        String[] whereArgs={
                contact_email,
                contact_email
        };
        Cursor c=db.query(MsgrContracts.MyMessages.TABLE_NAME,null,"receiver=? OR sender=?",whereArgs,
                null,null,MsgrContracts.MyMessages.MSG_ID+" ASC");
        while(c.moveToNext()){
            message=(c.getString(c.getColumnIndexOrThrow(MsgrContracts.MyMessages.MESSAGE)));
            rcvr=(c.getString(c.getColumnIndexOrThrow(MsgrContracts.MyMessages.RCVR)));
            uri=(c.getString(c.getColumnIndexOrThrow(MsgrContracts.MyMessages.URI)));
            has_uri=(c.getString(c.getColumnIndexOrThrow(MsgrContracts.MyMessages.HAS_URI)));
            time=(c.getString(c.getColumnIndexOrThrow(MsgrContracts.MyMessages.TIME)));
            msg_id=(c.getString(c.getColumnIndexOrThrow(MsgrContracts.MyMessages.MSG_ID)));

            temp=new ChatMessage(webAuth.getCurrentUser().getEmail(),rcvr,message);
            if(Objects.equals(rcvr, contact_email)){
                temp=new ChatMessage(webAuth.getCurrentUser().getEmail(),rcvr,message);
            }
            else{
                temp=new ChatMessage(rcvr,webAuth.getCurrentUser().getEmail(),message);
            }
            temp.setUri(uri);
            temp.setHas_img(has_uri);
            temp.setMessageTime(time);
            temp.setMsgId(msg_id);
            ls.add(temp);
        }
    }

    @Override
    protected void onStop() {
//        db.close();
//        dbr.close();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==33 && resultCode==RESULT_OK){
            Uri filepath=data.getData();
            ChatMessage temp=new ChatMessage(WebAuth.getInstance(ChatActivity.this).getCurrentUser().getEmail()
                    ,contact_email,null);
            temp.setUri(filepath.toString());
            msgHandler.sendMsg(temp);
            ls.add(temp);
            adapter.notifyDataSetChanged();
            ImageHandler.getInstance(ChatActivity.this).sendImage(filepath,temp.getMsgId());

        }
    }

    // method for screenshot
    protected static File takeScreenShot(View view, String fileName) {
        Date date = new Date();
        CharSequence format = DateFormat.getDateInstance().format(date);

        try {
            String dirPath = Environment.getExternalStorageDirectory().toString()+"/chatapplicationphotos";
            File fileDir = new File(dirPath);
            fileDir.mkdir();

            String path = dirPath + "/" + fileName + "-" + format + ".jpeg";

            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File imageFile = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return imageFile;
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}