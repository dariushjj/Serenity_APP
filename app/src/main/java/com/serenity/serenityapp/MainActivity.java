package com.serenity.serenityapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.serenityapp.R;
import com.serenity.view.guide.GuideActivity;
import com.example.util.PreferenceUtil;
import com.serenity.view.play.PlayActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.util.ConstantUtil.LYRIC_LIST;
import static com.example.util.ConstantUtil.TIME_LIST;


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
            Intent intent = new Intent(MainActivity.this, GuideActivity.class);

            /*
            ArrayList<String> l = new ArrayList<>();
            l.add("sao");
            l.add("zhu");
            l.add("tank");
            l.add("ha");
            l.add("hia");
            l.add("sao!");
            l.add("zhu!");
            l.add("tank!");
            l.add("ha!");
            l.add("hia!");
            intent.putStringArrayListExtra(LYRIC_LIST, l);
            intent.putStringArrayListExtra(TIME_LIST, l);*/

            startActivity(intent);
            PreferenceUtil.setBooleanPair(this, PreferenceUtil.GUIDE, true, "guide");
        }
    }
}
