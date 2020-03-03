package com.example.ls_listsave.DataBase_Room.TagEntity;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TagRepository {
    private TagEntity_Dao tagEntity_dao;
    private LiveData<List<TagEntity>> allTags;
    private TagEntity[] selectDismissTagEntities;
    private int position;

    public void setPosition(int position) {
        this.position = position;
    }

    public TagRepository(Application application) {
        TagDatabase tagDatabase = TagDatabase.getInstance(application);
        tagEntity_dao = tagDatabase.tagEntity_dao();
        allTags = tagEntity_dao.getAllData();
        selectDismissTagEntities = tagEntity_dao.multipleSelectionByForeignKey(position);
    }
    /*
    public static class InsertTagAsyncTask extends AsyncTask<TagEntity, Void, Void>{
        private TagEntity_Dao tagEntity_dao;
        public InsertTagAsyncTask(TagEntity_Dao tagEntity_dao) {
            this.tagEntity_dao = tagEntity_dao;
        }

        @Override
        protected Void doInBackground(TagEntity... tagEntities) {
            tagEntity_dao.insert(tagEntities[0]);
            return null;
        }
    }
    public void insert(TagEntity tagEntity){
        new InsertTagAsyncTask(tagEntity_dao).execute(tagEntity);
    }
    public void update(TagEntity tagEntity){
        new UpdateTagAsyncTask(tagEntity_dao).execute(tagEntity);
    }
    public void delete(TagEntity... tagEntities){
        new DeleteTagAsyncTask(tagEntity_dao).execute(tagEntities);
    }

    public static class UpdateTagAsyncTask extends AsyncTask<TagEntity, Void, Void>{
        private TagEntity_Dao tagEntity_dao;
        public UpdateTagAsyncTask(TagEntity_Dao tagEntity_dao) {
            this.tagEntity_dao = tagEntity_dao;
        }

        @Override
        protected Void doInBackground(TagEntity... tagEntities) {
            tagEntity_dao.update(tagEntities[0]);
            return null;
        }
    }
    public static class DeleteTagAsyncTask extends AsyncTask<TagEntity, Void, Void>{
        private TagEntity_Dao tagEntity_dao;
        public DeleteTagAsyncTask(TagEntity_Dao tagEntity_dao) {
            this.tagEntity_dao = tagEntity_dao;
        }

        @Override
        protected Void doInBackground(TagEntity... tagEntities) {
            tagEntity_dao.delete(tagEntities);
            return null;
        }
    }
    public static class MultipleSelectionAsyncTask extends AsyncTask<TagEntity, Void, Void>{
        private TagEntity_Dao tagEntity_dao;
        private int position;
        public MultipleSelectionAsyncTask(TagEntity_Dao tagEntity_dao, int position) {
            this.tagEntity_dao = tagEntity_dao;
            this.position = position;
        }

        @Override
        protected Void doInBackground(TagEntity... tagEntities) {
            tagEntity_dao.multipleSelectionByForeignKey(position);
            return null;
        }
    }

     */
}
