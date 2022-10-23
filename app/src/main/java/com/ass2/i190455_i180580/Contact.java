package com.ass2.i190455_i180580;

public class Contact {
    String name;
    String dp;
    String status;
    String latestMsg;
    String email;

    public Contact(String name, String dp, String latestMsg) {
        this.name = name;
        this.dp = dp;
        if(latestMsg==null) {
            this.latestMsg = "Hi, I'm using Musify";
        }
        else{
            this.latestMsg=latestMsg;
        }
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatestMsg() {
        return latestMsg;
    }

    public void setLatestMsg(String latestMsg) {
        this.latestMsg = latestMsg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
