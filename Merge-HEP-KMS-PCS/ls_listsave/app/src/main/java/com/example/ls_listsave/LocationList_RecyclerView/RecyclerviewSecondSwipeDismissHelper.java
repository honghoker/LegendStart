package com.example.ls_listsave.LocationList_RecyclerView;


import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.ls_listsave.DataBase_Room.LocationRoom.LocationEntity;
import com.example.ls_listsave.DataBase_Room.LocationRoom.LocationViewModel;
import com.example.ls_listsave.DataBase_Room.TagEntity.TagDatabase;
import com.example.ls_listsave.DataBase_Room.TagEntity.TagEntity;
import com.example.ls_listsave.DataBase_Room.TagEntity.TagViewModel;
import com.google.android.material.snackbar.Snackbar;

public class RecyclerviewSecondSwipeDismissHelper extends ItemTouchHelper.SimpleCallback {
    private LocationViewModel locationViewModel;
    private RecyclerAdapter recyclerAdapter;
    private LocationEntity locationEntity;
    private TagViewModel tagViewModel;

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
        locationEntity = locationViewModel.delete(recyclerAdapter.getLocationEntityAt(viewHolder.getAdapterPosition()));
        final TagDatabase tagDatabase = Room.databaseBuilder(viewHolder.itemView.getContext(), TagDatabase.class, "Tag_Database").allowMainThreadQueries().build();
        final TagEntity[] tagEntities = tagDatabase.tagEntity_dao().multipleSelectionByForeignKey(locationEntity.getId());
        tagDatabase.tagEntity_dao().delete(tagEntities);
        Log.d("tag","Successful dismiss Tag");

        Snackbar.make(viewHolder.itemView, "", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationViewModel.insert(locationEntity);
                tagDatabase.tagEntity_dao().insert(tagEntities);
                Log.d("tag","Successful Undo");
            }
        }).show();
        Toast.makeText(viewHolder.itemView.getContext(), "Success Undo", Toast.LENGTH_SHORT).show();

    }
}