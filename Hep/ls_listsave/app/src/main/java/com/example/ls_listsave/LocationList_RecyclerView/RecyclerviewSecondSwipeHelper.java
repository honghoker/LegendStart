package com.example.ls_listsave.LocationList_RecyclerView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewSecondSwipeHelper extends ItemTouchHelper.SimpleCallback {

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    public RecyclerviewSecondSwipeHelper(int dragDirs, int swipeDirs) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
    }

    @Override
    public void setDefaultSwipeDirs(int defaultSwipeDirs) {
        super.setDefaultSwipeDirs(defaultSwipeDirs);
    }

    @Override
    public void setDefaultDragDirs(int defaultDragDirs) {
        super.setDefaultDragDirs(defaultDragDirs);
    }

    @Override
    public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return super.getSwipeDirs(recyclerView, viewHolder);
    }

    @Override
    public int getDragDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return super.getDragDirs(recyclerView, viewHolder);
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return super.getMovementFlags(recyclerView, viewHolder);
    }

}
