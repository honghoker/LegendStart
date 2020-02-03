package com.example.androidsqlite;

import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import com.example.androidsqlite.LSSQLContract.*;

        import androidx.annotation.Nullable;

public class LSDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="LSSQL.db";
    private static final int DATABASE_VERSION = 1;

    public LSDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private void createLocationTable(SQLiteDatabase db){
        final String SQL_CREATE_LOCATION_TABLE =
                "CREATE TABLE "+ LocationTable.TABLE_NAME + " (" +
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
        db.execSQL(SQL_CREATE_LOCATION_TABLE);
    }

    private void createTagTable(SQLiteDatabase db){
        final String SQL_CREATE_TAG_TABLE =
                "CREATE TABLE " + TagTable.TABLE_NAME + " (" +
                        TagTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TagTable.COLUMN_FOREIGNKEY_LOCATION_SEQ + " INTEGER NOT NULL, " +
                        TagTable.COLUMN_TAG_1 + " TEXT," +
                        TagTable.COLUMN_TAG_2 + " TEXT," +
                        TagTable.COLUMN_TAG_3 + " TEXT," +
                        TagTable.COLUMN_TAG_4 + " TEXT," +
                        TagTable.COLUMN_TAG_5 + " TEXT," +
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
}
