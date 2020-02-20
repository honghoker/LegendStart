package com.example.ls_listsave.LocationList_RecyclerView;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.location.Location;
import android.widget.Toast;

import com.example.ls_listsave.DataBase.LSDBHelper;
import com.example.ls_listsave.DataBase.LSSQLContract;
import com.example.ls_listsave.DataBase.LSSQLContract.*;
import com.example.ls_listsave.DataLappingByContentValues.DataLappingByContentValues;

public class UndoFactory {

    private long dismiss_ID = 0;
    private SQLiteDatabase database = null;
    private Context context = null;
    private LSDBHelper lsdbHelper = null;
    private RecyclerAdapter recyclerAdapter = null;
    private TemporaryData temporaryData = null;

    public UndoFactory(Context context, long dismiss_ID, RecyclerAdapter recyclerAdapter) {
        this.context = context;
        this.dismiss_ID = dismiss_ID;
        this.recyclerAdapter = recyclerAdapter;
        lsdbHelper = new LSDBHelper(context);
        database = lsdbHelper.getWritableDatabase();
    }


    public TemporaryData setRemoveItem(long id) {
        //id는 swipe하는 행을 말합니다.
        database.delete(LSSQLContract.LocationTable.TABLE_NAME,
                LSSQLContract.LocationTable._ID + "=" + id, null);
        recyclerAdapter.notifyDataSetChanged();
        return onAction();
    }

    private TemporaryData onAction() {
        onFindLocationData();
        TemporaryData temporaryData = new TemporaryData(onFindLocationData(), onFindTag());
        return temporaryData;
    }


    private ContentValues onFindLocationData() {
        ContentValues contentValues = null;
        String QUERY = "SELECT * FROM " + LocationTable.TABLE_NAME + " WHERE " + LocationTable._ID +
                " = " + dismiss_ID + ";";
        Cursor queryRowCursor = database.rawQuery(QUERY, null);

        contentValues = settingUndoDBLocation(queryRowCursor);
        queryRowCursor.close();
        return contentValues;
    }

    private ContentValues onFindTag() {
        ContentValues contentValues = null;
        String QUERYFINDTAG = "SELECT * FROM " + TagTable.TABLE_NAME + " WHERE " + TagTable.COLUMN_FOREIGNKEY_LOCATION_SEQ +
                " = " + dismiss_ID + ";";
        Cursor queryRowCursor = database.rawQuery(QUERYFINDTAG, null);

        if (queryRowCursor.getCount() != 0)
            contentValues = settingUndoDBTag(queryRowCursor);

        queryRowCursor.close();
        return contentValues;
    }

    private ContentValues settingUndoDBLocation(Cursor cursor) {
        ContentValues locationCV = new ContentValues();
        try {
            if (cursor.moveToFirst()) {
                do {
                    locationCV = new ContentValues();
                    locationCV.put(LocationTable._ID, cursor.getString(cursor.getColumnIndex(LocationTable._ID)));
                    locationCV.put(LocationTable.COLUMN_NAME, cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_NAME)));
                    locationCV.put(LocationTable.COLUMN_ADDRESS, cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_ADDRESS)));
                    locationCV.put(LocationTable.COLUMN_DETAILADDRESS, cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_DETAILADDRESS)));
                    locationCV.put(LocationTable.COLUMN_PHONE, cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_PHONE)));
                    locationCV.put(LocationTable.COLUMN_MEMO, cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_MEMO)));
                    locationCV.put(LocationTable.COLUMN_TIMESTAMP, cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_TIMESTAMP)));
                    locationCV.put(LocationTable.COLUMN_LATITUDE, cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_LATITUDE)));
                    locationCV.put(LocationTable.COLUMN_LONGITUDE, cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_LONGITUDE)));

                    onFindTag();
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return locationCV;
    }

    private ContentValues settingUndoDBTag(Cursor cursor) {
        ContentValues tagCV = new ContentValues();
        try {
            tagCV = new ContentValues();

            tagCV.put(TagTable.COLUMN_FOREIGNKEY_LOCATION_SEQ, dismiss_ID);
            tagCV.put(TagTable.COLUMN_TAG, cursor.getString(cursor.getColumnIndex(TagTable.COLUMN_TAG)));
        } catch (Exception e) {
            throw e;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return tagCV;
    }
    public RecyclerAdapter setRecyclerAdapter(){
        return this.recyclerAdapter;
    }
}

class TemporaryData {
    public ContentValues contentValuesLocation = null;
    public ContentValues contentValuesTag = null;
    private Context context = null;
    private SQLiteDatabase sqLiteDatabase = null;
    private LSDBHelper lsdbHelper = null;
    private DataLappingByContentValues dataLappingByContentValues = null;

    public TemporaryData(ContentValues contentValuesLocation, ContentValues contentValuesTag) {
        this.contentValuesLocation = contentValuesLocation;
        this.contentValuesTag = contentValuesTag;
    }

    public boolean onUndo(Context context, RecyclerAdapter recyclerAdapter, int position) {
        String tableName = null;

    }
}