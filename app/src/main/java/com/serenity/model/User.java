package com.serenity.model;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;

/**
 * 表示用户的实体类
 * uid：用户的唯一标识
 * name：用户的呢称（也是登录名）
 * password：用户的密码
 * age：用户年龄
 * isMale：用户的性别（True是男）
 * songSheets：用户保存的歌单
 */
public class User extends LitePalSupport {
    private int uid;
    private String name;
    private String password;
    private int age;
    private boolean isMale;
    private ArrayList<SongSheet> songSheets;

    public ArrayList<SongSheet> getSongSheets() {
        return songSheets;
    }

    public void setSongSheets(ArrayList<SongSheet> songSheets) {
        this.songSheets = songSheets;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
