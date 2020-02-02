package com.example.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase lsDataBase;
    private LSDBHelper dbHelper;

    private void settingDB(){
        dbHelper = new LSDBHelper(this);
        lsDataBase = dbHelper.getWritableDatabase();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingDB();
    }


}
