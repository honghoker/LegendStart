package com.example.ls_listsave.DataLappingByContentValues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.ls_listsave.DataBase.LSSQLContract.*;


public class DataLapping_LocationData extends DataLappingByContentValues {

    private String locationName = null;
    private String address = null;
    private String detailAddr = null;
    private String phone = null;
    private String comment = null;

    public DataLapping_LocationData(Context context, final String tablename, String locationName, String address, String detailAddr, String phone, String comment){
        super(context, tablename);
        this.locationName = locationName;
        this.address = address;
        this.detailAddr = detailAddr;
        this.phone = phone;
        this.comment = comment;
    }

    @Override
    public ContentValues receiveDataToContentValues() {
        ContentValues cv = new ContentValues();

        cv.put(LocationTable.COLUMN_NAME,locationName);
        cv.put(LocationTable.COLUMN_ADDRESS,address);
        cv.put(LocationTable.COLUMN_DETAILADDRESS,detailAddr);
        cv.put(LocationTable.COLUMN_PHONE, phone);
        cv.put(LocationTable.COLUMN_MEMO, comment);

        return cv;
    }

    public boolean storeConfirm(){
        boolean flag = inputInnerDataBase(receiveDataToContentValues());
        if(flag)
            Toast.makeText(context, locationName + "가 추가되었습니다", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"DB추가 실패",Toast.LENGTH_SHORT).show();

        return flag;
    }

    public int idNumber(){
        db = lsdbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + ";", null);
        cursor.moveToLast();

        return cursor.getInt(cursor.getColumnIndex(LocationTable._ID));
    }

}
