package com.example.ls_listsave.LocationList_RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ls_listsave.DataBase_Room.LocationRoom.LocationEntity;
import com.example.ls_listsave.DataBase_Room.LocationRoom.LocationViewModel;
import com.example.ls_listsave.DataBase_Room.TagEntity.TagViewModel;
import com.example.ls_listsave.MainActivity;
import com.example.ls_listsave.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

;

public class LocationList extends AppCompatActivity {
    private static final int GET_ADD_LOCATION_REQUEST_CODE = 200; //For intent
    private RecyclerAdapter recyclerAdapter;
    private LocationViewModel locationViewModel;
    private TagViewModel tagViewModel;
    private RecyclerView recyclerView; //For recyclerview
    private RecyclerviewSwipeHelper recyclerviewSwipeHelper = null;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        setRecyclerView();

        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel.class);
        tagViewModel = ViewModelProviders.of(this).get(TagViewModel.class);
        locationViewModel.getAllData().observe(this, new Observer<List<LocationEntity>>() {
            @Override
            public void onChanged(List<LocationEntity> locationEntities) {
                //Update RecyclerView
                recyclerAdapter.setLocationEntities(locationEntities);
            }
        });
        init();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check_toggle:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.check_list_menu, menu);
        return true;
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
        floatingActionButton = findViewById(R.id.floatingButton);
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
        recyclerviewSwipeHelper = new RecyclerviewSwipeHelper(getApplicationContext(), locationViewModel ,recyclerAdapter ,new SwipeActionInterface() {

            @Override
            public void onRightClicked(RecyclerView.ViewHolder viewHolder, int position, RecyclerviewSecondSwipeDismissHelper recyclerviewSecondSwipeDismissHelper) {
                recyclerviewSecondSwipeDismissHelper.onSwiped(viewHolder, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
            }

            @Override
            public void onLeftClicked(RecyclerView.ViewHolder viewHolder, int position, RecyclerviewSecondSwipeToDoHelper recyclerviewSecondSwipeToDoHelper) {
                recyclerviewSecondSwipeToDoHelper.onSwiped(viewHolder, ItemTouchHelper.RIGHT);
            }
        });
    }
    public void AddOnClick(View view){
        Intent intent = new Intent(LocationList.this, MainActivity.class);
        startActivityForResult(intent, GET_ADD_LOCATION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("tag","onActivityResultEnter");
        if(requestCode == GET_ADD_LOCATION_REQUEST_CODE && resultCode == RESULT_OK){
            Log.d("tag","onActivityResult");
            String title = data.getStringExtra(MainActivity.EXTRA_TITLE);
            String address = data.getStringExtra(MainActivity.EXTRA_Addr);
            String detailAddr = data.getStringExtra(MainActivity.EXTRA_DetailAddr);
            String number = data.getStringExtra(MainActivity.EXTRA_Number);
            String comment = data.getStringExtra(MainActivity.EXTRA_Comment);
            String latitude = data.getStringExtra(MainActivity.EXTRA_Latitude);
            String longitude = data.getStringExtra(MainActivity.EXTRA_Longitude);
            String timestamp = data.getStringExtra(MainActivity.EXTRA_Timestamp);
            ArrayList<String> hashTag = data.getStringArrayListExtra(MainActivity.EXTRA_HASHTAG);
//String location_Title, String location_Addr, String location_DetailAddr, String location_Phone, String location_Memo, String location_Latitude, String location_Longitude, String location_Timestamp
            LocationEntity locationEntity = new LocationEntity(title, address, detailAddr, number, comment, latitude, longitude, timestamp);
            locationViewModel.insert(locationEntity);
            if(hashTag.isEmpty()){

            }


            Toast.makeText(this, "Save",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Not Save",Toast.LENGTH_SHORT).show();
    }
}
