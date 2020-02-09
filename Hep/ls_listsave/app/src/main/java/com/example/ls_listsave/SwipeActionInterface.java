package com.example.ls_listsave;

import androidx.recyclerview.widget.RecyclerView;

public interface SwipeActionInterface {
    void onLeftClicked(RecyclerView.ViewHolder viewHolder, int position);
    void onRightClicked(RecyclerView.ViewHolder viewHolder, int position);
}
