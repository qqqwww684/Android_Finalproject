package com.example.android_finalproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "location.db";
    private static final int DATABASE_VERSION = 1;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE location (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "longitude REAL NOT NULL, " +
                "latitude REAL NOT NULL, " +
                "name  TEXT" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS location");
        onCreate(sqLiteDatabase);
    }
}
