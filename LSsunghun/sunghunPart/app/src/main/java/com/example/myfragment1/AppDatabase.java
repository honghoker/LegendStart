package com.example.myfragment1;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Directory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DirectoryDao directoryDao();
}
