package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myapplication.SqliteDB.*; // 이걸 사용 시 SqliteDB.SqliteEntry 를 SqliteEntry로 줄이기 가능


public class SqliteDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SqliteDB";
    public static final int DATABASE_VERSION = 1;
    public SqliteDBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //데이터베이스 Create문
        final String SQL_CREATE_SQLITELIST_TABLE = "CREATE TABLE " +
                SqliteEntry.TABLE_NAME + " (" +
                SqliteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SqliteEntry.COLUM_ADDRESS + " TEXT NOT NULL, "+
                SqliteEntry.COLUMN_DETAILADDRESS + " TEXT NOT NULL, " +
                SqliteEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_SQLITELIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SqliteEntry.TABLE_NAME);
        onCreate(db);
    }
}
