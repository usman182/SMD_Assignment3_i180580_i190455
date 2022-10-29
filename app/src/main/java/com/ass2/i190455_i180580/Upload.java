package com.ass2.i190455_i180580;

import java.io.Serializable;

public class Upload implements Serializable {
    private String mName;
    private String mSongURL;

    public Upload() {

    }

    public Upload(String name, String songLink) {
        mName = name;
        mSongURL = songLink;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSongUrl() {
        return mSongURL;
    }

    public void setSongUrl(String songUrl) {
        mSongURL = songUrl;
    }
}