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
        initFruits();
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



    private void initFruits() {
        // TODO: 19-7-2  here a function should return all alarms and their starts
            Time apple = new Time("Apple",true,false,true,true,false,true,false);
            timeList.add(apple);
            Time banana = new Time("Banana",false,true,false,true,true,true,false);
            timeList.add(banana);
            Time orange = new Time("Orange",true,true,true,true,true,true,false);
            timeList.add(orange);
            Time watermelon = new Time("Watermelon",true,true,true,true,true,true,false);
            timeList.add(watermelon);
            Time pear = new Time("Pear",true,true,true,true,true,true,false);
            timeList.add(pear);
            Time grape = new Time("Grape",true,true,true,true,true,true,false);
            timeList.add(grape);
            Time pineapple = new Time("Pineapple",true,true,true,true,true,true,false);
            timeList.add(pineapple);
            Time strawberry = new Time("Strawberry",true,true,true,true,true,true,false);
            timeList.add(strawberry);
            Time cherry = new Time("Cherry",true,true,true,true,true,true,false);
            timeList.add(cherry);
            timeList.remove(apple);
            //Time mango = new Time("Mango", R.drawable.mango_pic);
            //timeList.add(mango);

    }

    }
