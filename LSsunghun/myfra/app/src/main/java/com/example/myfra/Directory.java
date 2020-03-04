package com.example.myfragment1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Directory {
    @PrimaryKey(autoGenerate = true)
    private int seq;
    private String title;

    public Directory(String title) {
        this.title = title;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // toString 재정의
    @Override
    public String toString() {
        return title;
    }
}
