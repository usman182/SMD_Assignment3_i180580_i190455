package com.ass2.i190455_i180580;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageHandler {
    private static ImageHandler imageHandler;

    String encodeImageString;
    static int SEND_TO_DP=0;
    static int SEND_TO_IMG=1;
    static int GET_FROM_DP=2;
    static int GET_FROM_IMG=3;
    Context c;
    Bitmap bitmap;

    String sendImage_url="http://192.168.10.5/smda/a3/post_img.php";

    String sendDP_url="http://192.168.10.5/smda/a3/test.php";

    String getDP_url="http://192.168.10.5/smda/a3/get_dp.php";

    RequestQueue queue;

    private ImageHandler(){

    }

    public static synchronized ImageHandler getInstance(Context c){
        if(imageHandler==null){
            imageHandler=new ImageHandler();
        }
        imageHandler.c=c;

        return imageHandler;
    }

    public void sendImage(Uri image, String id){
//        Handles image sending in chats
//        Updates messages table img field with img name
//        Using message id;
        queue= Volley.newRequestQueue(c);
        try{
            InputStream inputStream=c.getContentResolver().openInputStream(image);
            bitmap= BitmapFactory.decodeStream(inputStream);
            encodeBitmapImage(bitmap);
        }
        catch (Exception e){
            Log.d("Stream_Exception",e.toString());
        }

        StringRequest request=new StringRequest(Request.Method.POST, sendImage_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response_Sent",error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map <String,String> params=new HashMap<String,String>();
                WebAuth w=WebAuth.getInstance(c);
                params.put("msgId",id);
                params.put("upload",encodeImageString);
                return params;
            }
        };

//        request.setRetryPolicy(new DefaultRetryPolicy(50000,5,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);
    }

    public void sendDP(Uri dp,String id){
//        Updates or sets DP with uid name
        queue= Volley.newRequestQueue(c);
        try{
            InputStream inputStream=c.getContentResolver().openInputStream(dp);
            bitmap= BitmapFactory.decodeStream(inputStream);
            encodeBitmapImage(bitmap);
        }
        catch (Exception e){
            Log.d("Stream_Exception",e.toString());
        }

        StringRequest request=new StringRequest(Request.Method.POST, sendDP_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response_Sent","DP Upload");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response_Sent",error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map <String,String> params=new HashMap<String,String>();
                WebAuth w=WebAuth.getInstance(c);
                params.put("uid",id);
                params.put("upload",encodeImageString);
                return params;
            }
        };

//        request.setRetryPolicy(new DefaultRetryPolicy(50000,5,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);
    }

    public void getDP(ImageView view){

        queue=Volley.newRequestQueue(c);

        StringRequest request=new StringRequest(Request.Method.GET, getDP_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject obj=new JSONObject(response);
                    String encodedImg;
                    encodedImg =obj.getString("pic");
                    Log.d("Response_Sent",obj.getString("msg"));
                    decodeImageString(encodedImg,view);
                }
                catch(JSONException e){
                    Log.d("JSONE",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response_Sent",error.toString());
            }
        });

        queue.add(request);

    }
    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] bytesofImage=byteArrayOutputStream.toByteArray();

        encodeImageString=android.util.Base64.encodeToString(bytesofImage, Base64.DEFAULT);
        Log.d("Image Encoding","Success");
    }

    private void decodeImageString(String encodeImageString, ImageView view){

        byte[] bytesofImage=android.util.Base64.decode(encodeImageString,Base64.DEFAULT);
        bitmap=BitmapFactory.decodeByteArray(bytesofImage,0,bytesofImage.length);

//        String path= MediaStore.Images.Media.insertImage(view.getContext().getContentResolver(), bitmap,null,null);
        view.setImageBitmap(bitmap);
        Log.d("uri_set","YES");


    }

    private void encodeUri(Uri uri) throws IOException {
        InputStream iStream = c.getContentResolver().openInputStream(uri);
        byte[] inputData = getBytes(iStream);

        encodeImageString=android.util.Base64.encodeToString(inputData,Base64.DEFAULT);
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

}
