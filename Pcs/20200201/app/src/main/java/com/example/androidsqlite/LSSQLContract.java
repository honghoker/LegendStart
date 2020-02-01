package com.example.androidsqlite;

import android.provider.BaseColumns;

public class LSSQLContract {
    public static final class LocationTable{
        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "locationTable";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_DETAILADDRESS = "detailAddress";
        public static final String COLUMN_PHONE= "phone";
        public static final String COLUMN_MEMO = "memo";
        public static final String COLUMN_LATITUDE= "latitude";
        public static final String COLUMN_LONGITUDE= "longitude";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

    public static final class TagTable{
        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "tagTable";
        public static final String COLUMN_FOREIGNKEY_LOCATION_SEQ = "location_SEQ";
        public static final String COLUMN_TAG_1 = "tag1";
        public static final String COLUMN_TAG_2 = "tag2";
        public static final String COLUMN_TAG_3 = "tag3";
        public static final String COLUMN_TAG_4 = "tag4";
        public static final String COLUMN_TAG_5 = "tag5";
    }
}


