package com.example.android_finalproject.data;

import android.provider.BaseColumns;

public class Contract {
    public static final class LocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "location";
        public static final String COLUMN_longitude = "longitude";
        public static final String COLUMN_latitude = "latitude";
        public static final String COLUMN_Name = "name";
    }
}
