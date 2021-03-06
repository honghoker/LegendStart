package com.example.ls_listsave.DataBase_Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationEntity_Dao {

    @Insert
    void insert(LocationEntity locationEntity);

    @Update
    void update(LocationEntity locationEntity);

    @Delete
    void delete(LocationEntity locationEntity);

    @Query("DELETE FROM LocationEntity")
    void deleteAllData();

    @Query("SELECT * FROM LocationEntity ORDER BY location_Timestamp DESC")
    LiveData<List<LocationEntity>> getAllData();
}
