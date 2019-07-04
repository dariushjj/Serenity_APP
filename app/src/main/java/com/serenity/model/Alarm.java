package com.serenity.model;

import org.litepal.crud.LitePalSupport;

/**
 * 闹铃的实体类
 * id：闹钟的唯一标识
 * date：闹钟的响铃日期和时间
 * song：闹钟响铃时的铃声
 * isRepeat：闹钟是否重复
 * repeatTime：闹钟每次重复的间隔
 */
public class Alarm extends LitePalSupport {
    private int id;
    private String name;
    private String alarmTime;
    private String week;
    private String isTurnOn;
    private String songUri;

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

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getIsTurnOn() {
        return isTurnOn;
    }

    public void setIsTurnOn(String isTurnOn) {
        this.isTurnOn = isTurnOn;
    }

    public String getSongUri() {
        return songUri;
    }

    public void setSongUri(String songUri) {
        this.songUri = songUri;
    }
}
