package com.example.ls_listsave.DataLappingByContentValues;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.ls_listsave.DataBase.LSSQLContract.*;


import java.util.ArrayList;

public class DataLapping_Tag extends DataLappingByContentValues {
    private ArrayList<String> arrayList;
    private String ar;
    private int foreign_id = 0;

    public DataLapping_Tag(Context context, String tableName, String arrayList, int foreign_id) {
        super(context, tableName);
        this.ar = arrayList;
        this.foreign_id = foreign_id;
    }

    @Override
    public ContentValues receiveDataToContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TagTable.COLUMN_TAG, ar);
        Log.d("tag", ar);
        contentValues.put(TagTable.COLUMN_FOREIGNKEY_LOCATION_SEQ, foreign_id);
        return contentValues;
    }
}
