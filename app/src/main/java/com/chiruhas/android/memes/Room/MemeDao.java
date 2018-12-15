package com.chiruhas.android.memes.Room;

import com.chiruhas.android.memes.Model.RoomModel.CacheMemeModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;



@Dao
public interface MemeDao {

    @Insert
     void insert(CacheMemeModel cacheMemeModel);
    @Delete
     void delete(CacheMemeModel cacheMemeModel);
    @androidx.room.Query("DELETE FROM meme_table")
     void deleteAll();

    @androidx.room.Query("SELECT * FROM meme_table")
    LiveData<List<CacheMemeModel>> getAllMemes();

}
