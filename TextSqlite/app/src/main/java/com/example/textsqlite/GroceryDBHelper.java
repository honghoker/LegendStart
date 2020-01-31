package com.example.textsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.textsqlite.GroceryContract.*;

import androidx.annotation.Nullable;

public class GroceryDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "grocerylist.db";
    public static final int DATABASE_VERSION = 1;

    public GroceryDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    /*
    private void sqliteL(SQLiteDatabase db){
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE "+
                GrocertEntry.TABLE_NAME + " (" +
                GrocertEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                GrocertEntry.COLUMN_ADDRESS + " TEXT NOT NULL, "+
                GrocertEntry.COLUMN_DETAILADDRESS+ " INTEGER NOT NULL, "+
                GrocertEntry.COLUM_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
    }

     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE "+
                GrocertEntry.TABLE_NAME + " (" +
                GrocertEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                GrocertEntry.COLUMN_ADDRESS + " TEXT NOT NULL, "+
                GrocertEntry.COLUMN_DETAILADDRESS+ " INTEGER NOT NULL, "+
                GrocertEntry.COLUM_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ GrocertEntry.TABLE_NAME);
        onCreate(db);

    }
}
