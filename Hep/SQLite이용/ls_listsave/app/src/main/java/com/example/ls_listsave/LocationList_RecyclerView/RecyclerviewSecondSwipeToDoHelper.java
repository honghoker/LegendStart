package com.example.ls_listsave.LocationList_RecyclerView;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;



public class RecyclerviewSecondSwipeToDoHelper extends ItemTouchHelper.SimpleCallback{
    private Context context = null;

    public RecyclerviewSecondSwipeToDoHelper(int dragDirs, int swipeDirs, Context context) {
        super(0, ItemTouchHelper.RIGHT);
        this.context = context;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }
}