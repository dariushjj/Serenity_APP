package com.serenity.dao;

import com.serenity.dao.impl.SongDaoImpl;
import com.serenity.model.Song;
import com.serenity.model.SongSheet;

public class SongDao implements SongDaoImpl {
    public boolean addSongToSongSheet(SongSheet songSheet, Song song){
        song.getSongSheets().add(songSheet);
        return song.save();
    }
}
