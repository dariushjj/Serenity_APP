package com.serenity.model;

import java.util.ArrayList;

/**
 * 表示用户的收藏歌单
 * id：歌单唯一标识
 * name：歌单名称
 * description：歌单描述
 * songs：歌单中的所有歌
 */
public class SongSheet {
    private int id;
    private String name;
    private String description;
    private ArrayList<Song> songs;

    public int getSongNumber(){
        return this.songs.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
