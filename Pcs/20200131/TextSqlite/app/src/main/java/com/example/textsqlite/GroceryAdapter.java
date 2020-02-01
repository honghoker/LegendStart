package com.example.textsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public GroceryAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder{
        public TextView addressText;
        public TextView detailAddressText;

        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);
            addressText = itemView.findViewById(R.id.textview_address);
            detailAddressText = itemView.findViewById(R.id.textview_detailAddress);
        }
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.grocery_item, parent, false);
        return  new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return;

        String address = mCursor.getString(mCursor.getColumnIndex(GroceryContract.GrocertEntry.COLUMN_ADDRESS));
        String detailAddress = mCursor.getString(mCursor.getColumnIndex(GroceryContract.GrocertEntry.COLUMN_DETAILADDRESS));
        holder.addressText.setText(address);
        holder.addressText.setText(detailAddress);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    public void swapCursor(Cursor newCursor){
        if (mCursor != null)
            mCursor.close();
        mCursor = newCursor;
        if(newCursor != null)
            notifyDataSetChanged();
    }
}
