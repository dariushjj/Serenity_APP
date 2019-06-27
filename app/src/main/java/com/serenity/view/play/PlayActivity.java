package com.serenity.view.play;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.serenityapp.R;
import com.serenity.view.widget.BackTitleView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class PlayActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private BackTitleView backTitleView;
    private TextView musicTitleView;
    private TextView musicInfoView;
    private CircleIndicator circleIndicator;
    private PlayControlView playControlView;
    private List<View> viewList;

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
}
