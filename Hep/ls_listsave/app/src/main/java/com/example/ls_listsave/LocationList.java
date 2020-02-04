package com.example.ls_listsave;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LocationList extends AppCompatActivity {
    private static final int GET_ADD_LOCATION_REQUEST_CODE = 200;
    private RecyclerAdapter recyclerAdapter;
    private SQLiteDatabase mDatabase;
    private RecyclerView recyclerView;
    private Button disSortingButton, updatedSortingButton, nameSortingButton;
    private String sortingCondition = LSSQLContract.LocationTable.COLUMN_TIMESTAMP + " DESC";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        initDB();
        init();

        //Item Swipe method (Left or Right)
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
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

}
