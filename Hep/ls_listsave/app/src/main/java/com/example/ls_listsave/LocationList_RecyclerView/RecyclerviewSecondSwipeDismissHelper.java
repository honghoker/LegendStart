package com.example.ls_listsave.LocationList_RecyclerView;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewSecondSwipeDismissHelper extends ItemTouchHelper.SimpleCallback {
    private Context context;
    private boolean firstSwipeFlag = false;
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Log.d("tag","LEFT");
    }

    public RecyclerviewSecondSwipeDismissHelper(int dragDirs, int swipeDirs, Context context) {
        super(0, ItemTouchHelper.LEFT);
        this.context = context;
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
