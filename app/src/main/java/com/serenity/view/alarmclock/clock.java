package com.serenity.view.alarmclock;

import android.app.Activity;
import android.app.Service;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.serenityapp.R;
//TODO
// this is the clock when time is up
public class clock extends AppCompatActivity
{
    private Thread thread;
    private MediaPlayer player;
    private Vibrator vib;
    protected  void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.clock_test);
        TextView view = (TextView)findViewById(R.id.show_text);
        view.setText("time is up");
        vib = (Vibrator)this.getSystemService(Service.VIBRATOR_SERVICE);
        virabte(vib,this,new long[]{0,1000,1000,1000},true);
        play();
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
