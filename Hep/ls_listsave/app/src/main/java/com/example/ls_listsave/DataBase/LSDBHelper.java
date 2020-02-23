/*package com.example.ls_listsave.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ls_listsave.DataBase.LSSQLContract.*;
import com.example.ls_listsave.DataBase_Room.TagTable;

import androidx.annotation.Nullable;

public class LSDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LSSQL.db";
    private static final int DATABASE_VERSION = 1;

    public LSDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void createLocationTable(SQLiteDatabase db) {
        //SQL_CREATE_LOCATION_TABLE 에 SQLite의 문법에 맞춰서 Table을 만듭니다
        final String SQL_CREATE_LOCATION_TABLE =
                "CREATE TABLE " + LocationTable.TABLE_NAME + " (" +
                        LocationTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        LocationTable.COLUMN_NAME + " TEXT NOT NULL, " +
                        LocationTable.COLUMN_ADDRESS + " TEXT, " +
                        LocationTable.COLUMN_DETAILADDRESS + " TEXT, " +
                        LocationTable.COLUMN_PHONE + " TEXT, " +
                        LocationTable.COLUMN_MEMO + " TEXT, " +
                        LocationTable.COLUMN_LATITUDE + " TEXT, " +
                        LocationTable.COLUMN_LONGITUDE + " TEXT, " +
                        LocationTable.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ");";
        //String SQL_CREATE_LOCATION_TABLE에 저장되어있는 것을 SQL에 주어 DB에 Table을 만듭니다
        db.execSQL(SQL_CREATE_LOCATION_TABLE);
    }

    private void createTagTable(SQLiteDatabase db) {
        final String SQL_CREATE_TAG_TABLE =
                "CREATE TABLE " + com.example.ls_listsave.DataBase_Room.TagTable.TABLE_NAME + " (" +
                        //
                        com.example.ls_listsave.DataBase_Room.TagTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        com.example.ls_listsave.DataBase_Room.TagTable.COLUMN_FOREIGNKEY_LOCATION_SEQ + " INTEGER NOT NULL, " +
                        com.example.ls_listsave.DataBase_Room.TagTable.COLUMN_TAG + " TEXT," +
                        " FOREIGN KEY (" + TagTable.COLUMN_FOREIGNKEY_LOCATION_SEQ + ") " +
                        "REFERENCES " + LocationTable.TABLE_NAME +
                        "(" + LocationTable._ID + "));";
        db.execSQL(SQL_CREATE_TAG_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createLocationTable(db);
        createTagTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

}


 */