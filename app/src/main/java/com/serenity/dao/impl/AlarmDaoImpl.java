package com.serenity.dao.impl;

import com.serenity.model.Alarm;

import java.util.List;

public interface AlarmDaoImpl
{

    List<Alarm> getAllAlarms();
    void deleteAlarmByName(String name);
    void addAlarm(String songName, String alarmTime, String week, String isTurnOn, String songUri);
    String get_absolutePath(String song);
    String getAllAlarmTime();
    String getWeekByAlarmTime(String alarmTime);
    String getSongUriByAlarmTime(String alarmTime);
}
