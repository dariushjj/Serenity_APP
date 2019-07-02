package com.serenity.view.playlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

import com.android.serenityapp.R;
import com.serenity.model.Song;
import com.serenity.severconnect.MusicServerConnect;
import com.serenity.view.play.PlayActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private Button backBtn;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private ArrayList<Song> songList;
    private SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        backBtn = findViewById(R.id.search_back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchView = findViewById(R.id.search_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.search_recycler_view);
        recyclerView.setLayoutManager(layoutManager);

//        initSongList();
        songAdapter = new SongAdapter(SearchActivity.this, songList);

        recyclerView.setAdapter(songAdapter);

        songAdapter.setOnItemClickListener(new SongAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                songAdapter.setPosition(position);
                Intent intent  = new Intent(SearchActivity.this, PlayActivity.class);
                //send some data to PlayActivity by using intent
                startActivity(intent);
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //when hitting the search button, trigger that method
            @Override
            public boolean onQueryTextSubmit(String s) {
                MusicServerConnect musicServerConnect = new MusicServerConnect();
                musicServerConnect.init(s , null, MusicServerConnect.SEARCH);
                while (musicServerConnect.usefulInfo == null){}
                songList = musicServerConnect.songList;
                Log.d(TAG, "onQueryTextSubmit: " + songList.get(1).getName());
                songAdapter.setSongList(songList);
                songAdapter.notifyDataSetChanged();
                return true;
            }

            //when query text changed, trigger that method
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

//    private void initSongList(){
//        //search result
//        songList = null;
//    }
}
