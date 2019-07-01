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

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        }else {
            scanSongs("/storage/emulated/0/");
        }


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

    private void scanSongs(String path){
        Connector.getDatabase();
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files != null){
            for (int i = 0; i < files.length; i++){
                String name = files[i].getName();
                if (files[i].isDirectory()) {
                    scanSongs(files[i].getAbsolutePath());
                }else {
                    if (name.endsWith("flac") || name.endsWith("mp3") || name.endsWith("ape")){
                        if (name.matches("(\\w|\\s)+-(\\w|\\s)+.(\\w)+")){
                            SongDao songDao = new SongDao();
                            String[] songName = name.split("\\.");
                            String[] songInfo = songName[0].split("-");
                            songDao.addSong(songInfo[1], songInfo[0], files[i].getAbsolutePath());
                            Log.d(TAG, "scanSongs: " + name);
                        }
                    }

                }
            }
        }
    }

}
