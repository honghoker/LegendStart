package com.example.ls_listsave.DataBase_Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {
    private LocationRepository repository;
    private LiveData<List<LocationEntity>> allDatas;

    public LocationViewModel(@NonNull Application application) {
        super(application);
        repository = new LocationRepository(application);
        allDatas = repository.getAllLocations();
    }
    public void insert(LocationEntity locationEntity){
        repository.insert(locationEntity);
    }
    public void update(LocationEntity locationEntity){
        repository.update(locationEntity);
    }
    public void delete(LocationEntity locationEntity){
        repository.delete(locationEntity);
    }
    public void deleteAllDates(){
        repository.deleteAllDates();
    }
    public LiveData<List<LocationEntity>> getAllData(){
        return allDatas;
    }

}
