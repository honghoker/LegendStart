package com.example.ls_listsave.DataBase_Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Location")
public class LocationTable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String location_Name;
    private String location_Addr;
    private String location_DetailAddr;
    private String location_Phone;
    private String location_Memo;
    private String location_Latitude;
    private String location_Longitude;
    private String location_Timestamp;

    public LocationTable(int id, String location_Name, String location_Addr, String location_DetailAddr, String location_Phone, String location_Memo, String location_Latitude, String location_Longitude, String location_Timestamp) {
        this.id = id;
        this.location_Name = location_Name;
        this.location_Addr = location_Addr;
        this.location_DetailAddr = location_DetailAddr;
        this.location_Phone = location_Phone;
        this.location_Memo = location_Memo;
        this.location_Latitude = location_Latitude;
        this.location_Longitude = location_Longitude;
        this.location_Timestamp = location_Timestamp;
    }

    public int getId() {
        return id;
    }

    public String getLocation_Name() {
        return location_Name;
    }

    public String getLocation_Addr() {
        return location_Addr;
    }

    public String getLocation_DetailAddr() {
        return location_DetailAddr;
    }

    public String getLocation_Phone() {
        return location_Phone;
    }

    public String getLocation_Memo() {
        return location_Memo;
    }

    public String getLocation_Latitude() {
        return location_Latitude;
    }

    public String getLocation_Longitude() {
        return location_Longitude;
    }

    public String getLocation_Timestamp() {
        return location_Timestamp;
    }
}
