package com.ass2.i190455_i180580;

import android.content.Context;
import android.net.Uri;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.ass2.i190455_i180580", appContext.getPackageName());
    }

    @Test
    public void signInTest(Context c){
        WebAuth webauth=WebAuth.getInstance(c);
        webauth.SignIn("hasanriaz121gmail.com","12345678");
        Boolean output=webauth.signedIn;
        assertEquals(true,output);
    }

    @Test
    public void setUriTest(){
        ChatMessage temp=new ChatMessage("a","b",null);
        temp.setUri("none");
        Boolean output=temp.isHas_uri();
        assertEquals(false,output);
    }

    @Test
    public void sendImageTest(Context c, Uri uri, String msgId){
        ImageHandler imageHandler=ImageHandler.getInstance(c);
        imageHandler.sendImage(uri,msgId);
        Boolean output=imageHandler.imageSent();
        assertEquals(true,output);
    }


}