package com.example.ls_listsave.LocationList_RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ls_listsave.DataBase.LSDBHelper;
import com.example.ls_listsave.DataBase.LSSQLContract;
import com.example.ls_listsave.MainActivity;
import com.example.ls_listsave.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Stack;

public class LocationList extends AppCompatActivity {
    //private Stack<UndoFactory> undoDataStack = new Stack<>(); //추후 undo생각
    private static final int GET_ADD_LOCATION_REQUEST_CODE = 200; //For intent
    public RecyclerAdapter recyclerAdapter; //For recyclerview
    private SQLiteDatabase mDatabase; //For recyclerview
    private RecyclerView recyclerView; //For recyclerview
    private Button disSortingButton, updatedSortingButton, nameSortingButton; //For sorting
    private String sortingCondition = LSSQLContract.LocationTable.COLUMN_TIMESTAMP + " DESC"; //For sorting
    private RecyclerviewSwipeHelper recyclerviewSwipeHelper = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        initDB();
        init();
    }

    private void initDB() {
        LSDBHelper lsdbHelper = new LSDBHelper(this);
        mDatabase = lsdbHelper.getReadableDatabase();
    }

    private void init() {

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
        recyclerViewSortingMethod(sortingCondition);
        disSortingButton = findViewById(R.id.sort_distanceButton);
        updatedSortingButton = findViewById(R.id.sort_recently);
        nameSortingButton = findViewById(R.id.sort_name);


        setupSwipe();
    }

    public void setupSwipe() {
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                recyclerviewSwipeHelper.onDraw(c);

                recyclerviewSwipeHelper.setRecyclerView(recyclerView); //For Attach ItemTouch.Right Left class
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(recyclerviewSwipeHelper);
                itemTouchHelper.attachToRecyclerView(recyclerView);
            }
        });
        recyclerviewSwipeHelper = new RecyclerviewSwipeHelper(getApplicationContext(), recyclerAdapter ,new SwipeActionInterface() {

            @Override
            public void onRightClicked(RecyclerView.ViewHolder viewHolder, int position, RecyclerviewSecondSwipeDismissHelper recyclerviewSecondSwipeDismissHelper) {

                recyclerviewSecondSwipeDismissHelper.onSwiped(viewHolder, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftClicked(RecyclerView.ViewHolder viewHolder, int position, RecyclerviewSecondSwipeToDoHelper recyclerviewSecondSwipeToDoHelper) {
                recyclerviewSecondSwipeToDoHelper.onSwiped(viewHolder, ItemTouchHelper.RIGHT);
            }
        });
    }
    public void setRecyclerView(RecyclerAdapter recyclerAdapter){
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void recyclerViewSortingMethod(String condition) {
        Cursor databaseQuery = databaseSortingQueryMethod(condition);
        recyclerAdapter = new RecyclerAdapter(this, databaseQuery);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private Cursor databaseSortingQueryMethod(String sortingCondition) {
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

    public void recentSortingOnClick(View view) {
        recyclerviewSwipeHelper.getButtonGone(true);
        sortingCondition = LSSQLContract.LocationTable.COLUMN_TIMESTAMP + " DESC";
        recyclerViewSortingMethod(sortingCondition);
    }

    public void distanceSortingOnClick(View view) {
        //String sorting =
        //recyclerViewSortingMethod(sorting);
        return;
    }

    public void nameSortingOnClick(View view) {
        recyclerviewSwipeHelper.getButtonGone(true);
        sortingCondition = LSSQLContract.LocationTable.COLUMN_NAME + " ASC";
        recyclerViewSortingMethod(sortingCondition);
    }

    public void AddOnClick(View view) {
        mDatabase.close();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, GET_ADD_LOCATION_REQUEST_CODE);
    }
}
