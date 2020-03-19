package com.example.myfragment1.LocationList_RecyclerView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTagEntity;
import com.example.myfragment1.DataBase_Room.Repository.LocationRepository;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {
    private LocationRepository repository;
    private LiveData<List<LocationEntity>> allLocationData;
    private LiveData<List<TagEntity>> allTagData;
    public LocationViewModel(@NonNull Application application) {
        super(application);
        repository = new LocationRepository(application);
        allLocationData = repository.getAllLocations();
        allTagData = repository.getAllTags();

    }
    public int location_insert(LocationEntity locationEntity){
        repository.insert_Location(locationEntity);
        return locationEntity.getId();
    }
    public void location_update(LocationEntity locationEntity){
        repository.update_Location(locationEntity);
    }
    public LocationEntity location_delete(LocationEntity locationEntity){
        repository.delete_Location(locationEntity);
        return locationEntity;
    }
    public TagEntity[] tag_delete(TagEntity... tagEntities){
        repository.delete_Tag(tagEntities);
        return tagEntities;
    }
    public void tag_insert(TagEntity tagEntity){
        repository.insert_Tag(tagEntity);
    }
    public void locationTag_insert(LocationTagEntity locationTagEntity){
        repository.insert_LocationTag(locationTagEntity);
    }
    public LocationTagEntity[] locationTag_delete(LocationTagEntity... locationTagEntities){
        repository.delete_LocationTag(locationTagEntities);
        return locationTagEntities;
    }
    public List<LocationTagEntity> locationTag_FindLocationID(int locationId){
        return repository.searchByLocationID_LocationTag(locationId);
    }

    public void deleteAllDates(){
        repository.deleteAllDates();
    }
    public LiveData<List<LocationEntity>> getAllLocationData(){
        return allLocationData;
    }
    public LiveData<List<TagEntity>> getAllTagData(){
        return allTagData;
    }


    public List<TagEntity> searchTagByLocationID(int position){
        return repository.searchAboutLocationId(position);
    }
}
