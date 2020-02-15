package com.example.ls_listsave.DataBase;

import android.provider.BaseColumns;

public class LSSQLContract {
    public static final class LocationTable{
        //BaseColumn의 ID는 Android에서 BaseColumn Interface에
        //정의되어있는 Column명으로  interger key column name으로 주로 사용합니다
        //_id는 Autoincrement로 사용하여 기본키로 사용을 많이 합니다

        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "locationTable";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_DETAILADDRESS = "detailAddress";
        public static final String COLUMN_PHONE= "phone";
        public static final String COLUMN_MEMO = "memo";
        public static final String COLUMN_LATITUDE= "latitude";
        public static final String COLUMN_LONGITUDE= "longitude";
        //Timestamp같은 경우는 사용자가 DB에 등록을 한 후 등록순으로 정렬할 때 사용하기위해
        //즉 최신순으로 정렬하기 위해 사용하는 경우가 많습니다.
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


