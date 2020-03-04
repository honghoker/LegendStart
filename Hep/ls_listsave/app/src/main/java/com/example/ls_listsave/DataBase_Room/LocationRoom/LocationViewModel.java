package com.example.ls_listsave.DataBase_Room.LocationRoom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {
    private LocationRepository repository;
    private LiveData<List<LocationEntity>> allData;

    public LocationViewModel(@NonNull Application application) {
        super(application);
        repository = new LocationRepository(application);
        allData = repository.getAllLocations();
    }
    public int insert(LocationEntity locationEntity){
        repository.insert(locationEntity);
        return locationEntity.getId();
    }
    public void update(LocationEntity locationEntity){
        repository.update(locationEntity);
    }
    public LocationEntity delete(LocationEntity locationEntity){
        repository.delete(locationEntity);
        return locationEntity;
    }
    public void deleteAllDates(){
        repository.deleteAllDates();
    }
    public LiveData<List<LocationEntity>> getAllData(){
        return allData;
    }

}
