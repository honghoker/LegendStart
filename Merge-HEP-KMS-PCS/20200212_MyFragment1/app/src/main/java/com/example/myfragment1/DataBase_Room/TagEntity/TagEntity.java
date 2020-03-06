package com.example.myfragment1.DataBase_Room.TagEntity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;

@Entity(tableName = "Tag_Database",
        foreignKeys = {
                @ForeignKey(entity = LocationEntity.class,
                        parentColumns = "id", childColumns = "location_id", onDelete = ForeignKey.CASCADE)})
public class TagEntity {
    @PrimaryKey(autoGenerate = true)
    private int tag_id;

    @ColumnInfo(name = "location_id")
    private int locationId;

    private String tag;

    public TagEntity(int locationId, String tag) {
        this.locationId = locationId;
        this.tag = tag;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getTag() {
        return tag;
    }
}
