package com.serenity.view.alarmclock;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.android.serenityapp.R;

public class BellRingingActivity extends AppCompatActivity
{
    private Thread thread;
    private MediaPlayer player;
    private Vibrator vib;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_bell_ringing);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.hide();
        }
        Button stopButton = (Button)findViewById(R.id.stopringbell);
        stopButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               finish();
            }
        });
        Button remindlaterButton = (Button)findViewById(R.id.remindlater);
        remindlaterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CountDownTimer timer = new CountDownTimer(10*1000,1000)
                {
                    @Override
                    public void onTick(long l)
                    {
                        ;
                    }

                    @Override
                    public void onFinish()
                    {
                        Intent intent = new Intent(getApplicationContext(),BellRingingActivity.class);
                        startActivity(intent);
                    }
                }.start();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                finish();
            }
        });
        vib = (Vibrator)this.getSystemService(Service.VIBRATOR_SERVICE);
        virabte(vib,this,new long[]{0,1000,1000,1000},true);
        play();
    }



    private void stop()
    {
        finish();
    }
    private void play()
    {
        thread =  new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                player = new MediaPlayer();
                try
                {
                    player.setDataSource(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()+"/test.flac");
                    player.prepare();
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                player.start();
            }
        });
        thread.start();
    }

    private void virabte(Vibrator vib, final Activity activity, long [] time, boolean isRepeat)
    {
        vib.vibrate(time,isRepeat ? 1:-1);
    }

    @Override
    public  void onDestroy()
    {
        super.onDestroy();
        vib.cancel();
        player.stop();
    }

}
