package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        public TextView addressText;
        public TextView detailAddressText;

        public SqliteViewHolder(@NonNull View itemView) {
            super(itemView);

            addressText = itemView.findViewById(R.id.textview_address_item);
            detailAddressText = itemView.findViewById(R.id.textview_detailaddress_item);
        }

    }

    @NonNull
    @Override
    public SqliteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.grocery_item,parent,false);
        return new SqliteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SqliteViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }
        String address = mCursor.getString(mCursor.getColumnIndex(SqliteDB.SqliteEntry.COLUM_ADDRESS));
        String detailAddress = mCursor.getString(mCursor.getColumnIndex(SqliteDB.SqliteEntry.COLUMN_DETAILADDRESS));

        holder.addressText.setText(address);
        holder.detailAddressText.setText(detailAddress);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
