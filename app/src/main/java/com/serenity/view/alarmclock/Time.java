package com.serenity.view.alarmclock;

public class Time {
    private String name;
    boolean state1;
    boolean state2;
    boolean state3;
    boolean state4;
    boolean state5;
    boolean state6;
    boolean state7;
    //private int imageId;
    public Time(String name, boolean state1, boolean state2, boolean state3, boolean state4, boolean state5, boolean state6, boolean state7) {
        this.name = name;
        this.state1=state1;
        this.state2=state2;
        this.state3=state3;
        this.state4=state4;
        this.state5=state5;
        this.state6=state6;
        this.state7=state7;
        //this.imageId = imageId;
    }
    public String getName() {
        return name;
    }
    public boolean getState1() {
        return state1;
    }
    public boolean getState2() {
        return state2;
    }
    public boolean getState3() {
        return state3;
    }
    public boolean getState4() {
        return state4;
    }
    public boolean getState5() {
        return state5;
    }
    public boolean getState6() {
        return state6;
    }
    public boolean getState7() {
        return state7;
    }

    /*
    public int getImageId() {
        return imageId;
    }
    */
}
