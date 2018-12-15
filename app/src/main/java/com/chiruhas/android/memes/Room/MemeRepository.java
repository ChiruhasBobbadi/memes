package com.chiruhas.android.memes.Room;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.chiruhas.android.memes.Model.RoomModel.CacheMemeModel;


import java.util.List;

import androidx.lifecycle.LiveData;

public class MemeRepository {
    private MemeDao memeDao;
    LiveData<List<CacheMemeModel>> listLiveData;
    public MemeRepository(Application application)
    {
        MemeDatabase memeDatabase = MemeDatabase.getInstance(application);
        memeDao = memeDatabase.memeDao();
        listLiveData = memeDao.getAllMemes();
    }
    public void insert(CacheMemeModel cacheMemeModel){

        new InsertAsync(memeDao).execute(cacheMemeModel);
    }
    public void delete(CacheMemeModel cacheMemeModel)
    {
        new Delete(memeDao).execute(cacheMemeModel);
    }
    public void deleteAll()
    {
        new DeletAllAsync(memeDao).execute();
    }
    public LiveData<List<CacheMemeModel>> getMemes()
    {
        return listLiveData;
    }

    public static class InsertAsync extends AsyncTask<CacheMemeModel,Void,Void>
    {
        private MemeDao memeDao;
        private InsertAsync(MemeDao memeDao1)
        {
            memeDao = memeDao1;
        }
        @Override
        protected Void doInBackground(CacheMemeModel... cacheMemeModels) {
            Log.d("Meme Repository",cacheMemeModels[0].getName());
            try{
                memeDao.insert(cacheMemeModels[0]);
            }
            catch (Exception e)
            {
                Log.d("Insertion Exception",": Handled");
            }
            return null;
        }
    }
    public static class DeletAllAsync extends AsyncTask<Void,Void,Void>
    {
        private MemeDao memeDao;
        private DeletAllAsync(MemeDao memeDao1)
        {
            memeDao = memeDao1;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            memeDao.deleteAll();
            return null;
        }
    }
    public static class Delete extends AsyncTask<CacheMemeModel,Void,Void>
    {

        private MemeDao memeDao;
        private Delete(MemeDao memeDao1)
        {
            memeDao = memeDao1;
        }



        @Override
        protected Void doInBackground(CacheMemeModel... cacheMemeModels) {
           memeDao.delete(cacheMemeModels[0]);

            return null;
        }
    }
}
