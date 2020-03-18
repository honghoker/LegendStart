package com.example.todolist;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class EditNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.rv);
        Button todo_edit_btn = findViewById(R.id.todo_edit_btn);
        final LiveData<Todo> mtodo;
        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,"todo_db").build();

//        String todo_seq = bundle.getString("note_seq");
        final int todo_seq = getIntent().getIntExtra("note_seq",1);
//        String note_data = getIntent().getStringExtra("note_data");
        final EditText todo_edit = findViewById(R.id.todo_edit);

        Log.d("1","intent후   "+todo_seq);

        mtodo = db.todoDao().getTodo(String.valueOf(todo_seq));
        mtodo.observe(this, new Observer<Todo>() {
            @Override
            public void onChanged(Todo todo) {
                todo_edit.setText(todo.getTitle());
        }
    });

//    final Todo todo = new Todo(todo_edit.getText().toString());

        todo_edit_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//                Log.d("1","확인이요   "+todo_edit.getText().toString());
            String updateTodo = todo_edit.getText().toString();
            Intent resultIntent = new Intent(getApplicationContext(),MainActivity.class);
                resultIntent.putExtra("update_note_seq",todo_seq);
                resultIntent.putExtra("update_note_data",updateTodo);
            int RESULT_CODE = -1;
            Todo todo = new Todo();
            todo.setSeq(todo_seq);
            todo.setTitle(updateTodo);

            // 이시발 여기 업데이트 고쳐야함
//            new UpdateAsyncTask(db.todoDao()).execute(todo);
            MainActivity.updateFrag =true;
            setResult(RESULT_CODE,resultIntent);
            finish();
//                new UpdateAsyncTask(db.todoDao()).execute(todo);

        }
    });
}
//
//private static class UpdateAsyncTask extends AsyncTask<Todo,Void,Void> {
//    private TodoDao mTodoDao;
//
//    public UpdateAsyncTask(TodoDao todoDao) {
//        this.mTodoDao = todoDao;
//    }
//
//    @Override
//    protected Void doInBackground(Todo... todos) {
//        mTodoDao.update(todos[0]);
//            return null;
//        }
//    }
}
