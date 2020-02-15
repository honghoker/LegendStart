package com.example.ls_listsave.DataLappingByContentValues;

import android.content.ContentValues;
import android.content.Context;

import com.example.ls_listsave.DataBase.LSSQLContract.*;


import java.util.ArrayList;

public class DataLapping_Tag extends DataLappingByContentValues {
    private ArrayList<String> arrayList;
    private int foreign_id = 0;

    public DataLapping_Tag(Context context, String tableName, ArrayList arrayList, int foreign_id) {
        super(context, tableName);
        this.arrayList = arrayList;
        this.foreign_id = foreign_id;
    }

    @Override
    public ContentValues receiveDataToContentValues() {
        ContentValues contentValues = new ContentValues();
        int count = 1;

        for(String s : arrayList){
            contentValues.put("tag"+count, s);
            count++;
        }
        contentValues.put(TagTable.COLUMN_FOREIGNKEY_LOCATION_SEQ, foreign_id);
        return contentValues;
    }

}
