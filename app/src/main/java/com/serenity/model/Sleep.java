package com.serenity.model;

/**
 * 表示睡眠情况的实体类
 * id：睡眠情况的唯一标识
 * date：睡眠日期
 * usePhoneNumb：使用手机次数
 * isDayDream：是否说梦话
 * listenTime：听歌时长
 */
public class Sleep {
    private int id;
    private String date;
    private int usePhoneNumb;
    private boolean isDayDream;
    private int listenTime;

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

    public int getUsePhoneNumb() {
        return usePhoneNumb;
    }

    public void setUsePhoneNumb(int usePhoneNumb) {
        this.usePhoneNumb = usePhoneNumb;
    }

    public boolean isDayDream() {
        return isDayDream;
    }

    public void setDayDream(boolean dayDream) {
        isDayDream = dayDream;
    }

    public int getListenTime() {
        return listenTime;
    }

    public void setListenTime(int listenTime) {
        this.listenTime = listenTime;
    }
}
