package com.serenity.view.home;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.serenityapp.R;
import com.google.android.material.navigation.NavigationView;
import com.serenity.dao.SongDao;
import com.serenity.model.Song;
import com.serenity.view.Sleep.SleepActivity;
import com.serenity.view.alarmclock.SetAlarmClockActivity;
import com.serenity.view.guide.GuideActivity;
import com.serenity.view.playlist.PlayListActivity;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.io.File;
import java.util.ArrayList;

import static com.example.util.ConstantUtil.HOME;

public class HomeActivity extends AppCompatActivity{
    private static final String TAG = "HomeActivity";

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.home_drawer_layout);
        final NavigationView navigationView = findViewById(R.id.home_navigation_view);
        Button menuBtn = findViewById(R.id.title_menu_button);
        TextView textView = findViewById(R.id.title_menu_text);
        textView.setText(HOME);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_music:
                        Log.d(TAG, "onNavigationItemSelected: ");
                        startActivity(new Intent(HomeActivity.this, PlayListActivity.class));
                        break;
                    case R.id.menu_timer:
                        startActivity(new Intent(HomeActivity.this, SetAlarmClockActivity.class));
                        break;
                    case R.id.menu_sleep:
                        startActivity(new Intent(HomeActivity.this, SleepActivity.class));
                        break;
                    case R.id.menu_others:
                        break;
                    case R.id.menu_settings:
                        break;
                    case R.id.menu_scan:
                        ArrayList<Song> songs = new SongDao().getSongs();
                        if (songs.size() == 0){
                            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    != PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(HomeActivity.this,new String[] {
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                }, 1);
                            }else {
                                scanSongs("/storage/emulated/0/");
                                Toast.makeText(HomeActivity.this,"Scan finished",Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(HomeActivity.this, "Scaning....Please wait.", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(HomeActivity.this,"scan finished",Toast.LENGTH_LONG).show();
                        }else {
                            new AlertDialog.Builder(HomeActivity.this)
                                    .setTitle("Watch!")
                                    .setMessage("You have scanned it.Will you scan it again?")
                                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            new SongDao().deleteSongs();
                                            scanSongs("/storage/emulated/0/");
                                        }
                                    }).setNegativeButton("no",null)
                                    .show();
                        }
                        break;
                    default:

                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        findViewById(R.id.music_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PlayListActivity.class));
            }
        });
        findViewById(R.id.timer_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SetAlarmClockActivity.class));
            }
        });
    }

    private void scanSongs(final String path)
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
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
        });
        thread.start();
    }

}
