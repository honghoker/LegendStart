package com.example.ls_listsave.DataBase_Room.TagEntity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.ls_listsave.DataBase_Room.LocationRoom.LocationEntity;

@Entity(tableName = "TagEntity", foreignKeys = @ForeignKey(entity = LocationEntity.class, parentColumns = "id",childColumns = "location_id",onDelete = ForeignKey.CASCADE))
public class TagEntity {
    @PrimaryKey(autoGenerate = true)
    private int tag_id;
    private int location_id;
    private String tag;

    public TagEntity(int location_id, String tag) {
        this.location_id = location_id;
        this.tag = tag;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public String getTag() {
        return tag;
    }
}
