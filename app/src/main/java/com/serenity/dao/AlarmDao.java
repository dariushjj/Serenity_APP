package com.serenity.dao;

import com.serenity.model.Alarm;
import com.serenity.model.Song;


import java.util.List;

public class AlarmDao {
    public List<Alarm> getAllAlarms(){
        return new UserDao().getCurrentUser().getAlarms();
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


}
