package com.serenity.model;

/**
 * 歌曲的实体类
 * id：每首歌曲的唯一标识
 * name：歌曲名称
 * album：歌曲所属专辑
 * singer：唱歌者
 * length：歌曲长度
 * lyric：歌词
 */
public class Song {
    private int id;
    private String name;
    private String album;
    private String singer;
    private int length;
    private String lyric;

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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
}
