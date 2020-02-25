package com.example.todolist;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button add_btn;
    Adapter adapter;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // db 객체생성
        // 우선 메인쓰레드에서 돌아가게 해놈
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,"todo_db").allowMainThreadQueries().build();

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Add Todo Item");
                View view = getLayoutInflater().inflate(R.layout.dialog_dashboard, null);
                final EditText toDoName = view.findViewById(R.id.ev_todo);
                dialog.setView(view);
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(toDoName.getText().toString().length() > 0){

                            // db 추가
                            db.todoDao().insert(new Todo(toDoName.getText().toString()));

                            adapter = new Adapter(MainActivity.this,toDoName.getText().toString(),++i);
                            recyclerView.setAdapter(adapter);
                            Toast.makeText(getApplicationContext(),"등록완료",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }
}
