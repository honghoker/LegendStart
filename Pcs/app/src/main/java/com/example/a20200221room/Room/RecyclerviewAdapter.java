package com.example.a20200221room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20200221room.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RoomDBHolder> {
    private List<RoomDB> roomDBS = new ArrayList<>();

    @NonNull
    @Override
    public RoomDBHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new RoomDBHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomDBHolder holder, int position) {
        RoomDB currentRoom = roomDBS.get(position);
        holder.textView.setText(currentRoom.getTitle());
    }

    @Override
    public int getItemCount() {
        return roomDBS.size();
    }
    public void setRoomDBS(List<RoomDB> roomDBS){
        this.roomDBS = roomDBS;
        notifyDataSetChanged();
    }

    class RoomDBHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public RoomDBHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
