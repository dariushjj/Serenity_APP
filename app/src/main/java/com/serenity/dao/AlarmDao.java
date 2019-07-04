package com.serenity.dao;

import android.content.ContentValues;

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

    public void updateSong(Alarm alarm,String newsong)
    {
        ContentValues values = new ContentValues();
        values.put("song",newsong);
        LitePal.update(Alarm.class,values,alarm.getId());
    }
    public void updateDate(Alarm alarm, String newDate)
    {
        ContentValues values  = new ContentValues();
        values.put("date",newDate);
        LitePal.update(Alarm.class,values,alarm.getId());
    }
    public void updateIsRepeat(Alarm alarm,boolean newIsRepeat)
    {
        ContentValues values = new ContentValues();
        values.put("isRepeat",newIsRepeat);
        LitePal.update(Alarm.class,values,alarm.getId());
    }
    public void updateRepeatTime(Alarm alarm, String newRepeatTime)
    {
        ContentValues values = new ContentValues();
        values.put("repeatTime",newRepeatTime);
        LitePal.update(Alarm.class,values,alarm.getId());
    }
    public void deleteAlarm(Alarm alarm)
    {
        LitePal.delete(Alarm.class,alarm.getId());
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
    public int get_alarm_jiange() {
        return 0;
    }

    @Override
    public String get_local_songs() {
        return null;
    }

    @Override
    public String get_absolutePath(String song) {
        ArrayList<Song> songs = (ArrayList<Song>)LitePal.where("where name = ?", song).find(Song.class);
        return songs.get(0).getUri();
    }

    @Override
    public void setSong(String song, Alarm alarm) {

    }

    @Override
    public String get_alarm_times() {
        return null;
    }
}
