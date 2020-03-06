package com.example.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public class EditTodoViewModel extends AndroidViewModel {

    private TodoDao todoDao;
    private RoomDatabase db;

    public EditTodoViewModel(@NonNull Application application) {
        super(application);

    }
}
