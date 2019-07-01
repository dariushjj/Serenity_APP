package com.serenity.view.alarmclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.serenityapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseMusicActivity extends AppCompatActivity {
    private List<Music> musicList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_music);
        initMusics();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.music_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MusicAdapter adapter = new MusicAdapter(musicList,this);
        recyclerView.setAdapter(adapter);

    }

    private void initMusics() {
        Music apple = new Music("loveyourself");
        musicList.add(apple);
        Music banana = new Music("baby");
        musicList.add(banana);
        Music orange = new Music("innocence");
        musicList.add(orange);
        Music watermelon = new Music("rolling in the deep");
        musicList.add(watermelon);
        Music pear = new Music("sugar");
        musicList.add(pear);
        Music grape = new Music("all of me");
        musicList.add(grape);
    }
}
