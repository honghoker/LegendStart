package com.example.ls_listsave.DataBase_Room.TagEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TagEntity.class}, version = 1)
public abstract class TagDatabase extends RoomDatabase {

    public abstract TagEntity_Dao tagEntity_dao();
    /*
    private static TagDatabase instance;
    public static synchronized TagDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext().getApplicationContext(),
                    TagDatabase.class, "Tag_Database")
                    .fallbackToDestructiveMigration()
                    .addCallback(tagCallback)
                    .build();
        }

        return instance;
    }
    public static Callback tagCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TagEntity_Dao tagEntity_dao;

        public PopulateDbAsyncTask(TagDatabase tagDatabase) {
            this.tagEntity_dao = tagDatabase.tagEntity_dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tagEntity_dao.insert(new TagEntity(1, "Tag_TEST"));
            return null;
        }
    }

     */
}

