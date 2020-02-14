package com.example.myflowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(20,20);

        for(int i = 0; i < 10; i++) {
            Button b = new Button(this);
            final Integer aa = i;
            b.setText("temp");
            b.setLayoutParams(params);
            b.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, aa.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            ((FlowLayout) findViewById(R.id.flowlayout)).addView(b);
        }
    }
}
