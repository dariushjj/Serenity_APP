package com.serenity.serenityapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.serenityapp.R;
import com.serenity.severconnect.MusicServerConnect;
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
    private static final String TAG = "MainActivity";
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
            Intent intent = new Intent(MainActivity.this, PlayActivity.class);
            ArrayList<String> l = new ArrayList<>();
            MusicServerConnect lrcConnect = new MusicServerConnect();
            lrcConnect.init(null,"573747359", MusicServerConnect.LRC);
            while (lrcConnect.usefulInfo == null || lrcConnect.usefulInfo.equals("")){ }
            l = (ArrayList<String>)lrcConnect.lrcSentence;

            Log.d(TAG, "run: " + l);
            intent.putStringArrayListExtra(LYRIC_LIST, l);
            intent.putStringArrayListExtra(TIME_LIST, l);
            startActivity(intent);

//            欢迎界面
            PreferenceUtil.setBooleanPair(this, PreferenceUtil.GUIDE, true, "guide");
        }
    }
}
