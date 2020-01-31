package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.SqliteDB.SqliteEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private SqliteAdapter mAdapter;
    private EditText addressEditText, detailAdressEditText;;
    private FloatingActionButton floatingActionButton;

    private void init(){
        SqliteDBHelper dbHelper = new SqliteDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SqliteAdapter(this, getAllItems()); // recyclerView 를 가져옴
        recyclerView.setAdapter(mAdapter);
        addressEditText = findViewById(R.id.editText1);
        detailAdressEditText = findViewById(R.id.editText2);
        floatingActionButton = findViewById(R.id.floatingActionButton);


    }

    private void clearEditText(){
        addressEditText.getText().clear();
        detailAdressEditText.getText().clear();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }
    private void addItem(){ //add item
        if(addressEditText.getText().toString().trim().length() == 0)
            return;
        String address = addressEditText.getText().toString();
        String detailAddress = detailAdressEditText.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(SqliteEntry.COLUM_ADDRESS, address);
        cv.put(SqliteEntry.COLUMN_DETAILADDRESS, detailAddress);
        //ID, TimeStamp is going to fill automatically
        mDatabase.insert(SqliteEntry.TABLE_NAME,null,cv);
        mAdapter.swapCursor(getAllItems()); //아이탬이 추가되면 swapCursor로 다시 불러옴

        clearEditText();
    }

    private Cursor getAllItems(){
        return mDatabase.query(
                SqliteEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                SqliteEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
