package com.serenity.view.alarmclock;

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
import android.widget.TextView;


import com.android.serenityapp.R;
//TODO
// this is the clock when time is up
public class clock extends Activity
{
    private Thread thread;
    private MediaPlayer player;
    private Vibrator vib;
    protected  void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.clock_test);
        TextView view = (TextView)findViewById(R.id.show_text);
        Button stop = (Button)findViewById(R.id.stop);
        Button after = (Button)findViewById(R.id.clock_after);
        stop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                stop();
            }
        });
        after.setOnClickListener(new View.OnClickListener()
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
                        Intent intent = new Intent(getApplicationContext(),clock.class);
                        startActivity(intent);
                    }
                }.start();
                stop();
            }
        });
        view.setText("time is up");
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
