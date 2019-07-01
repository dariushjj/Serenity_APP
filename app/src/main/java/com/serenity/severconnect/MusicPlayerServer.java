package com.serenity.severconnect;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
/*
从数据库中获取音乐播放uri
 */
public class MusicPlayerServer extends Service {
    private static final String TAG = "MusicPlayerServer";
    private MyBinder myBinder = new MyBinder();
    public MediaPlayer mediaPlayer = new MediaPlayer();
    public String uri = "/storage/emulated/0/netease/cloudmusic/Music/泰勒/Taylor Swift - Blank Space.flac";
    public boolean isLocal = true;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public MusicPlayerServer() {
        initMediaPlayer(uri, isLocal);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class MyBinder extends Binder{
        public void playMusic(){
            if (!mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
        }

        public void pauseMusic(){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        }

        public void resetMusic(){
            if (!mediaPlayer.isPlaying()){
                mediaPlayer.reset();
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
                mediaPlayer.reset();
                initMediaPlayer(uri, isLocal);
                playMusic();
            }
        }

        public void previousMusic(){
            if (mediaPlayer != null){
                mediaPlayer.reset();
                initMediaPlayer(uri, isLocal);
                playMusic();
            }
        }

        public int getProgress(){
            return mediaPlayer.getDuration();
        }

        public int getPlayPosition() {
            return mediaPlayer.getCurrentPosition();
        }

        public void seekToPosition(int msec) {
            mediaPlayer.seekTo(msec);
        }

    }

    private void initMediaPlayer(String uri, boolean isLocal){
        try {
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
