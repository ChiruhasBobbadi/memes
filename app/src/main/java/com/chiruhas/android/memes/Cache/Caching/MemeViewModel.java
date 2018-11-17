package com.chiruhas.android.memes.Cache.Caching;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MemeViewModel extends AndroidViewModel {
    MemeRepo memeRepo;
    LiveData<List<CacheModel>> liveData ;
    public MemeViewModel(Application app)
    {
        super(app);
        memeRepo = new MemeRepo(app);
        liveData = memeRepo.getAll_memes();

    }

    public void insert(CacheModel c)
    {
        memeRepo.insert(c);
    }
    public void delete(CacheModel c)
    {
        memeRepo.delete(c);
    }
    public void update(CacheModel c)
    {
        memeRepo.update(c);
    }
    public void deleteAll()
    {
        memeRepo.deleteAll();
    }
    LiveData<List<CacheModel>> getAllMemes()
    {
        return liveData;
    }
}
