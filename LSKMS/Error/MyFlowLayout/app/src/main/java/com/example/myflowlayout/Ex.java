package com.example.myflowlayout;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Ex extends AppCompatActivity {
    public static Context mContext2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext2 = this;
    }

    public void Toastt(Context context){
        Toast.makeText(context,"ddf",Toast.LENGTH_SHORT).show();
    }
}