package com.serenity.dao;

import android.graphics.Bitmap;

import com.serenity.dao.impl.SongDaoImpl;
import com.serenity.model.Song;
import com.serenity.model.SongSheet;

import org.litepal.LitePal;

import java.util.ArrayList;

public class SongDao implements SongDaoImpl {
    public boolean addSongToSongSheet(SongSheet songSheet, Song song){
        song.getSongSheets().add(songSheet);
        return song.save();
    }

    @Override
    public void addSong(String name, String singer, String uri) {
        Song song = new Song();
        song.setName(name);
        song.setSinger(singer);
        song.setUri(uri);
        song.save();
    }

    @Override
    public ArrayList<Song> getSongs() {
        ArrayList<Song> songs = (ArrayList<Song>)LitePal.findAll(Song.class);
        return songs;
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
    public ArrayList<String> getTime(Song song) {
        return null;
    }
}
