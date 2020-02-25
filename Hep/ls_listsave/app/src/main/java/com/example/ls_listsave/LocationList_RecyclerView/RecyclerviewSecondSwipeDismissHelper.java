package com.example.ls_listsave.LocationList_RecyclerView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ls_listsave.DataBase_Room.LocationViewModel;

public class RecyclerviewSecondSwipeDismissHelper extends ItemTouchHelper.SimpleCallback {
    private LocationViewModel locationViewModel;
    private RecyclerAdapter recyclerAdapter;

    public RecyclerviewSecondSwipeDismissHelper(int dragDirs, int swipeDirs, RecyclerAdapter recyclerAdapter, LocationViewModel locationViewModel) {
        super(dragDirs, ItemTouchHelper.LEFT);
        this.recyclerAdapter = recyclerAdapter;
        this.locationViewModel = locationViewModel;

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }


    @Override
    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
        //Undo
        locationViewModel.delete(recyclerAdapter.getLocationEntityAt(viewHolder.getAdapterPosition()));
        Toast.makeText(viewHolder.itemView.getContext(),"LocationDelete", Toast.LENGTH_SHORT).show();

    }

}