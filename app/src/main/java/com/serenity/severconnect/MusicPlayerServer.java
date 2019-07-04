package com.serenity.severconnect;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

public class MusicPlayerServer extends Service {
    private static final String TAG = "MusicPlayerServer";
    private MyBinder myBinder = new MyBinder();
    public MediaPlayer mediaPlayer = new MediaPlayer();
    public String uri = "";
    public boolean isLocal = true;
    public ArrayList<String> lyricList = new ArrayList<>();
    public ArrayList<String> timeList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        uri = intent.getStringExtra("uri");
        initMediaPlayer(uri, true);

        Log.d(TAG, "onStartCommand " + uri);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }

    public MusicPlayerServer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (!(intent.getStringExtra("uri") == null ||
                intent.getStringExtra("uri").equals(""))){
            uri = intent.getStringExtra("uri");
            isLocal = intent.getBooleanExtra("isLocal",true);
            initMediaPlayer(uri, isLocal);
            Log.d(TAG, "onBind: " + uri);
        }
        return myBinder;
    }

    public class MyBinder extends Binder{
        public void playMusic(){
            if (!mediaPlayer.isPlaying()){
                Log.d(TAG, "playMusic: " + uri);
                mediaPlayer.start();
                Intent musicIntent = new Intent("com.serenity.severconnect.MUSIC_PLAY");
                musicIntent.putExtra("isPlaying", mediaPlayer.isPlaying());
                sendBroadcast(musicIntent);
            }
        }

        public boolean isPlaying(){
            return mediaPlayer.isPlaying();
        }

        public void pauseMusic(){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                Intent musicIntent = new Intent("com.serenity.severconnect.MUSIC_PLAY");
                musicIntent.putExtra("isPlaying", mediaPlayer.isPlaying());
                sendBroadcast(musicIntent);
            }
        }

        public void closeMedia(){
            if (mediaPlayer != null){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }

        public void nextMusic(){
            if (mediaPlayer != null){
                initMediaPlayer(uri, isLocal);
                playMusic();
            }
        }

        public void previousMusic(){
            if (mediaPlayer != null){
                initMediaPlayer(uri, isLocal);
                playMusic();
            }
        }

        //获取时长
        public int getProgress(){
            return mediaPlayer.getDuration();
        }

        //获取当前播放位置
        public int getPlayPosition() {
            return mediaPlayer.getCurrentPosition();
        }

        //跳转到播放位置
        public void seekToPosition(int msec) {
            mediaPlayer.seekTo(msec);
        }

    }

    private void initMediaPlayer(String uri, boolean isLocal){
        try {
            Log.d(TAG, "initMediaPlayer: uri: " + uri);
            mediaPlayer.stop();
            mediaPlayer.reset();
            if (isLocal){
                mediaPlayer.setDataSource(uri);
            }else {
                mediaPlayer.setDataSource(this, Uri.parse(uri));
            }
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
