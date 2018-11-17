package com.chiruhas.android.memes.Cache.Caching;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meme_table")
public class CacheModel {
    String name;
    String id;
    Bitmap bitmap;
    int width;
    int height;
    String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public CacheModel(String name, String id, Bitmap bitmap, int width, int height,String time) {

        this.name = name;
        this.id = id;
        this.bitmap = bitmap;
        this.width = width;
        this.height = height;
        timestamp = time;
    }
}
