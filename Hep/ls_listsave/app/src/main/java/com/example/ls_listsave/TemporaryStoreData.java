package com.example.ls_listsave;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.ls_listsave.LSSQLContract.*;

public class TemporaryStoreData{

    private String COLUMN_NAME;
    private String _ID;
    private String COLUMN_ADDRESS;
    private String COLUMN_DETAILADDRESS;
    private String COLUMN_PHONE;
    private String COLUMN_MEMO;
    private String TIMESTAMP;
    private String COLUMN_LATITUDE ;
    private String COLUMN_LONGITUDE;

    public Cursor findCursorUsingID(SQLiteDatabase db, long id){
        String QUERY = "SELECT * FROM " + LocationTable.TABLE_NAME + " WHERE " + LocationTable._ID +
                " = " + id + ";";
        Cursor queryRowCursor = db.rawQuery(QUERY,null);

        return queryRowCursor;
    }


    private void settingUndoDB(Cursor cursor){
        try {
            if(cursor.moveToFirst()) {
                do {
                    _ID = cursor.getString(cursor.getColumnIndex(LocationTable._ID));
                    COLUMN_NAME = cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_NAME));
                    COLUMN_ADDRESS = cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_ADDRESS));
                    COLUMN_DETAILADDRESS = cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_DETAILADDRESS));
                    COLUMN_PHONE = cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_PHONE));
                    COLUMN_MEMO = cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_MEMO));
                    TIMESTAMP = cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_TIMESTAMP));
                    COLUMN_LATITUDE = cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_LATITUDE));
                    COLUMN_LONGITUDE = cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_LONGITUDE));
                }while(cursor.moveToNext());
            }
        }catch (Exception e){
            throw e;
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }

    }

    public ContentValues undoDataContentValue(TemporaryStoreData tempDB){
        ContentValues cv = new ContentValues();

        cv.put(LocationTable._ID,tempDB._ID);
        cv.put(LocationTable.COLUMN_NAME,tempDB.COLUMN_NAME);
        cv.put(LocationTable.COLUMN_ADDRESS,tempDB.COLUMN_ADDRESS);
        cv.put(LocationTable.COLUMN_DETAILADDRESS,tempDB.COLUMN_DETAILADDRESS);
        cv.put(LocationTable.COLUMN_PHONE, tempDB.COLUMN_PHONE);
        cv.put(LocationTable.COLUMN_MEMO, tempDB.COLUMN_MEMO);
        cv.put(LocationTable.COLUMN_TIMESTAMP,tempDB.TIMESTAMP);
        cv.put(LocationTable.COLUMN_LATITUDE,tempDB.COLUMN_LATITUDE);
        cv.put(LocationTable.COLUMN_LONGITUDE,tempDB.COLUMN_LONGITUDE);

        return cv;
    }
    public TemporaryStoreData recoverDataMethod(SQLiteDatabase db, long id) {

        TemporaryStoreData recoverDB = new TemporaryStoreData();

        Cursor cursor = recoverDB.findCursorUsingID(db, id);
        recoverDB.settingUndoDB(cursor);
        return recoverDB;
    }


}
