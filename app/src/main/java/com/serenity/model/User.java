package com.serenity.model;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 表示用户的实体类
 * id：用户的唯一标识
 * name：用户的呢称（也是登录名）
 * password：用户的密码
 * age：用户年龄
 * isMale：用户的性别（True是男）
 * songSheets：用户保存的歌单
 * alarms：用户的所有闹钟
 * sleeps：用户的睡眠情况集合
 */
public class User extends LitePalSupport {
    private int id;
    private String name;
    private String password;
    private int age;
    private boolean isMale;
    private boolean isSignIn = false;
    private List<SongSheet> songSheets = new ArrayList<>();
    private List<Alarm> alarms = new ArrayList<>();
    private List<Sleep> sleeps = new ArrayList<>();

    public boolean isSignIn() {
        return isSignIn;
    }

    public void setSignIn(boolean signIn) {
        isSignIn = signIn;
    }

    public List<SongSheet> getSongSheets() {
        return LitePal.where("user_id = ?", String.valueOf(id)).find(SongSheet.class);
    }

    public void setSongSheets(List<SongSheet> songSheets) {
        this.songSheets = songSheets;
    }

    public List<Alarm> getAlarms() {
        return LitePal.where("user_id = ?", String.valueOf(id)).find(Alarm.class);
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public List<Sleep> getSleeps() {
        return LitePal.where("user_id = ?", String.valueOf(id)).find(Sleep.class);
    }

    public void setSleeps(List<Sleep> sleeps) {
        this.sleeps = sleeps;
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
