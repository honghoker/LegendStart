package com.example.ls_listsave.LocationList_RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ls_listsave.DataBase_Room.LocationEntity;
import com.example.ls_listsave.DataBase_Room.LocationViewModel;
import com.example.ls_listsave.R;

import java.util.List;

public class LocationList extends AppCompatActivity {
    private static final int GET_ADD_LOCATION_REQUEST_CODE = 200; //For intent
    private RecyclerAdapter recyclerAdapter;
    private LocationViewModel locationViewModel;
    private RecyclerView recyclerView; //For recyclerview
    private RecyclerviewSwipeHelper recyclerviewSwipeHelper = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        setRecyclerView();

        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel.class);
        locationViewModel.getAllData().observe(this, new Observer<List<LocationEntity>>() {
            @Override
            public void onChanged(List<LocationEntity> locationEntities) {
                //Update RecyclerView
                recyclerAdapter.setLocationEntities(locationEntities);
            }
        });
        init();
    }

    private void setRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void init() {
//
//
//
//        //recyclerViewSortingMethod(sortingCondition);
//
//        disSortingButton = findViewById(R.id.sort_distanceButton);
//        updatedSortingButton = findViewById(R.id.sort_recently);
//        nameSortingButton = findViewById(R.id.sort_name);
//
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
}
