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
import com.google.android.material.snackbar.Snackbar;

public class RecyclerviewSecondSwipeDismissHelper extends ItemTouchHelper.SimpleCallback {
    private Context context = null;
    private SQLiteDatabase sqLiteDatabase = null;


    public RecyclerviewSecondSwipeDismissHelper(int dragDirs, int swipeDirs, Context context) {
        super(dragDirs, ItemTouchHelper.LEFT);
        this.context = context;

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final TemporaryData temporaryData;
        long click_primaryKey = (long) viewHolder.itemView.getTag();
        UndoFactory undoFactory = new UndoFactory(context, click_primaryKey);
        temporaryData = undoFactory.onAction();

        removeItem(click_primaryKey);
        recyclerAdapter.notifyItemRemoved(position);
        Snackbar.make(recyclerView, "이거고쳐야함", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (temporaryData.onUndo(getApplicationContext())) {
                            recyclerAdapter.swapCursor(databaseSortingQueryMethod(sortingCondition));
                            Toast.makeText(getApplicationContext(), "Undo Success", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();

    }
    private void removeItem(long id) {
        //id는 swipe하는 행을 말합니다.
        mDatabase.delete(LSSQLContract.LocationTable.TABLE_NAME,
                LSSQLContract.LocationTable._ID + "=" + id, null);
        recyclerAdapter.swapCursor(databaseSortingQueryMethod(sortingCondition));
    }

}