package com.example.ls_listsave;

import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.ls_listsave.LSSQLContract.*;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Stack;

public class LocationList extends AppCompatActivity {
    private Stack<ContentValues> undoStack = new Stack<>(); //For Undo
    private static final int GET_ADD_LOCATION_REQUEST_CODE = 200; //For intent
    private RecyclerAdapter recyclerAdapter; //For recyclerview
    private SQLiteDatabase mDatabase; //For recyclerview
    private RecyclerView recyclerView; //For recyclerview
    private Button disSortingButton, updatedSortingButton, nameSortingButton; //For sorting
    private String sortingCondition = LSSQLContract.LocationTable.COLUMN_TIMESTAMP + " DESC"; //For sorting

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        initDB();
        init();

        //Item Swipe method (Left or Right)
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                setUndoStack(recyclerAdapter.returnCursor());
                removeItem((long) viewHolder.itemView.getTag());
                startActivity(new Intent(getApplicationContext(),UndoPopup.class));
            }
        }).attachToRecyclerView(recyclerView);
        //swipe to Right 오른쪽으로 스와이핑
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        }).attachToRecyclerView(recyclerView);
    }


    //Recyclerview Swipe 해서 지우는 메소드
    private void removeItem(long id){
        //id는 swipe하는 행을 말합니다.
        mDatabase.delete(LSSQLContract.LocationTable.TABLE_NAME,
                LSSQLContract.LocationTable._ID + "=" + id, null);
        recyclerAdapter.swapCursor(databaseSortingQueryMethod(sortingCondition));
    }


    private void initDB(){
        LSDBHelper lsdbHelper = new LSDBHelper(this);
        mDatabase = lsdbHelper.getReadableDatabase();
    }

    private void init(){

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSortingMethod(sortingCondition);
        disSortingButton = findViewById(R.id.sort_distanceButton);
        updatedSortingButton = findViewById(R.id.sort_recently);
        nameSortingButton = findViewById(R.id.sort_name);

    }
    private void recyclerViewSortingMethod(String condition){
        Cursor databaseQuery = databaseSortingQueryMethod(condition);
        recyclerAdapter = new RecyclerAdapter(this, databaseQuery);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private Cursor databaseSortingQueryMethod(String sortingCondition){
        //정렬 쿼리문
        Cursor query = mDatabase.query(LSSQLContract.LocationTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortingCondition);
        return query;
    }

    private void setUndoStack(Cursor cursor){
        ContentValues undoContent = undoContentLapping(cursor);
        undoStack.push(undoContent);
    }

    private ContentValues undoContentLapping(Cursor cursor){
        ContentValues cv = new ContentValues();
        cv.put(LocationTable._ID,cursor.getString(cursor.getColumnIndex(LocationTable._ID)));
        cv.put(LocationTable.COLUMN_NAME,cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_NAME)));
        cv.put(LocationTable.COLUMN_ADDRESS,cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_ADDRESS)));
        cv.put(LocationTable.COLUMN_DETAILADDRESS,cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_DETAILADDRESS)));
        cv.put(LocationTable.COLUMN_PHONE,cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_DETAILADDRESS)));
        cv.put(LocationTable.COLUMN_MEMO,cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_MEMO)));
        cv.put(LocationTable.COLUMN_TIMESTAMP,cursor.getString(cursor.getColumnIndex(LocationTable.COLUMN_TIMESTAMP)));

        /*
        cv.put(TagTable._ID, cursor.getString(cursor.getColumnIndex(TagTable._ID)));
        cv.put(TagTable.COLUMN_TAG_1, cursor.getString(cursor.getColumnIndex(TagTable.COLUMN_TAG_1)));
        cv.put(TagTable.COLUMN_TAG_2, cursor.getString(cursor.getColumnIndex(TagTable.COLUMN_TAG_2)));
        cv.put(TagTable.COLUMN_TAG_3, cursor.getString(cursor.getColumnIndex(TagTable.COLUMN_TAG_3)));
        cv.put(TagTable.COLUMN_TAG_4, cursor.getString(cursor.getColumnIndex(TagTable.COLUMN_TAG_4)));
         */
        return cv;
    }

    public void recentSortingOnClick(View view){
        sortingCondition = LSSQLContract.LocationTable.COLUMN_TIMESTAMP + " DESC";
        recyclerViewSortingMethod(sortingCondition);
    }

    public void distanceSortingOnClick(View view){
        //String sorting =
        //recyclerViewSortingMethod(sorting);
        return;
    }

    public void nameSortingOnClick(View view){
        sortingCondition = LSSQLContract.LocationTable.COLUMN_NAME + " ASC";
        recyclerViewSortingMethod(sortingCondition);
    }

    public void AddOnClick(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, GET_ADD_LOCATION_REQUEST_CODE);
    }

    public void undoTextOnClick(View view){
        if(undoStack.empty()) {
            ContentValues temp = undoStack.pop();
            if(mDatabase.insert(LocationTable.TABLE_NAME,null,temp) >0)
                Toast.makeText(getApplicationContext(), "되돌리기 성공", Toast.LENGTH_SHORT).show();
        }
    }
}
