package com.ass2.i190455_i180580;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage implements Comparable<ChatMessage> {

    private String messageText;
    private String messageUser;
    private String messageTime;
    private String messageDate;
    private Date ms;
    private String uid;
    private String rcvr;
    private String uri;
    private boolean has_uri = false;
    private String has_img="false"; //false
    private String msgId;
    private String audio="none";
    private String has_audio="false";




    public ChatMessage(String uid, String rcvr, String messageText){
        this.messageText=messageText;
        this.uid=uid;
        this.rcvr=rcvr;
        this.messageTime=dateFormat();
        //        this.ms=new Date();
    }

    public ChatMessage(){

    }
    public static String dateFormat(){
        @SuppressLint("SimpleDateFormat")
        DateFormat df=new SimpleDateFormat("hh:mm");
        return df.format(new Date().getTime());
    }
    public static String dateFormatDay(){
        @SuppressLint("SimpleDateFormat")
        DateFormat df;
        df=new SimpleDateFormat("day");
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

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public Date getMs() {
        return ms;
    }

    public void setMs(Date ms) {
        this.ms = ms;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        if(uri.equals("no")){
            this.uri=uri;
            return;
        }
        this.uri = uri;
        this.has_uri=true;
        this.has_img="true";
    }

    public boolean isHas_uri() {
        return has_uri;
    }

    public void setHas_uri(boolean has_uri) {
        this.has_uri = has_uri;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getHas_img() {
        return has_img;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
        if(!audio.equals("none")){
            this.has_audio="true";
        }
    }

    public String getHas_audio() {
        return has_audio;
    }

    public void setHas_audio(String has_audio) {
        this.has_audio = has_audio;
    }

    public void setHas_img(String has_img) {
        this.has_img = has_img;
        if(has_img.equals("false")){
            this.has_uri=false;
        }
        else{
            this.has_uri=true;
        }
    }

    @Override
    public int compareTo(ChatMessage u) {
        if (getMs() == null || u.getMs() == null) {
            return 0;
        }
        return getMs().compareTo(u.getMs());
    }
}
