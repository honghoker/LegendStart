package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SqliteAdapter extends RecyclerView.Adapter<SqliteAdapter.SqliteViewHolder> {
    private Context mContext;
    private Cursor mCursor;


    public SqliteAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class SqliteViewHolder extends RecyclerView.ViewHolder{


        public SqliteViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public SqliteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SqliteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
