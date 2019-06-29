package com.serenity.view.playlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.serenityapp.R;
import com.serenity.model.Song;
import com.serenity.view.play.PlayActivity;
import com.serenity.view.widget.BackTitleView;

import java.util.List;

public class PlayListActivity extends AppCompatActivity {

    private BackTitleView backTitleView;
    private PlayListStateView playListStateView;
    private RecyclerView recyclerView;
    private SongAdapter songAdapter;
    private List<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        backTitleView = findViewById(R.id.play_list_back_title_view);
        playListStateView = findViewById(R.id.play_list_state_view);
        recyclerView = findViewById(R.id.play_list_recycler_view);

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
                        //translate data
                        //intent.putExtra()
                        startActivity(intent);
                }
            }
        });
    }

    /**
     * read list of songs from database
     */
    public void readSongList(){
        songList = null;
    }
}
