package com.chiruhas.android.memes.Model.RoomModel;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meme_table")
public class CacheMemeModel {


    @NonNull
    @PrimaryKey()
    String name;
    String url;
    int width;
    int height;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public CacheMemeModel(String name, String url, int width, int height) {

        this.name = name;
        this.url = url;
        this.width = width;
        this.height = height;
    }
}
