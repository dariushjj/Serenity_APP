package com.serenity.serenityapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.serenityapp.R;
import com.serenity.view.guide.GuideActivity;
import com.example.util.PreferenceUtil;
import com.serenity.view.widget.CircleProgressBar;

public class MainActivity extends AppCompatActivity {

    private boolean isGuided = false;
    private CircleProgressBar circleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_home);
        /*
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        Log.d("MainActivity", "onCreate execute");

        isGuided = PreferenceUtil.getBooleanValue(this, PreferenceUtil.GUIDE, "guide");
        isGuided = true;
        if(!isGuided){
            startActivity(new Intent(MainActivity.this, GuideActivity.class));
            PreferenceUtil.setBooleanPair(this, PreferenceUtil.GUIDE, true, "guide");
        }*/
    }
}
