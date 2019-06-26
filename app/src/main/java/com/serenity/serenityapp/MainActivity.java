package com.serenity.serenityapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.serenityapp.R;
import com.google.android.material.navigation.NavigationView;
import com.serenity.view.guide.GuideActivity;
import com.example.util.PreferenceUtil;
import com.serenity.view.widget.CircleProgressBar;

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
