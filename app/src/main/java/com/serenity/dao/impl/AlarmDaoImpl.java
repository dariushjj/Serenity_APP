package com.serenity.dao.impl;

import com.serenity.model.Alarm;

import java.util.List;

public interface AlarmDaoImpl
{
    List<Alarm> getAllAlarms();
    int get_alarm_jiange();   // get the time between clock rings
    String get_local_songs(); // get local songs
    String get_absolutePath(String song); //get the absolutePath of the local song
    void setSong(String song,Alarm alarm); //set song for the clock
    String get_alarm_times(); //get all ararms time return like "A:B + C:D ...."
    //String getState(Alarm alarm);
    /**
     * time: hh:mm:ss
     * state: 是一个长度为7的字符串，表示周一到周日的状态 例如：1100110
     */
    //void addAlarm(String time, String state, Song song);
}
