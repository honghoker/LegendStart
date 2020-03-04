package com.example.ls_listsave.DataBase_Room.TagEntity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TagEntity_Dao {
    @Insert
    void insert(TagEntity... tagEntities);
    @Update
    void update(TagEntity tagEntity);
    @Delete
    void delete(TagEntity... tagEntities);

    @Query("DELETE FROM TagEntity")
    void deleteAllData();

    @Query("SELECT * FROM TagEntity ORDER BY tag_id DESC")
    LiveData<List<TagEntity>> getAllData();


    @Query("SELECT * FROM TagEntity WHERE location_id = :location_id")
    TagEntity[] multipleSelectionByForeignKey(int location_id);
}
