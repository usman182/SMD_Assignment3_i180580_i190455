package com.ass2.i190455_i180580;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage {

    private String messageText;
    private String messageUser;
    private String messageTime;
    private String uid;
    private String rcvr;


    public ChatMessage(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;

        // Initialize to current time

        messageTime = dateFormat();
    }

    public ChatMessage(){

    }
    public static String dateFormat(){
        @SuppressLint("SimpleDateFormat")
        DateFormat df=new SimpleDateFormat("hh:mm");
        return df.format(new Date().getTime());
    }
    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime; }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRcvr() {
        return rcvr;
    }

    public void setRcvr(String rcvr) {
        this.rcvr = rcvr;
    }
}
