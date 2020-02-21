package com.example.ls_listsave.DataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {RoomDB.class}, version = 1)
public abstract class RoomDBDatabase extends RoomDatabase {

    private static RoomDBDatabase instance;
    public abstract RoomDBDao roomDBDao();
    public static synchronized RoomDBDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDBDatabase.class,"Location_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private RoomDBDao roomDBDao;

        private PopulateDbAsyncTask(RoomDBDatabase roomDBDatabase){
            roomDBDao = roomDBDatabase.roomDBDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            roomDBDao.insert(new RoomDB("Title 1", "Description 1", 1));
            roomDBDao.insert(new RoomDB("Title 2", "Description 2", 2));
            roomDBDao.insert(new RoomDB("Title 3", "Description 3", 3));

            return null;
        }
    }
}
