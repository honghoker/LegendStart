package com.example.ls_listsave.LocationList_RecyclerView;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ls_listsave.DataBase.LSSQLContract;
import com.example.ls_listsave.DataLappingByContentValues.DataLapping_LocationData;
import com.google.android.material.snackbar.Snackbar;

public class RecyclerviewSecondSwipeDismissHelper extends ItemTouchHelper.SimpleCallback {
    private Context context = null;
    private RecyclerAdapter recyclerAdapter = null;

    public RecyclerviewSecondSwipeDismissHelper(int dragDirs, int swipeDirs, Context context) {
        super(dragDirs, ItemTouchHelper.LEFT);
        this.context = context;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {

        long click_PrimaryKey = (long) viewHolder.itemView.getTag();
        final UndoFactory undoFactory = new UndoFactory(context, click_PrimaryKey, recyclerAdapter);
        final RecyclerAdapter recyclerAdapter = undoFactory.setRecyclerAdapter();
        final TemporaryData temporaryData = undoFactory.setRemoveItem(click_PrimaryKey);
        Snackbar.make(viewHolder.itemView, "이거고쳐야함", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        temporaryData.onUndo(context,recyclerAdapter);
                    }
                }).show();
    }

}