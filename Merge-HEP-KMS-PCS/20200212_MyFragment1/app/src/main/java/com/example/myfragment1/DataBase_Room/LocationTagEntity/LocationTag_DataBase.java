package com.example.myfragment1.DataBase_Room.LocationTagEntity;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {LocationTagEntity.class}, version = 1)
public abstract class LocationTag_DataBase extends RoomDatabase {
    public abstract LocationTag_Dao locationTag_dao();

    private static LocationTag_DataBase instance;
    public static synchronized LocationTag_DataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext().getApplicationContext(),
                    LocationTag_DataBase.class, "LocationTagTable")
                    .fallbackToDestructiveMigration().addCallback(locationtagCallback)
                    .build();
        }
        return instance;
    }
    public static Callback locationtagCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private LocationTag_Dao locationTag_dao;

        public PopulateDbAsyncTask(LocationTag_DataBase locationTag_dataBase) {
            this.locationTag_dao = locationTag_dataBase.locationTag_dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
