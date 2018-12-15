package com.chiruhas.android.memes.viewmodel;

import android.app.Application;
import android.util.Log;

import com.chiruhas.android.memes.Data.DataRepository;
import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.MemeModel;
import com.chiruhas.android.memes.Model.RoomModel.CacheMemeModel;
import com.chiruhas.android.memes.Room.MemeRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

public class MemeViewModel extends AndroidViewModel {
    DataRepository repository;
    LiveData<MemeModel> meme;

    MemeRepository repo;
    LiveData<List<CacheMemeModel>> cache;

    public MemeViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository();
        meme =repository.getMemeData();

        repo = new MemeRepository(application);
        cache = repo.getMemes();

    }

    public LiveData<MemeModel> getMemes() {
    meme = Transformations.map(repository.getMemeData(), new Function<MemeModel, MemeModel>() {
        @Override
        public MemeModel apply(MemeModel input) {
            return input;
        }
    });
    return meme;
    }

    //offline operations

    public void insert(CacheMemeModel cacheMemeModel)
    {
        Log.d("ViewModel",cacheMemeModel.getName());
        repo.insert(cacheMemeModel);
    }
    public void deleteAll()
    {
        repo.deleteAll();
    }
    public LiveData<List<CacheMemeModel>> getAll()
    {
        return cache;
    }
    public void delete(CacheMemeModel cacheMemeModel)
    {
        repo.delete(cacheMemeModel);
    }

}
