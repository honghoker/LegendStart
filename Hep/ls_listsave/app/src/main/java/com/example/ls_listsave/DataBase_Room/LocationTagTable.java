package com.example.ls_listsave.DataBase_Room;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "LocationTagTable",
        foreignKeys = @ForeignKey(entity = LocationEntity.class, parentColumns = "id", childColumns = "location_id", onDelete = ForeignKey.CASCADE))
public class LocationTagTable {
    @PrimaryKey(autoGenerate = false)
    private String location_id;
    private String tag_Tag;

    public String getTag_Tag() {
        return tag_Tag;
    }

    public String getLocation_id() {
        return location_id;
    }

    public LocationTagTable(String location_id, String tag_Tag) {
        this.location_id = location_id;
        this.tag_Tag = tag_Tag;
    }
}
