package com.serenity.view.alarmclock;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.serenityapp.R;
import android.app.Activity;

import android.graphics.Color;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;


import com.wx.wheelview.adapter.ArrayWheelAdapter;

import com.wx.wheelview.adapter.SimpleWheelAdapter;

import com.wx.wheelview.common.WheelData;

import com.wx.wheelview.util.WheelUtils;

import com.wx.wheelview.widget.WheelView;

import com.wx.wheelview.widget.WheelViewDialog;



import java.util.ArrayList;

import java.util.Arrays;

import java.util.HashMap;

import java.util.List;


//set the alarm you want
public class AlarmClockActivity extends AppCompatActivity {
    private Boolean b_sub_square0 = false;
    private Boolean b_sub_square1 = false;
    private Boolean b_sub_square2 = false;
    private Boolean b_sub_square3 = false;
    private Boolean b_sub_square4 = false;
    private Boolean b_sub_square5 = false;
    private Boolean b_sub_square6 = false;
    private WheelView hourWheelView, minuteWheelView, secondWheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setclock);

        initWheel2();

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Button backButton = (Button)findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AlarmClockActivity.this, SetAlarmClockActivity.class);
                startActivity(intent);
            }
        });

        Button decisionButton = (Button)findViewById(R.id.decisionbutton);
        decisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button mondayButton = (Button)findViewById(R.id.daymonday);

                WheelView wheelViewhour=(WheelView)findViewById(R.id.hour_wheelview);
                WheelView wheelViewminute=(WheelView)findViewById(R.id.minute_wheelview);
                WheelView wheelViewsecond=(WheelView)findViewById(R.id.second_wheelview);
                Object hour=wheelViewhour.getSelectionItem().toString();

                Object minute=wheelViewminute.getSelectionItem().toString();

                Object second=wheelViewsecond.getSelectionItem().toString();
                Intent intent=new Intent(AlarmClockActivity.this,SetAlarmClockActivity.class);
                startActivity(intent);
            }
        });
        final Button sundayButton = (Button)findViewById(R.id.daysunday);
        sundayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!b_sub_square0) {
                    b_sub_square0 = true;

                    sundayButton.setActivated(b_sub_square0);

                } else {
                    b_sub_square0 = false;
                    sundayButton.setActivated(b_sub_square0);
            }
                Log.d("asdasdas",b_sub_square0.toString());
        }
    });
        final Button mondayButton = (Button)findViewById(R.id.daymonday);
        mondayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!b_sub_square1) {
                    b_sub_square1 = true;
                    mondayButton.setActivated(b_sub_square1);
                } else {
                    b_sub_square1 = false;
                    mondayButton.setActivated(b_sub_square1);
                }
            }
        });
        final Button tuesdayButton = (Button)findViewById(R.id.daytuesday);
        tuesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!b_sub_square2) {
                    b_sub_square2 = true;
                    tuesdayButton.setActivated(b_sub_square2);
                } else {
                    b_sub_square2 = false;
                    tuesdayButton.setActivated(b_sub_square2);
                }
            }
        });
        final Button wednesdayButton = (Button)findViewById(R.id.daywednesday);
        wednesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!b_sub_square3) {
                    b_sub_square3 = true;
                    wednesdayButton.setActivated(b_sub_square3);
                } else {
                    b_sub_square3 = false;
                    wednesdayButton.setActivated(b_sub_square3);
                }
            }
        });
        final Button thursdayButton = (Button)findViewById(R.id.daythursday);
        thursdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!b_sub_square4) {
                    b_sub_square4 = true;
                    thursdayButton.setActivated(b_sub_square4);
                } else {
                    b_sub_square4 = false;
                    thursdayButton.setActivated(b_sub_square4);
                }
            }
        });
        final Button fridayButton = (Button)findViewById(R.id.dayfriday);
        fridayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!b_sub_square5) {
                    b_sub_square5 = true;
                    fridayButton.setActivated(b_sub_square5);
                } else {
                    b_sub_square5 = false;
                    fridayButton.setActivated(b_sub_square5);
                }
            }
        });
        final Button saturdayButton = (Button)findViewById(R.id.daysaturday);
        saturdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!b_sub_square6) {
                    b_sub_square6 = true;
                    //write to the database
                    saturdayButton.setActivated(b_sub_square6);
                } else {
                    b_sub_square6 = false;
                    //write to the database
                    saturdayButton.setActivated(b_sub_square6);
                }
            }
        });
    }





    /**

     * holo皮肤

     */

    private void initWheel2() {

        hourWheelView = (WheelView) findViewById(R.id.hour_wheelview);

        hourWheelView.setWheelAdapter(new ArrayWheelAdapter(this));

        hourWheelView.setSkin(WheelView.Skin.None);

        hourWheelView.setWheelData(createHours());

        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();

        style.selectedTextColor = Color.parseColor("#fffafa");

        style.textColor = Color.parseColor("#BFD6D7D7");

        style.selectedTextSize = 30;

        style.backgroundColor=Color.parseColor("#00000000");

        hourWheelView.setStyle(style);

        minuteWheelView = (WheelView) findViewById(R.id.minute_wheelview);

        minuteWheelView.setWheelAdapter(new ArrayWheelAdapter(this));

        minuteWheelView.setSkin(WheelView.Skin.None);

        minuteWheelView.setWheelData(createMinutes());

        minuteWheelView.setStyle(style);

        secondWheelView = (WheelView) findViewById(R.id.second_wheelview);

        secondWheelView.setWheelAdapter(new ArrayWheelAdapter(this));

        secondWheelView.setSkin(WheelView.Skin.None);

        secondWheelView.setWheelData(createMinutes());

        secondWheelView.setStyle(style);

    }

    private ArrayList<String> createHours() {

        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < 24; i++) {

            if (i < 10) {

                list.add("0" + i);

            } else {

                list.add("" + i);

            }

        }

        return list;

    }



    private ArrayList<String> createMinutes() {

        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < 60; i++) {

            if (i < 10) {

                list.add("0" + i);

            } else {

                list.add("" + i);

            }

        }

        return list;

    }



    private ArrayList<String> createArrays() {

        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < 20; i++) {

            list.add("item" + i);

        }

        return list;

    }
}
