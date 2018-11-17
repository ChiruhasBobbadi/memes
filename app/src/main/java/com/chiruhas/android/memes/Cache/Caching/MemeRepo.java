package com.chiruhas.android.memes.Cache.Caching;

import android.app.Application;
import android.os.AsyncTask;

import com.chiruhas.android.memes.Pojo.Templates.Data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

public class MemeRepo {
    private MemeDao dao;
    private LiveData<List<CacheModel>> all_memes;
    public MemeRepo(Application app)
    {
        MemeDb database = MemeDb.getInstance(app);
        dao = database.memeDao();
        all_memes = dao.getAllMemes();
    }

    public void insert(CacheModel m)
    {
        new InsertNoteAsyncTask(dao).execute(m);
    }
    public void update(CacheModel m)
    {
        new UpdateNoteAsyncTask(dao).execute(m);
    }public void delete(CacheModel m)
    {
        new DeleteNoteAsyncTask(dao).execute(m);
    }public void deleteAll()
    {
        new DeleteAllNoteAsyncTask(dao).execute();
    }

    public LiveData<List<CacheModel>> getAll_memes() {
        return all_memes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<CacheModel,Void,Void>
    {
        private MemeDao memeDao;
        private InsertNoteAsyncTask(MemeDao memeDao1)
        {
            memeDao = memeDao1;
        }

        @Override
        protected Void doInBackground(CacheModel... cacheModels) {
            memeDao.insert(cacheModels[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<CacheModel,Void,Void>
    {
        private MemeDao memeDao;
        private UpdateNoteAsyncTask(MemeDao memeDao1)
        {
            memeDao = memeDao1;
        }

        @Override
        protected Void doInBackground(CacheModel... cacheModels) {
            memeDao.update(cacheModels[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<CacheModel,Void,Void>
    {
        private MemeDao memeDao;
        private DeleteNoteAsyncTask(MemeDao memeDao1)
        {
            memeDao = memeDao1;
        }

        @Override
        protected Void doInBackground(CacheModel... cacheModels) {
            memeDao.delete(cacheModels[0]);
            return null;
        }
    }
    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private MemeDao memeDao;
        private DeleteAllNoteAsyncTask(MemeDao memeDao1)
        {
            memeDao = memeDao1;
        }

        @Override
        protected Void doInBackground(Void... v) {
            memeDao.deleteAll();
            return null;
        }
    }

}
