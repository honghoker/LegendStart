package com.example.myfragment1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DirectoryDao {
    @Query("SELECT * FROM Directory")
    LiveData<List<Directory>> getAll();

    @Insert
    void insert(Directory directory);

    @Delete
    void delete(Directory directory);


}
