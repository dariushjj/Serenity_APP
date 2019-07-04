package com.serenity.dao;

import com.serenity.dao.impl.AlarmDaoImpl;
import com.serenity.model.Alarm;
import com.serenity.model.Song;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class AlarmDao implements AlarmDaoImpl {
    public List<Alarm> getAllAlarms(){
        return LitePal.findAll(Alarm.class);
    }

    public void deleteAlarmByName(String name)
    {
        LitePal.deleteAll(Alarm.class, "name = ?", name);
    }

    public void addAlarm(String songName, String alarmTime, String week, String isTurnOn, String songUri){
        Alarm alarm = new Alarm();
        alarm.setName(songName);
        alarm.setAlarmTime(alarmTime);
        alarm.setWeek(week);
        alarm.setIsTurnOn(isTurnOn);
        alarm.setSongUri(songUri);
        alarm.save();
    }


    @Override
    public String get_absolutePath(String song) {
        ArrayList<Song> songs = (ArrayList<Song>)LitePal.where("name = ?", song).find(Song.class);
        return songs.get(0).getUri();
    }

    @Override
    public String getAllAlarmTime() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Alarm> alarms = (ArrayList<Alarm>)getAllAlarms();
        for (Alarm a : alarms){
            sb.append(a.getAlarmTime()).append(" ");
        }
        return sb.toString();
    }

    @Override
    public String getWeekByAlarmTime(String alarmTime) {
        ArrayList<Alarm> alarms = (ArrayList<Alarm>)LitePal.where("alarmtime = ?", alarmTime).find(Alarm.class);
        return alarms.get(0).getWeek();
    }

    @Override
    public String getSongByAlarmTime(String alarmTime) {
        ArrayList<Alarm> alarms = (ArrayList<Alarm>)LitePal.where("alarmtime = ?", alarmTime).find(Alarm.class);
        return alarms.get(0).getName();
    }
}
