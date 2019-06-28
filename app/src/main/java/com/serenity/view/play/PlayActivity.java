package com.serenity.view.play;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.serenityapp.R;
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
    private BackTitleView backTitleView;
    private TextView musicTitleView;
    private TextView musicInfoView;
    private TextView backTitleTextView;
    private CircleIndicator circleIndicator;
    private PlayControlView playControlView;
    private List<View> viewList;
    private View diskView;
    private View lyricView;
    private WheelView wheelView;
    private Button play;

    private ArrayList<String> lyricList = null;
    private ArrayList<String> timeList = null;

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String url = "";
    private CircleImageView diskImage;
    private boolean isButtonStop = false;

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

        this.initVariables();
        this.initViewList();

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
                    style.selectedTextSize = 15;
                    style.backgroundColor = Color.parseColor("#00000000");
                    wheelView.setStyle(style);
                }
                return viewList.get(position);
            }
        };

        viewPager.setAdapter(pagerAdapter);
        circleIndicator.setViewPager(viewPager);

        play = (Button)findViewById(R.id.play_stop_start_button);
        diskImage = (CircleImageView) findViewById(R.id.play_cover);
        wheelView = (WheelView)findViewById(R.id.play_lyric_wheel_view);
        play.setOnClickListener(this);
    }

    private void initVariables(){
        viewPager = findViewById(R.id.play_view_pager);
        backTitleView = findViewById(R.id.play_title_view);
        backTitleTextView = findViewById(R.id.title_back_text);
        backTitleTextView.setText(PLAY_TITLE_TEXT);
        musicTitleView = findViewById(R.id.play_disk_music_title);
        musicInfoView = findViewById(R.id.play_disk_music_info);
        circleIndicator = findViewById(R.id.play_indicator);
        playControlView = findViewById(R.id.play_control_view);
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
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
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

                        if (url.equals("")){
                            MusicServerConnect play = new MusicServerConnect();
                            play.init(null,"573747359", MusicServerConnect.URL);
                            while (play.usefulInfo == null || play.equals("")){ }
                            url = play.usefulInfo;
                            initMediaPlayer(url);
                        }

                        if (!mediaPlayer.isPlaying()){
                            mediaPlayer.start();
                        }else {
                            mediaPlayer.pause();
                        }
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MusicServerConnect imageConnect = new MusicServerConnect();
                        imageConnect.init(null,"573747359", MusicServerConnect.PIC);
                        while (imageConnect.picture == null || imageConnect.equals(null)){}
                        Log.d(TAG, "run: " + imageConnect.picture);
                        // TODO: 2019/6/27 图片无法显示
                    }
                }).start();

                break;
            default:
                break;
        }
    }

    private void setImage(final String uri) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                diskImage.setImageURI(Uri.parse(uri));
            }
        });
    }

    private void setLrc(final String lrc){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    private void initMediaPlayer(String url) {
        try {
            mediaPlayer.setDataSource(PlayActivity.this, Uri.parse(url));
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
