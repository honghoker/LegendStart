package com.example.ls_listsave.LocationList_RecyclerView;

import android.content.Context;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewSecondSwipeHelper extends ItemTouchHelper.SimpleCallback {
    private int firstSwipe = -1;
    private Context context;
    private int mSwipeDirs;



    public void setFirstSwipe(int firstSwipe) {
        this.firstSwipe = firstSwipe;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if(mSwipeDirs == direction){
            Toast.makeText(context,"TQ",Toast.LENGTH_SHORT).show();
        }

    }
    public RecyclerviewSecondSwipeHelper(int dragDirs, int swipeDirs, Context context) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mSwipeDirs = swipeDirs;
        this.context = context;
        Log.d("tag", String.valueOf(swipeDirs));
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
