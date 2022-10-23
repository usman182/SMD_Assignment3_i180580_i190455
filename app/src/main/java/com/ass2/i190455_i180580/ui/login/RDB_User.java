package com.ass2.i190455_i180580.ui.login;

public class RDB_User {
    String email,display_name,uri;

    public RDB_User(String email, String display_name, String uri) {
        this.email = email;
        this.display_name = display_name;
        this.uri = uri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
