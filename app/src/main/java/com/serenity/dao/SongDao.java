package com.serenity.dao;

import android.graphics.Bitmap;

import com.serenity.dao.impl.SongDaoImpl;
import com.serenity.model.Song;
import com.serenity.model.SongSheet;

import java.util.ArrayList;

public class SongDao implements SongDaoImpl {
    @Override
    public boolean addSongToSongSheet(SongSheet songSheet, Song song){
        song.getSongSheets().add(songSheet);
        return song.save();
    }

    @Override
    public String getSinger(Song song) {
        return null;
    }

    @Override
    public String getTitle(Song song) {
        return null;
    }

    @Override
    public String getAlbum(Song song) {
        return null;
    }

    @Override
    public Bitmap getAlbumImage(Song song) {
        return null;
    }

    @Override
    public ArrayList<String> getLyric(Song song) {
        return null;
    }

    @Override
    public ArrayList<String> getTime(Song song){
        return null;
    }
}
