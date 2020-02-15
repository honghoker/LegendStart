package com.example.ls_listsave.LocationList_RecyclerView;

import androidx.recyclerview.widget.RecyclerView;

public interface SwipeActionInterface {
    void onLeftClicked(RecyclerView.ViewHolder viewHolder, int position);
    void onRightClicked(RecyclerView.ViewHolder viewHolder, int position);
}
