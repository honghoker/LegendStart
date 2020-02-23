package com.example.ls_listsave.DataBase_Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LocationRepository {
    private LocationEntity_Dao locationEntity_dao;
    private LiveData<List<LocationEntity>> allLocations;

    public LocationRepository(Application application) {
        LocationDatabase locationDatabase = LocationDatabase.getInstance(application);
        locationEntity_dao = locationDatabase.locationEntity_dao();
        allLocations = locationEntity_dao.getAllData();
    }
    public void insert(LocationEntity locationEntity){
        new InsertLocationAsyncTask(locationEntity_dao).execute(locationEntity);
    }
    public void update(LocationEntity locationEntity){
        new UpdateLocationAsyncTask(locationEntity_dao).execute(locationEntity);
    }
    public void delete(LocationEntity locationEntity){
        new DeleteLocationAsyncTask(locationEntity_dao).execute(locationEntity);
    }
    public void deleteAllDates(){
        new DeleteAllLocationAsyncTask(locationEntity_dao).execute();
    }
    public LiveData<List<LocationEntity>> getAllLocations(){
        return allLocations;
    }
    private static class InsertLocationAsyncTask extends AsyncTask<LocationEntity,Void, Void>{
        private LocationEntity_Dao locationEntity_dao;
        private InsertLocationAsyncTask(LocationEntity_Dao locationEntity_dao){
            this.locationEntity_dao = locationEntity_dao;
        }
        @Override
        protected Void doInBackground(LocationEntity... locationEntities) {
            locationEntity_dao.insert(locationEntities[0]);
            return null;
        }
    }
    private static class UpdateLocationAsyncTask extends AsyncTask<LocationEntity,Void, Void>{
        private LocationEntity_Dao locationEntity_dao;
        private UpdateLocationAsyncTask(LocationEntity_Dao locationEntity_dao){
            this.locationEntity_dao = locationEntity_dao;
        }
        @Override
        protected Void doInBackground(LocationEntity... locationEntities) {
            locationEntity_dao.update(locationEntities[0]);
            return null;
        }
    }
    private static class DeleteLocationAsyncTask extends AsyncTask<LocationEntity,Void, Void>{
        private LocationEntity_Dao locationEntity_dao;
        private DeleteLocationAsyncTask(LocationEntity_Dao locationEntity_dao){
            this.locationEntity_dao = locationEntity_dao;
        }
        @Override
        protected Void doInBackground(LocationEntity... locationEntities) {
            locationEntity_dao.delete(locationEntities[0]);
            return null;
        }
    }
    private static class DeleteAllLocationAsyncTask extends AsyncTask<Void,Void, Void>{
        private LocationEntity_Dao locationEntity_dao;
        private DeleteAllLocationAsyncTask(LocationEntity_Dao locationEntity_dao){
            this.locationEntity_dao = locationEntity_dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            locationEntity_dao.deleteAllData();
            return null;
        }
    }
}
