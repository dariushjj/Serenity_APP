package com.serenity.dao.impl;

import com.serenity.model.Alarm;
import com.serenity.model.Song;

import java.util.List;

public interface AlarmDaoImpl
{
    List<Alarm> getAllAlarms();
    //String getState(Alarm alarm);
    /**
     * time: hh:mm:ss
     * state: 是一个长度为7的字符串，表示周一到周日的状态 例如：1100110
     */
    //void addAlarm(String time, String state, Song song);
}
