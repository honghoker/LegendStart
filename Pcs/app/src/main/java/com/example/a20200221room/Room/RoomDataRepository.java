package com.example.a20200221room.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RoomDataRepository {
    private RoomDBDao roomDBDao;
    private LiveData<List<RoomDB>> allRoom;


    public RoomDataRepository(Application application){
        RoomDBDatabase database = RoomDBDatabase.getInstance(application);
        roomDBDao = database.roomDBDao();
        allRoom = roomDBDao.getAllData();

    }

    public void insert(RoomDB roomDB){
        new InsertRoomDatasyncTask(roomDBDao).execute(roomDB);
    }
    public void update(RoomDB roomDB){
        new UpdateRoomDatasyncTask(roomDBDao).execute(roomDB);
    }
    public void delete(RoomDB roomDB){
        new DeleteRoomDatasyncTask(roomDBDao).execute(roomDB);
    }
    public void deleteAllData(){
        new DeleteAllRoomDatasyncTask(roomDBDao).execute();
    }
    public LiveData<List<RoomDB>> getAllRoom(){
        return allRoom;
    }

    private static class InsertRoomDatasyncTask extends AsyncTask<RoomDB, Void, Void>{
        private RoomDBDao roomDBDao;
        private InsertRoomDatasyncTask(RoomDBDao roomDBDao){
            this.roomDBDao = roomDBDao;
        }
        @Override
        protected Void doInBackground(RoomDB... roomDBS) {
            roomDBDao.insert(roomDBS[0]);
            return null;
        }
    }

    private static class UpdateRoomDatasyncTask extends AsyncTask<RoomDB, Void, Void>{
        private RoomDBDao roomDBDao;
        private UpdateRoomDatasyncTask(RoomDBDao roomDBDao){
            this.roomDBDao = roomDBDao;
        }
        @Override
        protected Void doInBackground(RoomDB... roomDBS) {
            roomDBDao.update(roomDBS[0]);
            return null;
        }
    }

    private static class DeleteRoomDatasyncTask extends AsyncTask<RoomDB, Void, Void>{
        private RoomDBDao roomDBDao;
        private DeleteRoomDatasyncTask(RoomDBDao roomDBDao){
            this.roomDBDao = roomDBDao;
        }
        @Override
        protected Void doInBackground(RoomDB... roomDBS) {
            roomDBDao.delete(roomDBS[0]);
            return null;
        }
    }
    private static class DeleteAllRoomDatasyncTask extends AsyncTask<RoomDB, Void, Void>{
        private RoomDBDao roomDBDao;
        private DeleteAllRoomDatasyncTask(RoomDBDao roomDBDao){
            this.roomDBDao = roomDBDao;
        }
        @Override
        protected Void doInBackground(RoomDB... roomDBS) {
            roomDBDao.deleteAllData();
            return null;
        }
    }
}
