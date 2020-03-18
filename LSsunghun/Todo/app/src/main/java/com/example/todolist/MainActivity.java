package com.example.todolist;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int UPDATE_CODE = 1;
    RecyclerView recyclerView;
    static RecyclerView recyclerView_sta;
    static boolean addFrag = false;
    static boolean updateFrag = false;
    Button add_btn;
    Adapter adapter;
    ArrayList<String> listData;
    int i = 0;

    // update intent 에서 result 값 받아와서 세팅
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,"todo_db").build();
        Log.d("1","update 확인1");
        if(resultCode == RESULT_OK){
            Log.d("1","update 확인2");
            Todo todo = new Todo();
            todo.setSeq(data.getIntExtra("update_note_seq",1));
            todo.setTitle(data.getStringExtra("update_note_data"));
            new UpdateAsyncTask(db.todoDao()).execute(todo);
            db.todoDao().getAll().observe(this, new Observer<List<Todo>>() {
                @Override
                public void onChanged(List<Todo> todos) {
                    // clear 한번 해주고 다시 add
                    listData.clear();
                    Log.d("1","아님 여기 ??");
                    for (int i = 0; i < todos.size(); i++) {
                        listData.add(todos.get(i).toString());
                        Log.d("1","확인"+i+"  "+todos.get(i).toString());
                    }
                    adapter = new Adapter(MainActivity.this,listData, todos,db);
                    recyclerView.setAdapter(adapter);
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listData = new ArrayList<>();

//        final int todo_seq = getIntent().getIntExtra("test",1);
        Log.d("1","제발        ");

        // db 객체생성
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,"todo_db").build();
        // 라이브데이터 관찰
        db.todoDao().getAll().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                // clear 한번 해주고 다시 add
                listData.clear();
                Log.d("1","아님 여기 ??");
                for (int i = 0; i < todos.size(); i++) {
                    listData.add(todos.get(i).toString());
                    Log.d("1","확인"+i+"  "+todos.get(i).toString());
                }
                adapter = new Adapter(MainActivity.this,listData, todos,db);
                recyclerView.setAdapter(adapter);
            }
        });


        recyclerView = findViewById(R.id.rv);
        recyclerView_sta = findViewById(R.id.rv);
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
                            new InsertAsyncTask(db.todoDao()).execute(new Todo(toDoName.getText().toString()));
                            listData.clear();
                            addFrag = true;

//                            adapter = new Adapter(MainActivity.this,toDoName.getText().toString(),++i);
//                            recyclerView.setAdapter(adapter);

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




    // 비동기처리
    private static class InsertAsyncTask extends AsyncTask<Todo,Void,Void>{
        private TodoDao mTodoDao;

        public InsertAsyncTask(TodoDao todoDao) {
            this.mTodoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTodoDao.insert(todos[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Todo,Void,Void> {
        private TodoDao mTodoDao;

        public UpdateAsyncTask(TodoDao todoDao) {
            this.mTodoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTodoDao.update(todos[0]);
            return null;
        }
    }
}

//    @Override
//    public void OnDeleteClickListener(Todo todo){
//
//    }

