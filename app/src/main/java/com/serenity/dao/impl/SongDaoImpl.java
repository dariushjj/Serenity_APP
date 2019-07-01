package com.serenity.dao.impl;

import android.graphics.Bitmap;

import com.serenity.model.Song;
import com.serenity.model.SongSheet;

import java.util.ArrayList;

public interface SongDaoImpl {
    boolean addSongToSongSheet(SongSheet songSheet, Song song);
    String getSinger(Song song);
    String getTitle(Song song);
    String getAlbum(Song song);
    Bitmap getAlbumImage(Song song);
    void addSong(String name, String singer, String uri);
    ArrayList<Song> getSongs();
    ArrayList<String> getLyric(Song song);
    ArrayList<String> getTime(Song song);

}
