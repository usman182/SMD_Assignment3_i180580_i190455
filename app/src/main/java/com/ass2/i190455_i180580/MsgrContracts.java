package com.ass2.i190455_i180580;

import android.provider.BaseColumns;

public class MsgrContracts {
    private MsgrContracts() {
    }

    public static class MyContacts implements BaseColumns {
        public static String TABLE_NAME = "contacts";
        public static String DISPLAY_NAME = "dname";
        public static String DISPLAY_PIC = "dp";
        public static String EMAIL = "email";
    }

    public static class MyMessages implements BaseColumns {
        public static String TABLE_NAME = "messages";
        public static String MESSAGE = "message";
        public static String RCVR = "receiver";
        public static String URI = "uri";
        public static String HAS_URI = "has_uri";
        public static String TIME = "time";
    }

}