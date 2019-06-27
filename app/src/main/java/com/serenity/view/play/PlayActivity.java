package com.serenity.view.play;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "PlayActivity";

    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private BackTitleView backTitleView;
    private TextView musicTitleView;
    private TextView musicInfoView;
    private CircleIndicator circleIndicator;
    private PlayControlView playControlView;
    private List<View> viewList;

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String url = "";
    private CircleImageView diskImage;
    private WheelView wheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

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
                return viewList.get(position);
            }
        };

        viewPager.setAdapter(pagerAdapter);
        circleIndicator.setViewPager(viewPager);

        Button play = (Button)findViewById(R.id.play_stop_start_button);
        diskImage = (CircleImageView) findViewById(R.id.play_cover);
        wheelView = (WheelView)findViewById(R.id.play_lyric_wheel_view); 
        play.setOnClickListener(this);
    }

    private void initVariables(){
        viewPager = findViewById(R.id.play_view_pager);
        backTitleView = findViewById(R.id.play_title_view);
        musicTitleView = findViewById(R.id.play_disk_music_title);
        musicInfoView = findViewById(R.id.play_disk_music_info);
        circleIndicator = findViewById(R.id.play_indicator);
        playControlView = findViewById(R.id.play_control_view);
    }

    private void initViewList(){
        viewList = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        viewList.add(inflater.inflate(R.layout.play_disk, null));
        viewList.add(inflater.inflate(R.layout.play_lyric, null));
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
                        if (url.equals("")){
                            MusicServerConnect play = new MusicServerConnect();
                            play.init(null,"444267215", MusicServerConnect.URL);
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
                        imageConnect.init(null,"444267215", MusicServerConnect.PIC);
                        while (imageConnect.picture == null || imageConnect.equals(null)){}
                        Log.d(TAG, "run: " + imageConnect.picture);
                        // TODO: 2019/6/27 图片无法显示
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MusicServerConnect lrcConnect = new MusicServerConnect();
                        lrcConnect.init(null,"444267215", MusicServerConnect.LRC);
                        while (lrcConnect.usefulInfo == null || lrcConnect.equals("")){ }
//                        Log.d(TAG, "run: " + lrcConnect.usefulInfo);
                        // TODO: 2019/6/27 歌词信息放置
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
