package com.chiruhas.android.memes.viewmodel;

import android.app.Application;

import com.chiruhas.android.memes.Data.DataRepository;
import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.MemeModel;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

public class MemeViewModel extends AndroidViewModel {
    DataRepository repository;
    LiveData<MemeModel> meme;

    public MemeViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository();
        meme =repository.getMemeData();
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

//    public LiveData<MemeModel> getMemes() {
//        return meme;
//    }
}
