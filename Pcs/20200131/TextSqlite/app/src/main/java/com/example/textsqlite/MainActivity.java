package com.example.textsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.textsqlite.GroceryContract.*;

public class MainActivity extends AppCompatActivity {
    private GroceryAdapter mAdapter;
    private SQLiteDatabase mDatabase;
    private EditText addressEditText;
    private EditText detailAddressEditText;
    private FloatingActionButton floatingActionButton;

    /*private void init(){
        addressEditText = findViewById(R.id.editText);
        detailAddressEditText = findViewById(R.id.editText2);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GroceryAdapter(this, getAllItems());
        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
    }
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new GroceryAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        addressEditText = findViewById(R.id.editText);
        detailAddressEditText = findViewById(R.id.editText2);
        floatingActionButton = findViewById(R.id.floatingActionButton);





        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }
    private void addItem(){

        if(addressEditText.getText().toString().trim().length() == 0)
            return;
        String address = addressEditText.getText().toString();
        String detailAddress = detailAddressEditText.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(GrocertEntry.COLUMN_ADDRESS,address);
        cv.put(GrocertEntry.COLUMN_DETAILADDRESS,detailAddress);
        mDatabase.insert(GrocertEntry.TABLE_NAME,null, cv);
        mAdapter.swapCursor(getAllItems());

        clearText();
    }
    private Cursor getAllItems(){
        return mDatabase.query(
                GrocertEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                GrocertEntry.COLUM_TIMESTAMP + " DESC"
        );
    }
    private void clearText(){
        addressEditText.getText().clear();
        detailAddressEditText.getText().clear();
    }
}
