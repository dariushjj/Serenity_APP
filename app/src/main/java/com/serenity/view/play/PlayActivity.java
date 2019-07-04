package com.serenity.view.play;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.serenityapp.R;
import com.serenity.model.Song;
import com.serenity.severconnect.MusicPlayerServer;
import com.serenity.severconnect.MusicServerConnect;
import com.serenity.view.alarmclock.Music;
import com.serenity.view.playlist.PlayListActivity;
import com.serenity.view.widget.BackTitleView;
import com.wx.wheelview.widget.WheelView;
import com.wx.wheelview.adapter.SimpleWheelAdapter;
import com.wx.wheelview.common.WheelData;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;
import static com.example.util.ConstantUtil.LYRIC_LIST;
import static com.example.util.ConstantUtil.PLAY_TITLE_TEXT;
import static com.example.util.ConstantUtil.TIME_LIST;
import static com.serenity.view.playlist.PlayListActivity.songList;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "PlayActivity";
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TextView backTitleTextView;
    private CircleIndicator circleIndicator;
    private List<View> viewList;
    private View diskView;
    private View lyricView;
    private WheelView wheelView;
    private TextView diskTitle;
    private TextView diskInfo;
    private CircleImageView diskImage;
    private TextView currentProgress;
    private TextView leftProgress;
    private CircleProgressBar circleProgressBar;
    private Timer timer = new Timer();
    private Button play;
    private String name;
    private String singer;
    private String uri;
    private String id;
    private Bitmap pic;
    private boolean isLocal;
    private int position_;
    private ArrayList<String> lyricList = null;
    private ArrayList<String> timeList = null;
    private MediaPlayer plaayer;
    private boolean isButtonStop = false;
    String current ;
    String left;
//    private MusicPlayerServer.MyBinder myBinder;

//    private ServiceConnection connection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//            myBinder = (MusicPlayerServer.MyBinder) iBinder;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName componentName) {
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        initVariables();
        initViewList();
        getLrcImage();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        singer = intent.getStringExtra("singer");
        uri = intent.getStringExtra("uri");
        isLocal = intent.getBooleanExtra("isLocal", true);
        position_ = intent.getIntExtra("position",1);

        diskTitle.setText(name);
        diskInfo.setText(singer);
        pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(viewList.get(position));
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(viewList.get(position));
                if(position == getCount() - 1){
                    wheelView = findViewById(R.id.play_lyric_wheel_view);
                    wheelView.setWheelAdapter(new SimpleWheelAdapter(lyricView.getContext()));
                    wheelView.setWheelSize(7);
                    wheelView.setLoop(false);
                    wheelView.setSkin(WheelView.Skin.None);
                    wheelView.setWheelData(initLyricData());
                    WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
                    style.selectedTextColor = Color.parseColor("#fffafa");
                    style.textColor = Color.parseColor("#BFD6D7D7");
                    style.selectedTextSize = 20;
                    style.backgroundColor = Color.parseColor("#00000000");
                    wheelView.setStyle(style);
                }
                return viewList.get(position);
            }
        };

        viewPager.setAdapter(pagerAdapter);
        circleIndicator.setViewPager(viewPager);
        play.setOnClickListener(this);

        Intent bindIntent = new Intent(this, MusicPlayerServer.class);
//        if (!isLocal){
//            bindIntent.putExtra("isLocal", isLocal);
//            bindIntent.putExtra("uri", uri);
//        }

//        bindService(bindIntent, connection, BIND_AUTO_CREATE);

        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (PlayListActivity.getplayer().isPlaying())
                {
                    PlayListActivity.getplayer().pause();
                    play.setBackgroundResource(R.drawable.start);
                }
                else
                {
                    PlayListActivity.getplayer().start();
                    play.setBackgroundResource(R.drawable.stop);
                }
            }
        });

        setUiBytime();

    }

    /**
     * 初始化变量
     */
    private void initVariables()
    {
        viewPager = findViewById(R.id.play_view_pager);
        backTitleTextView = findViewById(R.id.title_back_text);
        backTitleTextView.setText(PLAY_TITLE_TEXT);
        circleIndicator = findViewById(R.id.play_indicator);
        play = (Button) findViewById(R.id.play_stop_start_button);
        diskTitle = findViewById(R.id.play_disk_music_title);
        diskInfo = findViewById(R.id.play_disk_music_info);
        wheelView = (WheelView) findViewById(R.id.play_lyric_wheel_view);

        if (PlayListActivity.getplayer().isPlaying())
        {
            play.setBackgroundResource(R.drawable.stop);
        }
        else
        {
            play.setBackgroundResource(R.drawable.start);
        }

        findViewById(R.id.play_previous_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                position_ = (--position_) % PlayListActivity.getSongList().size();
                Song song_ = PlayListActivity.getSongList().get(position_);
                setView(song_);
                final String uri_ = song_.getUri();
                if(PlayListActivity.getplayer().isPlaying())
                {
                    PlayListActivity.getplayer().stop();
                    //stopStartBtn.setBackgroundResource(R.drawable.stop);
                }
                PlayListActivity.setplayer(new MediaPlayer());
                Thread thread_ = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(200);
                            PlayListActivity.getplayer().setDataSource(uri_);
                            PlayListActivity.getplayer().prepare();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        PlayListActivity.getplayer().start();
                    }
                });
                thread_.start();
            }
        });

        findViewById(R.id.play_next_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                position_ = (++position_) % PlayListActivity.getSongList().size();
                Song song_ = PlayListActivity.getSongList().get(position_);
                setView(song_);
                final String uri_ = song_.getUri();
                    if(PlayListActivity.getplayer().isPlaying())
                {
                    PlayListActivity.getplayer().stop();
                    //stopStartBtn.setBackgroundResource(R.drawable.stop);
                }
                    PlayListActivity.setplayer(new MediaPlayer());
                Thread thread_ = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(200);
                            PlayListActivity.getplayer().setDataSource(uri_);
                            PlayListActivity.getplayer().prepare();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        PlayListActivity.getplayer().start();
                    }
                });
                thread_.start();
        }
        });
    }

    private void initViewList(){
        viewList = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        diskView = inflater.inflate(R.layout.play_disk, null);
        circleProgressBar = diskView.findViewById(R.id.play_circle_progress_bar);
        currentProgress = diskView.findViewById(R.id.play_current_progress_text);
        leftProgress = diskView.findViewById(R.id.play_left_progress_text);
        diskImage = diskView.findViewById(R.id.play_cover);
        lyricView = inflater.inflate(R.layout.play_lyric, null);
        viewList.add(diskView);
        viewList.add(lyricView);
    }

    private ArrayList<WheelData> initLyricData(){
        ArrayList<WheelData> list = new ArrayList<>();
        WheelData item;
//        while (lyricList == null || timeList == null){}
        if(lyricList == null || timeList == null || lyricList.size() != timeList.size()){
            item = new WheelData();
            item.setName("pure music.");
            list.add(item);
            return list;
        }
        for(String lyric : lyricList){
            item = new WheelData();
            item.setName(lyric);
            list.add(item);
        }
        return list;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play_stop_start_button:
                if (PlayListActivity.getplayer().isPlaying())
                {
                    PlayListActivity.getplayer().pause();
                    play.setBackgroundResource(R.drawable.start);
                }
                else
                {
                    PlayListActivity.getplayer().start();
                    play.setBackgroundResource(R.drawable.stop);
                }
                break;
            case R.id.play_next_button:
                break;
            case R.id.play_previous_button:
                break;
            default:
                break;
        }
    }

    private void setUiBytime()
    {
        plaayer = PlayListActivity.getplayer();
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                while(true)
                {
                    int currentTime = plaayer.getCurrentPosition() / 1000;
                    int leftTime = plaayer.getDuration() / 1000;
                    current = String.format("%02d:%02d", currentTime / 60, currentTime % 60);
                    left = String.format("%02d:%02d", leftTime / 60, leftTime % 60);
                    //Log.d("current ",String.format("%02d:%02d", currentTime / 60, currentTime % 60));
                    //Log.d("left",String.format("%02d:%02d", leftTime / 60, leftTime % 60));
                    float ratio = (float) plaayer.getCurrentPosition() / plaayer.getDuration();
                    circleProgressBar.setProgress(ratio * 100);

                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    PlayActivity.this.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {

                            currentProgress.setText(current);
                            leftProgress.setText(left);
                        }
                    });
                }

            }
        }).start();

//        Thread  thread = new Thread(new Runnable()
//        {
//            // TODO: 19-7-4
//            @Override
//            public void run()
//            {
//                while (true)
//                {
//                    try
//                    {
//                        Thread.sleep(1000);
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//                    if (plaayer.isPlaying())
//                    {
//                        int currentTime = plaayer.getCurrentPosition() / 1000;
//                        int leftTime = plaayer.getDuration() / 1000;
//                        //Log.d("current ",String.format("%02d:%02d", currentTime / 60, currentTime % 60));
//                        //Log.d("left",String.format("%02d:%02d", leftTime / 60, leftTime % 60));
//                        float ratio = (float) plaayer.getCurrentPosition() / plaayer.getDuration();
//                        circleProgressBar.setProgress(ratio * 100);
//                        currentProgress.setText(String.format("%02d:%02d", currentTime / 60, currentTime % 60));
//                        leftProgress.setText(String.format("%02d:%02d", leftTime / 60, leftTime % 60));
//                    }
//                }
//
//            }
//        });
//        thread.start();
    }
//    @Override
//    protected void onDestroy() {
//        unbindService(connection);
//        super.onDestroy();
//    }

//    private void setUiByTime(){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {

//            }
//         });
//    }

    private void setImage(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                while (pic == null){}
                Log.d(TAG, "run: in image: " + pic);
                diskImage.setImageBitmap(pic);
            }
        });
    }

    private void getLrcImage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Id获取
                MusicServerConnect musicServerConnect = new MusicServerConnect();
                musicServerConnect.init(name,null, MusicServerConnect.SEARCH_RETURN_ID);
                Log.d(TAG, "run: name" + name);
                while (musicServerConnect.usefulInfo == null){}
                id = musicServerConnect.usefulInfo;
                Log.d(TAG, "run: " + id);
                //歌词获取
                MusicServerConnect musicServerConnect2 = new MusicServerConnect();
                musicServerConnect2.init(null, id, MusicServerConnect.LRC);
                while (musicServerConnect2.usefulInfo == null){}
                lyricList = (ArrayList<String>)musicServerConnect2.lrcSentence;
                timeList = (ArrayList<String>)musicServerConnect2.lrcTime;
                Log.d(TAG, "run: " + lyricList);
                Log.d(TAG, "run: " + timeList);
                //图片获取
                MusicServerConnect musicServerConnect3 = new MusicServerConnect();
                musicServerConnect3.init(null,id, MusicServerConnect.PIC);
                while (musicServerConnect3.usefulInfo == null){}
                pic = musicServerConnect3.picture;
                Log.d(TAG, "run: " + musicServerConnect3.picture);
                Log.d(TAG, "run: diskimage" + diskImage);
                setImage();
            }
        }).start();
    }

    private void setView(Song song)
    {
        diskTitle.setText(song.getName());
        diskInfo.setText(song.getSinger());
        name = song.getName();
        getLrcImage();
//        diskImage
    }
}
