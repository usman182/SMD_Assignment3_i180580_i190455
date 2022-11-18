package com.ass2.i190455_i180580;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.jetbrains.annotations.Contract;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WebAuth {

    private static WebAuth webAuth;

    User currentUser;
    String password;
    Context c;
    boolean signedIn;

    private WebAuth(){}

    public static synchronized WebAuth getInstance(Context c){
        if(webAuth==null){
            webAuth=new WebAuth();
        }
        webAuth.c=c;
        return webAuth;
    }

    public String getCurrentUserId(){
        return currentUser.uid;
    }

    public void SignIn(String Email,String Password){
//        Verify User
//        Get User dp if set

        String url="http://192.168.10.5/smda/a3/sign_in.php";
        RequestQueue queue= Volley.newRequestQueue(c);
        if(c==null){
            Log.d("NULL_C","NULL C");
        }
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject obj=new JSONObject(response);
                    Log.d("Response_Message",obj.getString("msg"));
                    if(obj.getInt("code")==1){
                        Log.d("Code","1");
                        Integer uid=obj.getInt("uid");
                        currentUser=new User(Email,Password,uid.toString());
                        if(obj.getInt("signedIn")==1) {
                            Toast.makeText(c, "Signed In", Toast.LENGTH_LONG).show();
                            Log.d("User", uid.toString());
                            Log.d("SignedIn","1");
                            signedIn=true;
                            Intent i = new Intent(c, MsgHome.class);
                            c.startActivity(i);

                            ((Activity)c).finish();
                        }
                        else{
                            signedIn=false;
                        }


                    }
                }
                catch(JSONException e)
                {
                    Log.d("Response_Message",e.toString());
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response_Message",error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params=new HashMap<String,String>();
                params.put("email",Email);
                params.put("password",Password);

                return params;
            }
        };

        queue.add(request);


    }

    public void SignUp(String displayName,String Email,String Password,boolean has_dp){

    }

    private void getDP(String uid){
//        Retrieve DP from Server if set
    }

    public void setDp(Uri uri){
//        Set DP in server
//        Dp name is same as uid;
        ImageHandler imageHandler=ImageHandler.getInstance(c);
        imageHandler.sendDP(uri,getCurrentUserId());

    }

    public class User{
        String email;
        String password;
        String uid;

        public User(String email, String password, String uid) {
            this.email = email;
            this.password = password;
            this.uid=uid;
        }
    }

}
