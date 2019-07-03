package com.serenity.dao;

import android.content.ContentValues;

import com.serenity.dao.impl.AlarmDaoImpl;
import com.serenity.model.Alarm;
import com.serenity.model.Song;

import org.litepal.LitePal;

import java.util.List;

public class AlarmDao implements AlarmDaoImpl {
    public List<Alarm> getAllAlarms(){
        return new UserDao().getCurrentUser().getAlarms();
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
    public void addAlarm(String date, Song song, boolean isRepeat, String repeatTime){
        Alarm alarm = new Alarm();
        alarm.setDate(date);
        alarm.setSong(song);
        alarm.setRepeat(isRepeat);
        alarm.setRepeatTime(repeatTime);
        alarm.setUser(new UserDao().getCurrentUser());
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
        return null;
    }

    @Override
    public void setSong(String song, Alarm alarm) {

    }

    @Override
    public String get_alarm_times() {
        return null;
    }
}
