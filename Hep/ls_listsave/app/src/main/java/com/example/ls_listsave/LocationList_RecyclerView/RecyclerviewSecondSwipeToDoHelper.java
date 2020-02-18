package com.example.ls_listsave.LocationList_RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;



public class RecyclerviewSecondSwipeToDoHelper extends ItemTouchHelper.SimpleCallback {
    private int firstSwipe = -1;
    private Context context;
    private int mSwipeDirs;
    private boolean firstSwipeFlag = false;
    private LocationList locationList = null;

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Log.d("tag","RIGHT");
    }
    public RecyclerviewSecondSwipeToDoHelper(int dragDirs, int swipeDirs, Context context) {
        super(0, ItemTouchHelper.RIGHT);
    }

    public void setSwipeEnabled(boolean firstSwipeFlag){
        this.firstSwipeFlag = firstSwipeFlag;
        isItemViewSwipeEnabled();
    }


    @Override
    public boolean isItemViewSwipeEnabled() {
        return firstSwipeFlag;
    }
}
