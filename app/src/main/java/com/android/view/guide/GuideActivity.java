package com.android.view.guide;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.android.serenityapp.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> viewList;
    private GuidePagerAdapter guidePagerAdapter;
    private CircleIndicator circleIndicator;
    private View introductionView, musicView, timerView, othersView;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        viewPager = findViewById(R.id.guide_viewpager);
        circleIndicator = findViewById(R.id.indicator);
        this.initViewList();
        guidePagerAdapter = new GuidePagerAdapter(this.viewList);
        viewPager.setAdapter(guidePagerAdapter);
        circleIndicator.setViewPager(viewPager);
     }

    private void initViewList() {
        this.viewList = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        this.introductionView = inflater.inflate(R.layout.guide_introduction, null);
        this.musicView = inflater.inflate(R.layout.guide_music, null);
        this.timerView = inflater.inflate(R.layout.guide_timer, null);
        this.othersView = inflater.inflate(R.layout.guide_others, null);
        this.viewList.add(this.introductionView);
        this.viewList.add(this.musicView);
        this.viewList.add(this.timerView);
        this.viewList.add(this.othersView);
    }
}
