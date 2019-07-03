package com.serenity.view.Sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.serenityapp.R;

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
    private Boolean recordclick1 = false;
    private Boolean recordclick2 = false;
    private Boolean recordclick3 = false;
    private Boolean recordclick4 = false;
    private Boolean deleteclick1 = false;
    private Boolean deleteclick2 = false;
    private Boolean deleteclick3 = false;
    private Boolean deleteclick4 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        final Button zhankai = (Button) findViewById(R.id.zhankai);
        final Button dream1  = (Button) findViewById(R.id.dream1);
        final Button dream2  = (Button) findViewById(R.id.dream2);
        final Button dream3  = (Button) findViewById(R.id.dream3);
        final Button dream4  = (Button) findViewById(R.id.dream4);
        final Button dream5  = (Button) findViewById(R.id.dream5);
        final Button dream6  = (Button) findViewById(R.id.dream6);
        final Button state1  = (Button) findViewById(R.id.state1);
        final Button state2  = (Button) findViewById(R.id.state2);
        final Button state3  = (Button) findViewById(R.id.state3);
        final Button state4  = (Button) findViewById(R.id.state4);
        final Button state5  = (Button) findViewById(R.id.state5);
        final Button state6  = (Button) findViewById(R.id.state6);
        final Button record1 = (Button) findViewById(R.id.play1);
        final Button record2 = (Button) findViewById(R.id.play2);
        final Button record3 = (Button) findViewById(R.id.play3);
        final Button record4 = (Button) findViewById(R.id.play4);
        zhankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(zhankaiclick){//如果是myself按钮，则设置一种背景
                    zhankai.setBackgroundResource(R.mipmap.zhankai);
                    zhankaiclick=false;
                }else{//如果不是myself按钮，则设置回来。
                    zhankaiclick=true;
                    zhankai.setBackgroundResource(R.mipmap.shouhui);
                }
            }
        });

        dream1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick1){
                    dream1.setBackgroundResource(R.mipmap.meimeng1);
                    dreamclick1=false;
                }else{
                    dreamclick1=true;
                    dream1.setBackgroundResource(R.mipmap.meimeng2);
                    dream2.setBackgroundResource(R.mipmap.emeng1);
                    dream3.setBackgroundResource(R.mipmap.wumeng1);
                    dream4.setBackgroundResource(R.mipmap.chunmeng1);
                    dream5.setBackgroundResource(R.mipmap.pingdan1);
                    dream6.setBackgroundResource(R.mipmap.jingkong1);
                }
            }
        });

        dream2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick2){//如果是myself按钮，则设置一种背景
                    dream2.setBackgroundResource(R.mipmap.emeng1);
                    dreamclick2=false;
                }else{//如果不是myself按钮，则设置回来。
                    dreamclick2=true;
                    dream1.setBackgroundResource(R.mipmap.meimeng1);
                    dream2.setBackgroundResource(R.mipmap.emeng2);
                    dream3.setBackgroundResource(R.mipmap.wumeng1);
                    dream4.setBackgroundResource(R.mipmap.chunmeng1);
                    dream5.setBackgroundResource(R.mipmap.pingdan1);
                    dream6.setBackgroundResource(R.mipmap.jingkong1);
                }
            }
        });

        dream3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick3){//如果是myself按钮，则设置一种背景
                    dream3.setBackgroundResource(R.mipmap.wumeng1);
                    dreamclick3=false;
                }else{//如果不是myself按钮，则设置回来。
                    dreamclick3=true;
                    dream1.setBackgroundResource(R.mipmap.meimeng1);
                    dream2.setBackgroundResource(R.mipmap.emeng1);
                    dream3.setBackgroundResource(R.mipmap.wumeng2);
                    dream4.setBackgroundResource(R.mipmap.chunmeng1);
                    dream5.setBackgroundResource(R.mipmap.pingdan1);
                    dream6.setBackgroundResource(R.mipmap.jingkong1);
                }
            }
        });

        dream4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick4){//如果是myself按钮，则设置一种背景
                    dream4.setBackgroundResource(R.mipmap.chunmeng1);
                    dreamclick4=false;
                }else{//如果不是myself按钮，则设置回来。
                    dreamclick4=true;
                    dream1.setBackgroundResource(R.mipmap.meimeng1);
                    dream2.setBackgroundResource(R.mipmap.emeng1);
                    dream3.setBackgroundResource(R.mipmap.wumeng1);
                    dream4.setBackgroundResource(R.mipmap.chunmeng2);
                    dream5.setBackgroundResource(R.mipmap.pingdan1);
                    dream6.setBackgroundResource(R.mipmap.jingkong1);
                }
            }
        });

        dream5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick5){//如果是myself按钮，则设置一种背景
                    dream5.setBackgroundResource(R.mipmap.pingdan1);
                    dreamclick5=false;
                }else{//如果不是myself按钮，则设置回来。
                    dreamclick5=true;
                    dream1.setBackgroundResource(R.mipmap.meimeng1);
                    dream2.setBackgroundResource(R.mipmap.emeng1);
                    dream3.setBackgroundResource(R.mipmap.wumeng1);
                    dream4.setBackgroundResource(R.mipmap.chunmeng1);
                    dream5.setBackgroundResource(R.mipmap.pingdan2);
                    dream6.setBackgroundResource(R.mipmap.jingkong1);
                }
            }
        });

        dream6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dreamclick6){//如果是myself按钮，则设置一种背景
                    dream6.setBackgroundResource(R.mipmap.jingkong1);
                    dreamclick6=false;
                }else{//如果不是myself按钮，则设置回来。
                    dreamclick6=true;
                    dream1.setBackgroundResource(R.mipmap.meimeng1);
                    dream2.setBackgroundResource(R.mipmap.emeng1);
                    dream3.setBackgroundResource(R.mipmap.wumeng1);
                    dream4.setBackgroundResource(R.mipmap.chunmeng1);
                    dream5.setBackgroundResource(R.mipmap.pingdan1);
                    dream6.setBackgroundResource(R.mipmap.jingkong2);
                }
            }
        });

        state1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick1){
                    state1.setBackgroundResource(R.mipmap.meimeng1);
                    stateclick1=false;
                }else{
                    stateclick1=true;
                    state1.setBackgroundResource(R.mipmap.meimeng2);
                    state2.setBackgroundResource(R.mipmap.emeng1);
                    state3.setBackgroundResource(R.mipmap.wumeng1);
                    state4.setBackgroundResource(R.mipmap.chunmeng1);
                    state5.setBackgroundResource(R.mipmap.pingdan1);
                    state6.setBackgroundResource(R.mipmap.jingkong1);
                }
            }
        });

        state2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick2){//如果是myself按钮，则设置一种背景
                    state2.setBackgroundResource(R.mipmap.emeng1);
                    stateclick2=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick2=true;
                    state1.setBackgroundResource(R.mipmap.meimeng1);
                    state2.setBackgroundResource(R.mipmap.emeng2);
                    state3.setBackgroundResource(R.mipmap.wumeng1);
                    state4.setBackgroundResource(R.mipmap.chunmeng1);
                    state5.setBackgroundResource(R.mipmap.pingdan1);
                    state6.setBackgroundResource(R.mipmap.jingkong1);
                }
            }
        });

        state3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick3){//如果是myself按钮，则设置一种背景
                    state3.setBackgroundResource(R.mipmap.wumeng1);
                    stateclick3=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick3=true;
                    state1.setBackgroundResource(R.mipmap.meimeng1);
                    state2.setBackgroundResource(R.mipmap.emeng1);
                    state3.setBackgroundResource(R.mipmap.wumeng2);
                    state4.setBackgroundResource(R.mipmap.chunmeng1);
                    state5.setBackgroundResource(R.mipmap.pingdan1);
                    state6.setBackgroundResource(R.mipmap.jingkong1);
                }
            }
        });

        state4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick4){//如果是myself按钮，则设置一种背景
                    state4.setBackgroundResource(R.mipmap.chunmeng1);
                    stateclick4=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick4=true;
                    state1.setBackgroundResource(R.mipmap.meimeng1);
                    state2.setBackgroundResource(R.mipmap.emeng1);
                    state3.setBackgroundResource(R.mipmap.wumeng1);
                    state4.setBackgroundResource(R.mipmap.chunmeng2);
                    state5.setBackgroundResource(R.mipmap.pingdan1);
                    state6.setBackgroundResource(R.mipmap.jingkong1);
                }
            }
        });

        state5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick5){//如果是myself按钮，则设置一种背景
                    state5.setBackgroundResource(R.mipmap.pingdan1);
                    stateclick5=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick5=true;
                    state1.setBackgroundResource(R.mipmap.meimeng1);
                    state2.setBackgroundResource(R.mipmap.emeng1);
                    state3.setBackgroundResource(R.mipmap.wumeng1);
                    state4.setBackgroundResource(R.mipmap.chunmeng1);
                    state5.setBackgroundResource(R.mipmap.pingdan2);
                    state6.setBackgroundResource(R.mipmap.jingkong1);
                }
            }
        });

        state6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick6){//如果是myself按钮，则设置一种背景
                    state6.setBackgroundResource(R.mipmap.jingkong1);
                    stateclick6=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick6=true;
                    state1.setBackgroundResource(R.mipmap.meimeng1);
                    state2.setBackgroundResource(R.mipmap.emeng1);
                    state3.setBackgroundResource(R.mipmap.wumeng1);
                    state4.setBackgroundResource(R.mipmap.chunmeng1);
                    state5.setBackgroundResource(R.mipmap.pingdan1);
                    state6.setBackgroundResource(R.mipmap.jingkong2);
                }
            }
        });
        state6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stateclick6){//如果是myself按钮，则设置一种背景
                    state6.setBackgroundResource(R.mipmap.jingkong1);
                    stateclick6=false;
                }else{//如果不是myself按钮，则设置回来。
                    stateclick6=true;
                    state1.setBackgroundResource(R.mipmap.meimeng1);
                    state2.setBackgroundResource(R.mipmap.emeng1);
                    state3.setBackgroundResource(R.mipmap.wumeng1);
                    state4.setBackgroundResource(R.mipmap.chunmeng1);
                    state5.setBackgroundResource(R.mipmap.pingdan1);
                    state6.setBackgroundResource(R.mipmap.jingkong2);
                }
            }
        });
        record1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordclick1){//如果是myself按钮，则设置一种背景
                    record1.setBackgroundResource(R.mipmap.play1);
                    recordclick1=false;

                }else{//如果不是myself按钮，则设置回来。
                    recordclick1=true;
                    record1.setBackgroundResource(R.mipmap.play);

                }
            }
        });
        record2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordclick2){//如果是myself按钮，则设置一种背景
                    record2.setBackgroundResource(R.mipmap.play1);
                    recordclick2=false;

                }else{//如果不是myself按钮，则设置回来。
                    recordclick2=true;
                    record2.setBackgroundResource(R.mipmap.play);

                }
            }
        });
        record3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordclick3){//如果是myself按钮，则设置一种背景
                    record3.setBackgroundResource(R.mipmap.play1);
                    recordclick3=false;

                }else{//如果不是myself按钮，则设置回来。
                    recordclick3=true;
                    record3.setBackgroundResource(R.mipmap.play);

                }
            }
        });
        record4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordclick4){//如果是myself按钮，则设置一种背景
                    record4.setBackgroundResource(R.mipmap.play1);
                    recordclick4=false;

                }else{//如果不是myself按钮，则设置回来。
                    recordclick4=true;
                    record4.setBackgroundResource(R.mipmap.play);

                }
            }
        });
    }
}
