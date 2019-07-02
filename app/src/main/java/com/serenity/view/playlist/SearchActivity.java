package com.serenity.view.playlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

import com.android.serenityapp.R;
import com.serenity.model.Song;
import com.serenity.view.play.PlayActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //when hitting the search button, trigger that method
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            //when query text changed, trigger that method
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        recyclerView = findViewById(R.id.search_recycler_view);
        initSongList();
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
    }

    private void initSongList(){
        //search result
        songList = null;
    }
}
