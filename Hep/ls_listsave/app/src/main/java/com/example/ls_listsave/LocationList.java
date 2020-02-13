package com.example.ls_listsave;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Stack;

public class LocationList extends AppCompatActivity {
    private Stack<TemporaryStoreData> undoDataStack = new Stack<>(); //추후 undo생각
    private Stack<ContentValues> undoStack = new Stack<>(); //For Undo
    private static final int GET_ADD_LOCATION_REQUEST_CODE = 200; //For intent
    public RecyclerAdapter recyclerAdapter; //For recyclerview
    private SQLiteDatabase mDatabase; //For recyclerview
    private RecyclerView recyclerView; //For recyclerview
    private Button disSortingButton, updatedSortingButton, nameSortingButton; //For sorting
    private String sortingCondition = LSSQLContract.LocationTable.COLUMN_TIMESTAMP + " DESC"; //For sorting

    //swipe이전
    private ItemTouchHelper swipeLeftDismiss;
    private TemporaryStoreData temporaryStoreData;

    //Swipe 작업중
    private RecyclerviewSwipeHelper recyclerviewSwipeHelper = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        initDB();
        init();
    }

    //Recyclerview Swipe 해서 지우는 메소드
    private void removeItem(long id) {
        //id는 swipe하는 행을 말합니다.
        mDatabase.delete(LSSQLContract.LocationTable.TABLE_NAME,
                LSSQLContract.LocationTable._ID + "=" + id, null);
        recyclerAdapter.swapCursor(databaseSortingQueryMethod(sortingCondition));
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
        Log.d("1", "F_Start setupSwipe");
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                Log.d("1", "onDraw in setupSwipe");
                recyclerviewSwipeHelper.onDraw(c);
            }
        });
        Log.d("1", "F_Middle of setupSwipe");
        recyclerviewSwipeHelper = new RecyclerviewSwipeHelper(new SwipeActionInterface() {

            @Override
            public void onRightClicked(RecyclerView.ViewHolder viewHolder, int position) {

                long click_primaryKey = (long) viewHolder.itemView.getTag();
                temporaryStoreData = new TemporaryStoreData();
                final TemporaryStoreData temp = temporaryStoreData.recoverDataMethod(mDatabase, click_primaryKey);
                removeItem(click_primaryKey);
                recyclerAdapter.notifyItemRemoved(position);
                Snackbar.make(recyclerView, "이거고쳐야함", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ContentValues cv = temporaryStoreData.undoDataContentValue(temp);
                                if (mDatabase.insert(LSSQLContract.LocationTable.TABLE_NAME, null, cv) > 0) {
                                    recyclerAdapter.swapCursor(databaseSortingQueryMethod(sortingCondition));
                                    Toast.makeText(getApplicationContext(), "Undo Success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).show();

            }

            @Override
            public void onLeftClicked(RecyclerView.ViewHolder viewHolder, int position) {
                Snackbar.make(recyclerView, "일정에 추가되었습니다", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "취소되었습니다", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(recyclerviewSwipeHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);
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
