package com.serenity.view.Sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.serenityapp.R;

import static com.android.serenityapp.R.*;

public class SleepActivity extends AppCompatActivity {
    private Boolean zhankaiclick = false;
    private Boolean dreamclick1 = false;
    private Boolean dreamclick2 = false;
    private Boolean dreamclick3 = false;
    private Boolean dreamclick4 = false;
    private Boolean dreamclick5 = false;
    private Boolean dreamclick6 = false;
    private Boolean stateclick1 = false;
    private Boolean stateclick2 = false;
    private Boolean stateclick3 = false;
    private Boolean stateclick4 = false;
    private Boolean stateclick5 = false;
    private Boolean stateclick6 = false;
    private Boolean recordclick1 = true;
    private Boolean recordclick2 = true;
    private Boolean recordclick3 = true;
    private Boolean recordclick4 = true;
    private Boolean deleteclick1 = false;
    private Boolean deleteclick2 = false;
    private Boolean deleteclick3 = false;
    private Boolean deleteclick4 = false;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_sleep);
        final Button zhankai = (Button) findViewById(id.zhankai);
        final Button dream1  = (Button) findViewById(id.dream1);
        final Button dream2  = (Button) findViewById(id.dream2);
        final Button dream3  = (Button) findViewById(id.dream3);
        final Button dream4  = (Button) findViewById(id.dream4);
        final Button dream5  = (Button) findViewById(id.dream5);
        final Button dream6  = (Button) findViewById(id.dream6);
        final Button state1  = (Button) findViewById(id.state1);
        final Button state2  = (Button) findViewById(id.state2);
        final Button state3  = (Button) findViewById(id.state3);
        final Button state4  = (Button) findViewById(id.state4);
        final Button state5  = (Button) findViewById(id.state5);
        final Button state6  = (Button) findViewById(id.state6);
        final Button record1 = (Button) findViewById(id.play1);
        final Button record2 = (Button) findViewById(id.play2);
        final Button record3 = (Button) findViewById(id.play3);
        final Button record4 = (Button) findViewById(id.play4);
        final Button back = (Button) findViewById(id.sleepback);
        zhankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(zhankaiclick){//如果是myself按钮，则设置一种背景
                    zhankai.setBackgroundResource(mipmap.zhankai);
                    zhankaiclick=false;
                }else{//如果不是myself按钮，则设置回来。
                    zhankaiclick=true;
                    zhankai.setBackgroundResource(mipmap.shouhui);
                }
            }
        });

        dream1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick1){
                    dream1.setBackgroundResource(mipmap.meimeng1);
                    dreamclick1=false;
                }else{
                    dreamclick1=true;
                    dream1.setBackgroundResource(mipmap.meimeng2);
                    dream2.setBackgroundResource(mipmap.emeng1);
                    dream3.setBackgroundResource(mipmap.wumeng1);
                    dream4.setBackgroundResource(mipmap.chunmeng1);
                    dream5.setBackgroundResource(mipmap.pingdan1);
                    dream6.setBackgroundResource(mipmap.jingkong1);
                }
            }
        });

        dream2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick2){//如果是myself按钮，则设置一种背景
                    dream2.setBackgroundResource(mipmap.emeng1);
                    dreamclick2=false;
                }else{//如果不是myself按钮，则设置回来。
                    dreamclick2=true;
                    dream1.setBackgroundResource(mipmap.meimeng1);
                    dream2.setBackgroundResource(mipmap.emeng2);
                    dream3.setBackgroundResource(mipmap.wumeng1);
                    dream4.setBackgroundResource(mipmap.chunmeng1);
                    dream5.setBackgroundResource(mipmap.pingdan1);
                    dream6.setBackgroundResource(mipmap.jingkong1);
                }
            }
        });

        dream3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick3){//如果是myself按钮，则设置一种背景
                    dream3.setBackgroundResource(mipmap.wumeng1);
                    dreamclick3=false;
                }else{//如果不是myself按钮，则设置回来。
                    dreamclick3=true;
                    dream1.setBackgroundResource(mipmap.meimeng1);
                    dream2.setBackgroundResource(mipmap.emeng1);
                    dream3.setBackgroundResource(mipmap.wumeng2);
                    dream4.setBackgroundResource(mipmap.chunmeng1);
                    dream5.setBackgroundResource(mipmap.pingdan1);
                    dream6.setBackgroundResource(mipmap.jingkong1);
                }
            }
        });

        dream4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick4){//如果是myself按钮，则设置一种背景
                    dream4.setBackgroundResource(mipmap.chunmeng1);
                    dreamclick4=false;
                }else{//如果不是myself按钮，则设置回来。
                    dreamclick4=true;
                    dream1.setBackgroundResource(mipmap.meimeng1);
                    dream2.setBackgroundResource(mipmap.emeng1);
                    dream3.setBackgroundResource(mipmap.wumeng1);
                    dream4.setBackgroundResource(mipmap.chunmeng2);
                    dream5.setBackgroundResource(mipmap.pingdan1);
                    dream6.setBackgroundResource(mipmap.jingkong1);
                }
            }
        });

        dream5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick5){//如果是myself按钮，则设置一种背景
                    dream5.setBackgroundResource(mipmap.pingdan1);
                    dreamclick5=false;
                }else{//如果不是myself按钮，则设置回来。
                    dreamclick5=true;
                    dream1.setBackgroundResource(mipmap.meimeng1);
                    dream2.setBackgroundResource(mipmap.emeng1);
                    dream3.setBackgroundResource(mipmap.wumeng1);
                    dream4.setBackgroundResource(mipmap.chunmeng1);
                    dream5.setBackgroundResource(mipmap.pingdan2);
                    dream6.setBackgroundResource(mipmap.jingkong1);
                }
            }
        });

        dream6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick6){//如果是myself按钮，则设置一种背景
                    dream6.setBackgroundResource(mipmap.jingkong1);
                    dreamclick6=false;
                }else{//如果不是myself按钮，则设置回来。
                    dreamclick6=true;
                    dream1.setBackgroundResource(mipmap.meimeng1);
                    dream2.setBackgroundResource(mipmap.emeng1);
                    dream3.setBackgroundResource(mipmap.wumeng1);
                    dream4.setBackgroundResource(mipmap.chunmeng1);
                    dream5.setBackgroundResource(mipmap.pingdan1);
                    dream6.setBackgroundResource(mipmap.jingkong2);
                }
            }
        });

        state1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick1){
                    state1.setBackgroundResource(mipmap.meimeng1);
                    stateclick1=false;
                }else{
                    stateclick1=true;
                    state1.setBackgroundResource(mipmap.meimeng2);
                    state2.setBackgroundResource(mipmap.emeng1);
                    state3.setBackgroundResource(mipmap.wumeng1);
                    state4.setBackgroundResource(mipmap.chunmeng1);
                    state5.setBackgroundResource(mipmap.pingdan1);
                    state6.setBackgroundResource(mipmap.jingkong1);
                }
            }
        });

        state2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick2){//如果是myself按钮，则设置一种背景
                    state2.setBackgroundResource(mipmap.emeng1);
                    stateclick2=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick2=true;
                    state1.setBackgroundResource(mipmap.meimeng1);
                    state2.setBackgroundResource(mipmap.emeng2);
                    state3.setBackgroundResource(mipmap.wumeng1);
                    state4.setBackgroundResource(mipmap.chunmeng1);
                    state5.setBackgroundResource(mipmap.pingdan1);
                    state6.setBackgroundResource(mipmap.jingkong1);
                }
            }
        });

        state3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick3){//如果是myself按钮，则设置一种背景
                    state3.setBackgroundResource(mipmap.wumeng1);
                    stateclick3=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick3=true;
                    state1.setBackgroundResource(mipmap.meimeng1);
                    state2.setBackgroundResource(mipmap.emeng1);
                    state3.setBackgroundResource(mipmap.wumeng2);
                    state4.setBackgroundResource(mipmap.chunmeng1);
                    state5.setBackgroundResource(mipmap.pingdan1);
                    state6.setBackgroundResource(mipmap.jingkong1);
                }
            }
        });

        state4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick4){//如果是myself按钮，则设置一种背景
                    state4.setBackgroundResource(mipmap.chunmeng1);
                    stateclick4=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick4=true;
                    state1.setBackgroundResource(mipmap.meimeng1);
                    state2.setBackgroundResource(mipmap.emeng1);
                    state3.setBackgroundResource(mipmap.wumeng1);
                    state4.setBackgroundResource(mipmap.chunmeng2);
                    state5.setBackgroundResource(mipmap.pingdan1);
                    state6.setBackgroundResource(mipmap.jingkong1);
                }
            }
        });

        state5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick5){//如果是myself按钮，则设置一种背景
                    state5.setBackgroundResource(mipmap.pingdan1);
                    stateclick5=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick5=true;
                    state1.setBackgroundResource(mipmap.meimeng1);
                    state2.setBackgroundResource(mipmap.emeng1);
                    state3.setBackgroundResource(mipmap.wumeng1);
                    state4.setBackgroundResource(mipmap.chunmeng1);
                    state5.setBackgroundResource(mipmap.pingdan2);
                    state6.setBackgroundResource(mipmap.jingkong1);
                }
            }
        });

        state6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick6){//如果是myself按钮，则设置一种背景
                    state6.setBackgroundResource(mipmap.jingkong1);
                    stateclick6=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick6=true;
                    state1.setBackgroundResource(mipmap.meimeng1);
                    state2.setBackgroundResource(mipmap.emeng1);
                    state3.setBackgroundResource(mipmap.wumeng1);
                    state4.setBackgroundResource(mipmap.chunmeng1);
                    state5.setBackgroundResource(mipmap.pingdan1);
                    state6.setBackgroundResource(mipmap.jingkong2);
                }
            }
        });
        state6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick6){//如果是myself按钮，则设置一种背景
                    state6.setBackgroundResource(mipmap.jingkong1);
                    stateclick6=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick6=true;
                    state1.setBackgroundResource(mipmap.meimeng1);
                    state2.setBackgroundResource(mipmap.emeng1);
                    state3.setBackgroundResource(mipmap.wumeng1);
                    state4.setBackgroundResource(mipmap.chunmeng1);
                    state5.setBackgroundResource(mipmap.pingdan1);
                    state6.setBackgroundResource(mipmap.jingkong2);
                }
            }
        });
        record1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordclick1)
                {//如果是myself按钮，则设置一种背景
                    player = new MediaPlayer();
                    Toast.makeText(SleepActivity.this,"start playing record",Toast.LENGTH_LONG).show();
                    Thread thread = new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                player.setDataSource("/storage/emulated/0/DCIM/test.amr");
                                player.prepare();
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            player.start();
                        }
                    });
                    thread.start();
                    record1.setBackgroundResource(R.mipmap.play1);
                    recordclick1=false;

                }else{//如果不是myself按钮，则设置回来。
                    if(player != null)
                    {
                        player.stop();
                    }
                    record1.setBackgroundResource(R.mipmap.play);
                    recordclick1=true;

                }
            }
        });
        record2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordclick2)
                {
                    player = new MediaPlayer();
                    Toast.makeText(SleepActivity.this,"start playing record",Toast.LENGTH_LONG).show();
                    Thread thread = new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                player.setDataSource("/storage/emulated/0/DCIM/test.flac");
                                player.prepare();
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            player.start();
                        }
                    });
                    thread.start();
                    record2.setBackgroundResource(R.mipmap.play1);
                    recordclick2=false;

                }
                else {
                    record2.setBackgroundResource(R.mipmap.play);
                    recordclick2 = true;
                    if(player != null)
                    {
                        player.stop();
                    }
                }
            }
        });
        record3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordclick3){//如果是myself按钮，则设置一种背景
                    player = new MediaPlayer();
                    Toast.makeText(SleepActivity.this,"start playing record",Toast.LENGTH_LONG).show();
                    Thread thread = new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                player.setDataSource("/storage/emulated/0/DCIM/man.flac");
                                player.prepare();
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            player.start();
                        }
                    });
                    thread.start();
                    recordclick3=false;
                    record3.setBackgroundResource(R.mipmap.play1);

                }else{//如果不是myself按钮，则设置回来。
                     if(player != null)
                    {
                        player.stop();
                    }
                    recordclick3=true;
                    record3.setBackgroundResource(R.mipmap.play);
                }
            }
        });
        record4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordclick4){//如果是myself按钮，则设置一种背景
                    player = new MediaPlayer();
                    Toast.makeText(SleepActivity.this,"start playing record",Toast.LENGTH_LONG).show();
                    Thread thread = new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                player.setDataSource("/storage/emulated/0/DCIM/bandao.flac");
                                player.prepare();
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            player.start();
                        }
                    });
                    thread.start();
                    recordclick4=false;
                    record4.setBackgroundResource(R.mipmap.play1);

                }else{//如果不是myself按钮，则设置回来。
                    if(player != null)
                    {
                        player.stop();
                    }
                    recordclick4=true;
                    record4.setBackgroundResource(R.mipmap.play);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
