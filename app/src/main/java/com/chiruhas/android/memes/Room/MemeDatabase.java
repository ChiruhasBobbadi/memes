package com.chiruhas.android.memes.Room;

import android.content.Context;

import com.chiruhas.android.memes.Model.RoomModel.CacheMemeModel;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CacheMemeModel.class},version = 1)
public abstract class MemeDatabase extends RoomDatabase {
    public static MemeDatabase instance ;
    public abstract MemeDao memeDao();
    public static synchronized MemeDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),MemeDatabase.class,"meme_database")
                    .fallbackToDestructiveMigration().build();


        }

            return instance;

    }

}
