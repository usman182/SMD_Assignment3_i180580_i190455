package com.ass2.i190455_i180580;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
//
import android.Manifest;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
public class ImageUploadTest extends AppCompatActivity {

    Button browse, submit,get;

    ImageView img;

    EditText imageName;
    String encodeImageString;
    Bitmap bitmap;
    Uri uri;

    String url="http://192.168.10.5/smda/a3/test.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload_test);

        browse = findViewById(R.id.get);

        submit = findViewById(R.id.upload);

        img = findViewById(R.id.image);

        get=findViewById(R.id.download);

     browse.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Dexter.withContext(ImageUploadTest.this)
                     .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                     .withListener(new PermissionListener() {
                         @Override
                         public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                            Intent i=new Intent(Intent.ACTION_GET_CONTENT);
                            i.setType("image/*");
                            startActivityForResult(Intent.createChooser(i,"Browse Images"),33);
                         }

                         @Override
                         public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                         }

                         @Override
                         public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                             permissionToken.continuePermissionRequest();
                         }
                     }).check();
         }
     });

     submit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
//             uploaddatatodb();
                ImageHandler imageHandler=ImageHandler.getInstance(ImageUploadTest.this);
                imageHandler.sendImage(uri,"1");
         }
     });

     get.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             ImageHandler imageHandler=ImageHandler.getInstance(ImageUploadTest.this);
             ChatMessage temp=new ChatMessage("a","b","c");
             String uri;
             imageHandler.getImage("2",temp);
//
             uri=imageHandler.getUri();
             Log.d("msgUri2",uri);
//             Toast.makeText(ImageUploadTest.this,uri,Toast.LENGTH_LONG).show();

//             Picasso.get().load(temp.getUri()).into(img);

         }
     });


    }

    private void uploaddatatodb() {
        RequestQueue queue= Volley.newRequestQueue(ImageUploadTest.this);

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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
                params.put("upload",encodeImageString);
                return params;
            }
        };

//        request.setRetryPolicy(new DefaultRetryPolicy(50000,5,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==33 && resultCode==RESULT_OK){
            Uri filepath=data.getData();
            uri=filepath;
           Picasso.get().load(uri).into(img);
        }
    }

    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] bytesofImage=byteArrayOutputStream.toByteArray();

        encodeImageString=android.util.Base64.encodeToString(bytesofImage,Base64.DEFAULT);
        Log.d("Encoding","Success");
        Toast.makeText(ImageUploadTest.this,"Yeah",Toast.LENGTH_LONG).show();

    }
}