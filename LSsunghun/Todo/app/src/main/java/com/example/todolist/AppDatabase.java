package com.example.todolist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// database 객체
@Database(entities = {Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
//     AppDatabase를 생성해서 todoDao를 이용해 Todo 조작
    public abstract TodoDao todoDao();
}
