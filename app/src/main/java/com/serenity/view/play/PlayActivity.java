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
import com.serenity.severconnect.MusicPlayerServer;
import com.serenity.severconnect.MusicServerConnect;
import com.serenity.view.widget.BackTitleView;
import com.wx.wheelview.widget.WheelView;
import com.wx.wheelview.adapter.SimpleWheelAdapter;
import com.wx.wheelview.common.WheelData;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;
import static com.example.util.ConstantUtil.LYRIC_LIST;
import static com.example.util.ConstantUtil.PLAY_TITLE_TEXT;
import static com.example.util.ConstantUtil.TIME_LIST;



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
    private Button play;

    private ArrayList<String> lyricList = null;
    private ArrayList<String> timeList = null;

    private boolean isButtonStop = false;
    private MediaPlayer mediaPlayer = new MediaPlayer();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Intent intent = getIntent();
        lyricList = intent.getStringArrayListExtra(LYRIC_LIST);
        timeList = intent.getStringArrayListExtra(TIME_LIST);

        initVariables();
        initViewList();
        initMediaPlayer(intent.getStringExtra("uri"), true);
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

    }

    /**
     * 初始化变量
     */
    private void initVariables(){
        viewPager = findViewById(R.id.play_view_pager);
        backTitleTextView = findViewById(R.id.title_back_text);
        backTitleTextView.setText(PLAY_TITLE_TEXT);
        circleIndicator = findViewById(R.id.play_indicator);
        play = (Button)findViewById(R.id.play_stop_start_button);
//        diskImage = (CircleImageView) findViewById(R.id.play_cover);
        wheelView = (WheelView)findViewById(R.id.play_lyric_wheel_view);
    }

    private void initViewList(){
        viewList = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        diskView = inflater.inflate(R.layout.play_disk, null);
        lyricView = inflater.inflate(R.layout.play_lyric, null);
        viewList.add(diskView);
        viewList.add(lyricView);
    }

    private ArrayList<WheelData> initLyricData(){
        ArrayList<WheelData> list = new ArrayList<>();
        WheelData item;
        if(lyricList == null || timeList == null || lyricList.size() != timeList.size()){
            item = new WheelData();
            item.setName("No lyrics");
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (isButtonStop){
                            play.setBackgroundResource(R.drawable.start);
                            isButtonStop = !isButtonStop;
                        }else {
                            play.setBackgroundResource(R.drawable.stop);
                            isButtonStop = !isButtonStop;
                        }

                        if (!mediaPlayer.isPlaying()){
                            mediaPlayer.start();
                        }else {
                            mediaPlayer.pause();
                        }
                    }
                }).start();
                break;
            default:
                break;
        }
    }

    private void initMediaPlayer(String url, boolean isLocal) {
        try {
            if (!isLocal){
                mediaPlayer.setDataSource(PlayActivity.this, Uri.parse(url));
            }else {
                mediaPlayer.setDataSource(url);
            }
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
