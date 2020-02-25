package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.todo).setVisibility(View.GONE);
        findViewById(R.id.todo).setBackgroundColor(Color.RED);
        findViewById(R.id.view).setBackgroundColor(Color.BLUE);
    }

    public void ontestbtnClicked(View v){
        findViewById(R.id.todo).setVisibility(View.VISIBLE);
    }

}
