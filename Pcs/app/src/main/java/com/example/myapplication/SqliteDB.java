package com.example.myapplication;

import android.provider.BaseColumns;

public class SqliteDB {

    private  SqliteDB(){}//필요하지 않으므로 private로 생성 후 비어둠
    //Table 생성시 사용할 것들
    public static final class SqliteEntry implements BaseColumns{
        public static final String TABLE_NAME = "sqliteStore";
        public static final String COLUM_ADDRESS = "address";
        public static final String COLUMN_DETAILADDRESS = "detailAddress";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        //public static final String
    }

}
