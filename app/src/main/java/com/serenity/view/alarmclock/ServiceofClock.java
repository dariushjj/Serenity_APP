package com.serenity.view.alarmclock;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;

import com.android.serenityapp.R;
import com.serenity.serenityapp.MainActivity;

public class ServiceofClock extends Service
{
    @Override
    public void onCreate()
    {
        super.onCreate();
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent ,int flags,int startId)
    {
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        Intent nfIntent = new Intent(this, AlarmClockActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(this,0,nfIntent,0))
                .setContentTitle("sentitry")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("running background");
        Notification notification = builder.build();
        startForeground(110, notification);
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onDestroy()
    {
        stopForeground(true);
        super.onDestroy();
    }
}
