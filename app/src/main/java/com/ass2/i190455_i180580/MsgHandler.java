package com.ass2.i190455_i180580;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MsgHandler {
    private static MsgHandler msgHandler;
    private MsgrDbHelper db;
    Context c;
    String url="http://192.168.10.5";

    String sendMsg_url=url+"/smda/a3/send_msg.php";
    String getMsg_url=url+"/smda/a3/get_msg";

    private MsgHandler() {
    }

    public static synchronized MsgHandler getInstance(Context c){
        if(msgHandler==null){
            msgHandler=new MsgHandler();
        }
        msgHandler.c=c;
        return msgHandler;
    }

    public void sendMsg(ChatMessage message){
//        Post message to server db
//        If message contains file, run FileHandler (for now, only ImageHandler)
        RequestQueue queue= Volley.newRequestQueue(c);
        StringRequest request=new StringRequest(Request.Method.POST, sendMsg_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject obj=new JSONObject(response);
                    Log.d("Response_Sent",obj.getString("msg"));
                    if(obj.getInt("code")==1){
                        message.setMsgId(obj.getString("msgId"));
                    }
                }
                catch(JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("sndr",message.getUid());
                params.put("rcvr",message.getRcvr());
                params.put("time",message.getMessageTime());
                params.put("txt",message.getMessageText());
                params.put("has_img",message.getHas_img());
                return params;
            }
        };

        queue.add(request);
        if(message.isHas_uri()){
            ImageHandler imageHandler=ImageHandler.getInstance(c);
            imageHandler.sendImage(Uri.parse(message.getUri()), message.getMsgId());
        }

    }

    public void getMsg(List<ChatMessage> ls){
//        This method is called repeatedly in intervals
//        Gets the latest message where the curent user is listed as a reciever
        RequestQueue queue= Volley.newRequestQueue(c);
    }

    public void getMsgTest(String uid){

        RequestQueue queue=Volley.newRequestQueue(c);

        StringRequest request=new StringRequest(Request.Method.POST, "http://192.168.10.5/smda/a3/get_msg_test.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject obj=new JSONObject(response);
                    Log.d("Response_Sent",obj.getString("message"));
                    JSONArray msgs=obj.getJSONArray("messages");

                    for(int i=0;i<msgs.length();i++){
                        JSONObject temp=msgs.getJSONObject(i);

                        Log.d("messages_to_me",temp.getString("txt"));
                    }
                }
                catch(JSONException e){
                    Log.d("messages_to_me",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("uid",uid);
                return params;
            }
        };
        queue.add(request);
    }

    public void sync(){
//        When application starts, run this after sign-in/sign-up
//        Retrieve all messages that are not already cached in the application
//        Store new messages to db
        db=new MsgrDbHelper(c);
        SQLiteDatabase helper=db.getWritableDatabase();

    }

    public void sendAudio(String filepath){}

}
