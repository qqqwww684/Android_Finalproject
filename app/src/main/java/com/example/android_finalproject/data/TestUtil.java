package com.example.android_finalproject.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put("longitude", 121);
        cv.put("latitude",25);
        cv.put("name","NTUST");
        list.add(cv);

        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete ("location",null,null);

            for(ContentValues c:list){
                db.insert("location", null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}