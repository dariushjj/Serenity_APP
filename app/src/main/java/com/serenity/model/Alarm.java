package com.serenity.model;

/**
 * 闹铃的实体类
 * id：闹钟的唯一标识
 * date：闹钟的响铃日期和时间
 * song：闹钟响铃时的铃声
 * isRepeat：闹钟是否重复
 * repeatTime：闹钟每次重复的间隔
 */
public class Alarm {
    private int id;
    private String date;
    private Song song;
    private boolean isRepeat;
    private String repeatTime;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }

    public String getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(String repeatTime) {
        this.repeatTime = repeatTime;
    }
}
