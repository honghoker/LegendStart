package com.example.todolist;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.ls.LSException;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    String data;
    Context mcontext;
    int i;

    public Adapter(Context mcontext, String data, int i) {
        Log.d("1","확인 : "+ data);
        this.data = data;
        this.mcontext = mcontext;
        this.i = i;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("1","확인1");
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.ViewHolder holder, int position) {
        Log.d("1","확인2");
        holder.itemName.setText(data);

        holder.itemName.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.itemName.isChecked()){
                    Log.d("1","체크확인");
                }
                else{
                    Log.d("1","체크해제");
                }
            }
        }) ;
    }

    @Override
    public int getItemCount() {
        return i;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox itemName;
        ImageView edit;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.cb_item);
            edit = itemView.findViewById(R.id.iv_edit);
            delete = itemView.findViewById(R.id.iv_delete);
        }
    }
}
