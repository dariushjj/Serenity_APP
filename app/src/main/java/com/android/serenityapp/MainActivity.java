package com.android.serenityapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.view.guide.GuideActivity;
import com.example.util.PreferenceUtil;

public class MainActivity extends AppCompatActivity {

    private boolean isGuided = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        Log.d("MainActivity", "onCreate execute");

        isGuided = PreferenceUtil.getBooleanValue(this, PreferenceUtil.GUIDE, "guide");
        isGuided = false;
        if(!isGuided){
            startActivity(new Intent(MainActivity.this, GuideActivity.class));
            PreferenceUtil.setBooleanPair(this, PreferenceUtil.GUIDE, true, "guide");
        }
    }
}
