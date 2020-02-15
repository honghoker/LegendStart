package com.example.ls_listsave.DataLappingByContentValues;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.ls_listsave.DataBase.LSDBHelper;

public class DataLappingByContentValues {
    SQLiteDatabase db;
    String tableName = null;
    Context context;
    LSDBHelper lsdbHelper = null;

    public DataLappingByContentValues(Context context, String tableName) {

        this.tableName = tableName;
        this.context = context.getApplicationContext();
        lsdbHelper = new LSDBHelper(context);
        db = lsdbHelper.getWritableDatabase();
    }

    public ContentValues receiveDataToContentValues(){
        ContentValues lappingCV = new ContentValues();
        return lappingCV;
    }

    public boolean inputInnerDataBase(ContentValues contentValues){
        try {
            if (db.insert(tableName, null, contentValues) > 0) {
                db.close();
                return true;
            }
        }catch (SQLiteException e){}
        db.close();
        return false;

    }

}
