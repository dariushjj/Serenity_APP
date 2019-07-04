package com.serenity.view.alarmclock;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.serenityapp.R;
import android.app.Activity;

import android.graphics.Color;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.serenity.dao.AlarmDao;
import com.serenity.dao.SongDao;
import com.serenity.model.Alarm;
import com.wx.wheelview.adapter.ArrayWheelAdapter;

import com.wx.wheelview.adapter.SimpleWheelAdapter;

import com.wx.wheelview.common.WheelData;

import com.wx.wheelview.util.WheelUtils;

import com.wx.wheelview.widget.WheelView;

import com.wx.wheelview.widget.WheelViewDialog;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Arrays;

import java.util.Calendar;
import java.util.Date;
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
    private String time = "";
    private String day = "01011111";
    private String song ;
    private WheelView hourWheelView, minuteWheelView, secondWheelView;
    private PowerManager.WakeLock wakeLock = null;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setclock);
        getpermission();
        initWheel2();
        requestWakeLock();
        //``fvc Intent intent_s = new Intent(AlarmClockActivity.this,ServiceofClock.class);
        // TODO: 2019/7/4 服务会报错
        //startService(intent_s);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        TextView alarmmusic=(TextView)findViewById(R.id.alarmmusic);
        Intent intent=getIntent();
        String data=intent.getStringExtra("extra_data");
        song = intent.getStringExtra("path");
        alarmmusic.setText(data);
        Button forwardButton = (Button)findViewById(R.id.forwardbutton);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AlarmClockActivity.this, ChooseMusicActivity.class);
                startActivity(intent);
            }
        });

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
                time = hour.toString() + ":"+minute.toString();
                AlarmDao dao = new AlarmDao();
                dao.addAlarm(time ,time,get_states(),"1", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()+"/sheSay.mp3");
                hour = null;
                minute = null;
                Toast.makeText(AlarmClockActivity.this,"the colok will ring at "+time,Toast.LENGTH_LONG).show();
                //here we send Broadcase when time changes
                sendBroadcast(new Intent("update_time_action"));
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
        //here we set a receiver to get the message when time  changed
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("update_time_action");
        registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                if (intent.getAction().equals("update_time_action"))
                {
                    // TODO: 19-7-2  here the DAY_OF_WEEK should be considered and there may many alarms so need a function to return alarms ans Dayofwweks
//                  接收到广播之后先设置tv，然后重新设置AlarmManager
                    Log.d("time",time);
                    Log.d("getText()",getText());
                    Toast.makeText(AlarmClockActivity.this,"now time is "+getText()+" " + time,Toast.LENGTH_LONG).show();
                    if (time.equals(getText()))
                    {
                        // TODO: 19-7-3  here should be Week_day 
                        time = "";
                        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
                        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl =pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK , "StartupReceiver");
                        wl.acquire();
                        Toast.makeText(AlarmClockActivity.this,"time is up",Toast.LENGTH_SHORT).show();
                        Intent intent_c = new Intent(getApplicationContext(),BellRingingActivity.class);
                        intent.putExtra("time_",time);
                        startActivity(intent_c);
                        wl.release();
                        //tv.setText(getText());
                    }
                    else
                    {
                        //tv.setText("not now");
                    }
                    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                    PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
                    long nextTime = getNextTime();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                    {
                        am.setExact(AlarmManager.RTC_WAKEUP, nextTime, pi);
                    } else
                    {
                        am.set(AlarmManager.RTC_WAKEUP, nextTime, pi);
                    }
                }
            }
        }, intentFilter);
    }

    private String get_states()
    {
        String states = "";
        if(b_sub_square0)
        {
            states = states+"1";
        }
        else
        {
            states=states+"0";
        }
        if(b_sub_square1)
        {
            states = states+"1";
        }
        else
        {
            states=states+"0";
        }
        if(b_sub_square2)
        {
            states = states+"1";
        }
        else
        {
            states=states+"0";
        }
        if(b_sub_square3)
        {
            states = states+"1";
        }
        else
        {
            states=states+"0";
        }
        if(b_sub_square4)
        {
            states = states+"1";
        }
        else
        {
            states=states+"0";
        }
        if(b_sub_square5)
        {
            states = states+"1";
        }
        else
        {
            states=states+"0";
        }
        if(b_sub_square6)
        {
            states = states+"1";
        }
        else
        {
            states=states+"0";
        }
        return  states;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getpermission()
    {
        int check = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(check != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(AlarmClockActivity.this,new String [] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        check = checkSelfPermission(Manifest.permission.VIBRATE);
        if(check != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(AlarmClockActivity.this,new String [] {Manifest.permission.VIBRATE},1);
        }
    }

    //    获取当前时间的样式
    private String getText()
    {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    //    获取下次更新TextView的时间
    private long getNextTime()
    {
        long now = System.currentTimeMillis();
        return now + 60 * 1000 - now % (60 * 1000);
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



    private ArrayList<String> createArrays()
    {

        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < 20; i++)
        {

            list.add("item" + i);

        }

        return list;
    }

    @SuppressLint("InvalidWakeLockTag")
    public  void requestWakeLock() {
        if (wakeLock == null) {
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TESTAPP");
            wakeLock.setReferenceCounted(false);
        }
        wakeLock.acquire();
    }
    public int get_DAYOFWEEK()
    {
        Calendar calendar = Calendar.getInstance();
        return  calendar.get(Calendar.DAY_OF_WEEK);

    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
