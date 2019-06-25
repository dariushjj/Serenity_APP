package com.serenity.alarmclock;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.serenityapp.R;

public class alarmclock extends AppCompatActivity {
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
        setContentView(R.layout.setclock);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        Button backButton = (Button)findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(alarmclock.this,main.class);
                startActivity(intent);
            }
        });
        Button decisionButton = (Button)findViewById(R.id.decisionbutton);
        decisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(alarmclock.this,main.class);
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
                    saturdayButton.setActivated(b_sub_square6);
                } else {
                    b_sub_square6 = false;
                    saturdayButton.setActivated(b_sub_square6);
                }
            }
        });
    }
}
