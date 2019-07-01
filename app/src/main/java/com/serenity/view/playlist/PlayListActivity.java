package com.serenity.view.playlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.serenityapp.R;
import com.serenity.dao.SongDao;
import com.serenity.model.Song;
import com.serenity.severconnect.MusicPlayerServer;
import com.serenity.view.play.PlayActivity;
import com.serenity.view.widget.BackTitleView;

import java.util.ArrayList;
import java.util.List;

public class PlayListActivity extends AppCompatActivity {
    private static final String TAG = "PlayListActivity";
    private BackTitleView backTitleView;
    private PlayListStateView playListStateView;
    private RecyclerView recyclerView;
    private SongAdapter songAdapter;
    private List<Song> songList;
    private Button searchBtn;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        readSongList();

        backTitleView = findViewById(R.id.play_list_back_title_view);
        playListStateView = findViewById(R.id.play_list_state_view);
        recyclerView = findViewById(R.id.play_list_recycler_view);
        searchBtn = findViewById(R.id.play_list_search_button);
        //make sure only search once
        if(searchBtn.getVisibility() == View.VISIBLE){
            searchBtn.setVisibility(View.GONE);
        }
        else {
            searchBtn.setVisibility(View.VISIBLE);
        }
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlayListActivity.this, SearchActivity.class));
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        songAdapter = new SongAdapter(songList);
        recyclerView.setAdapter(songAdapter);
        songAdapter.notifyDataSetChanged();

        songAdapter.setOnItemClickListener(new SongAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                songAdapter.setPosition(position);
                songAdapter.notifyDataSetChanged();
            }
        });

        Intent intent = new Intent(this, MusicPlayerServer.class);


        playListStateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.play_list_state_previous_button:
                        //load previous song
                        //titleText.setText()
                        //infoText.setText()
                        break;
                    case R.id.play_list_state_next_button:
                        //load next song
                        //titleText.setText()
                        //infoText.setText()
                        break;
                    case R.id.play_list_state_stop_start_button:
                        //get information about the state of the song
                        //if singing then stop
                        //if stopped  the singing
                        break;
                    case R.id.play_list_state_image:
                    case R.id.play_list_state_info_text:
                    case R.id.play_list_state_title_text:
                        break;
                    default:
                        Intent intent = new Intent(PlayListActivity.this, PlayActivity.class);
                        intent.putExtra("name", songAdapter.name);
                        intent.putExtra("singer", songAdapter.singer);
                        intent.putExtra("uri", songAdapter.uri);
                        intent.putExtra("isLocal", true);
                        startActivity(intent);
//                        Intent intentService = new Intent(PlayListActivity.this, MusicPlayerServer.class);
//                        startService(intentService);
                }
            }
        });
    }

    /**
     * read list of songs from database
     */
    public void readSongList(){
        SongDao songDao = new SongDao();
        songList = songDao.getSongs();
        Log.d(TAG, "readSongList: " + songList);
    }
}
