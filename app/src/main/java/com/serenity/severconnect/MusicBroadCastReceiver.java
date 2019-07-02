package com.serenity.severconnect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MusicBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean musicIsPlaying = intent.getBooleanExtra("isPlaying", false);
    }

}
