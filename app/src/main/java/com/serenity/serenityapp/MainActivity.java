package com.serenity.serenityapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.android.serenityapp.R;
import com.serenity.dao.SongDao;
import com.serenity.model.Song;
import com.serenity.severconnect.MusicServerConnect;
import com.serenity.view.alarmclock.ServiceofClock;
import com.serenity.view.guide.GuideActivity;
import com.example.util.PreferenceUtil;
import com.serenity.view.play.PlayActivity;
import com.serenity.view.sign.SignInActivity;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.util.ConstantUtil.LYRIC_LIST;
import static com.example.util.ConstantUtil.TIME_LIST;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private boolean isGuided = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // add front service to mainactivity
        //Intent intent_s = new Intent(MainActivity.this,ServiceofClock.class);
        //startService(intent_s);
//        LitePal.deleteAll(Song.class);
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
            Intent intent = new Intent(MainActivity.this, GuideActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        }

    }

}
