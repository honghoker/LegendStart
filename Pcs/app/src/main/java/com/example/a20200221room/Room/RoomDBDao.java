package com.example.a20200221room.Room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomDBDao {

    @Insert
    void insert(RoomDB roomDB);

    @Update
    void update(RoomDB roomDB);

    @Delete
    void delete(RoomDB roomDB);

    @Query("DELETE FROM Location_Table")
    void deleteAllData();

    @Query("SELECT * FROM Location_Table ORDER BY priority DESC")
    LiveData<List<RoomDB>> getAllData();
}
