package com.serenity.view.guide;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.serenityapp.R;
import com.example.util.PreferenceUtil;
import com.serenity.dao.SongDao;
import com.serenity.serenityapp.MainActivity;
import com.serenity.view.home.HomeActivity;
import com.serenity.view.sign.SignInActivity;

import org.litepal.tablemanager.Connector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class GuideActivity extends AppCompatActivity {
    private static final String TAG = "GuideActivity";
    private ViewPager viewPager;
    private List<View> viewList;
    private PagerAdapter pagerAdapter;
    private CircleIndicator circleIndicator;
    private boolean isGuided = false;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        isGuided = PreferenceUtil.getBooleanValue(this, PreferenceUtil.GUIDE, "guide");

        //第一次登录
//        isGuided = false;
        if(!isGuided){

//            欢迎界面
            PreferenceUtil.setBooleanPair(this, PreferenceUtil.GUIDE, true, "guide");

        }else {

            Intent intent = new Intent(GuideActivity.this, SignInActivity.class);
            startActivity(intent);
        }


        viewPager = findViewById(R.id.guide_viewpager);
        circleIndicator = findViewById(R.id.indicator);
        this.initViewList();
        pagerAdapter = new PagerAdapter() {
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public int getCount() {
                return viewList.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };
        viewPager.setAdapter(pagerAdapter);
        circleIndicator.setViewPager(viewPager);
     }

    private void initViewList() {
        this.viewList = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        this.viewList.add(inflater.inflate(R.layout.guide_introduction, null));
        this.viewList.add(inflater.inflate(R.layout.guide_music, null));
        this.viewList.add(inflater.inflate(R.layout.guide_timer, null));
        this.viewList.add(inflater.inflate(R.layout.guide_sleep, null));
        this.viewList.add(inflater.inflate(R.layout.guide_others, null));
    }

    public void goSignIn(View view){
        startActivity(new Intent(GuideActivity.this, SignInActivity.class));
        finish();
    }



}
