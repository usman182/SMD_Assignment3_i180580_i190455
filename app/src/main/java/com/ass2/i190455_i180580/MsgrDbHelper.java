package com.ass2.i190455_i180580;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MsgrDbHelper extends SQLiteOpenHelper {
    public static String name="musify.db";
    public static int version=1;

    public static String CREATE_CONTACTS_TABLE="CREATE TABLE "+
            MsgrContracts.MyContacts.TABLE_NAME+" ( "+
            MsgrContracts.MyContacts.DISPLAY_NAME+" TEXT, "+
            MsgrContracts.MyContacts.EMAIL+" TEXT, "+
            MsgrContracts.MyContacts.DISPLAY_PIC+" TEXT "+
            ")";

    public static String CREATE_MESSAGES_TABLE="CREATE TABLE "+
            MsgrContracts.MyMessages.TABLE_NAME+" ( "+
            MsgrContracts.MyMessages.MESSAGE+" TEXT, "+
            MsgrContracts.MyMessages.RCVR+" TEXT, "+
            MsgrContracts.MyMessages.HAS_URI+" TEXT, "+
            MsgrContracts.MyMessages.URI+" TEXT, "+
            MsgrContracts.MyMessages.TIME+" TEXT "+
            ")";

    public MsgrDbHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_MESSAGES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}