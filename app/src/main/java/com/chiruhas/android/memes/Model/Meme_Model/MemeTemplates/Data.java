package com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("memes")
    @Expose
    private List<Meme> memes = null;

    public List<Meme> getMemes() {
        return memes;
    }

    public void setMemes(List<Meme> memes) {
        this.memes = memes;
    }

}