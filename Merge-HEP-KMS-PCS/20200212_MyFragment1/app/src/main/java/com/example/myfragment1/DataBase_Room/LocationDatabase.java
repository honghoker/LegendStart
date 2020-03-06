package com.example.myfragment1.DataBase_Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {LocationEntity.class}, version = 1)
public abstract class LocationDatabase extends RoomDatabase {
    private static LocationDatabase instance;
    public abstract LocationEntity_Dao locationEntity_dao();
    public static synchronized LocationDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext().getApplicationContext(),
                    LocationDatabase.class, "Location_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(locationcallback)
                    .build();
        }
        return instance;
    }
    private static Callback locationcallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private LocationEntity_Dao locationEntity_dao;

        private PopulateDbAsyncTask(LocationDatabase locationDatabase){
            locationEntity_dao = locationDatabase.locationEntity_dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            locationEntity_dao.insert(new LocationEntity("Strart","END","DETAIL","111","Hi"
            ,"11","22","22"));
            /*
            public LocationEntity(String location_Title, String location_Addr, String location_DetailAddr, String location_Phone, String location_Memo,
            String location_Latitude, String location_Longitude, String location_Timestamp) {
            roomDBDao.insert(new RoomDB("Title 1", "Description 1", 1));
            roomDBDao.insert(new RoomDB("Title 2", "Description 2", 2));
            roomDBDao.insert(new RoomDB("Title 3", "Description 3", 3));


             */
            return null;
        }
    }

}
