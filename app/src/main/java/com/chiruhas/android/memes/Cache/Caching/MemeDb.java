package com.chiruhas.android.memes.Cache.Caching;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CacheModel.class},version = 1)
public abstract class MemeDb extends RoomDatabase {
    private static MemeDb memeDb;
    public abstract MemeDao memeDao();
    public static synchronized MemeDb getInstance(Context context) {
        if (memeDb == null) {
            memeDb = Room.databaseBuilder(context.getApplicationContext(), MemeDb.class, "meme_database").fallbackToDestructiveMigration().build();
        }
        return memeDb;
    }

    }

