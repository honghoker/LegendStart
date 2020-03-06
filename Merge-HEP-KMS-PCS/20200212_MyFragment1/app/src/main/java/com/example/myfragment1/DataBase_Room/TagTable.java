package com.example.myfragment1.DataBase_Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tag")
public class TagTable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Tag_tag;

    public TagTable(int id, String tag_tag) {
        this.id = id;
        Tag_tag = tag_tag;
    }

    public int getId() {
        return id;
    }

    public String getTag_tag() {
        return Tag_tag;
    }

}