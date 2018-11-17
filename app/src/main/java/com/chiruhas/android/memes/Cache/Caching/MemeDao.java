package com.chiruhas.android.memes.Cache.Caching;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MemeDao {
    @Insert
    void insert(CacheModel c);
    @Delete
    void delete(CacheModel c);
    @Update
    void update(CacheModel c);
    @Query("DELETE FROM meme_table")
    void deleteAll();

    @Query("SELECT * FROM meme_table ORDER BY timestamp")
    LiveData<List<CacheModel>>  getAllMemes();
}
