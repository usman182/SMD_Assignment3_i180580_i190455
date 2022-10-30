package com.ass2.i190455_i180580;

import java.io.Serializable;

public class SongInfo implements Serializable {
    private String songName;
    private String songURL;
    private String playlistName;

    public SongInfo() {

    }

    public SongInfo(String name, String songLink) {
        songName = name;
        songURL = songLink;
    }

    public String getName() {
        return songName;
    }

    public void setName(String name) {
        songName = name;
    }

    public String getSongUrl() {
        return songURL;
    }

    public void setSongUrl(String songUrl) {
        songURL = songUrl;
    }


    public void setPlaylistName(String playlistName) {    this.playlistName = playlistName;    }

    public String getPlaylistName() {    return playlistName;    }
}