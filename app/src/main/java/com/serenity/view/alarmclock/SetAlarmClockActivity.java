package com.serenity.view.alarmclock;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.serenityapp.R;
import com.serenity.dao.AlarmDao;
import com.serenity.model.Alarm;
import com.serenity.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;
//main alarm show
public class SetAlarmClockActivity extends AppCompatActivity {
    private List<Time> timeList = new ArrayList<>();
    private Boolean b_sub_square0 = false;
    private Boolean b_sub_square1 = false;
    private Boolean b_sub_square2 = false;
    private Boolean b_sub_square3 = false;
    private Boolean b_sub_square4 = false;
    private Boolean b_sub_square5 = false;
    private Boolean b_sub_square6 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmclock);

        ActionBar actionBar = getSupportActionBar();
        initTime();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final TimeAdapter adapter = new TimeAdapter(timeList,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new TimeAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                adapter.setPosition(position);
                adapter.notifyDataSetChanged();
            }
        });


        if(actionBar != null){
            actionBar.hide();
        }
        Button addButton = (Button)findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SetAlarmClockActivity.this,AlarmClockActivity.class);
                startActivity(intent);
            }
        });
        Button backtomainbutton = (Button)findViewById(R.id.backtomainbutton);
        backtomainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SetAlarmClockActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }



    private void initTime() {
       // this.timeList = null;
        //init time list
       //AlarmDao alarmDao = new AlarmDao();
       //alarmDao.addAlarm(String time, String state, Song song);
        // TODO: 19-7-2  here a function should return all alarms and their states
            AlarmDao alarmDao = new AlarmDao();
            ArrayList<Alarm> alarms = (ArrayList<Alarm>)alarmDao.getAllAlarms();
            for(Alarm a : alarms)
            {
                String name = a.getName();
                String week = a.getWeek();
                boolean [] weeks = new boolean[7];
                for(int i = 0;i<week.length();i++)
                {
                    weeks[i] = week.charAt(i) == '1' ? true :false;
                }
                timeList.add(new Time(name,weeks[0],weeks[1],weeks[2],weeks[3],weeks[4],weeks[5],weeks[6]));
            }

    }

    }
