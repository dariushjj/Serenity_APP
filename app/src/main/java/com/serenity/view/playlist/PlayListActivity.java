package com.serenity.view.playlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.serenityapp.R;
import com.serenity.dao.SongDao;
import com.serenity.model.Song;
import com.serenity.severconnect.MusicPlayerServer;
import com.serenity.view.Sleep.SleepActivity;
import com.serenity.view.play.PlayActivity;
import com.serenity.view.widget.BackTitleView;

import java.util.ArrayList;
import java.util.List;

import static com.example.util.ConstantUtil.PLAY_LIST;

public class PlayListActivity extends AppCompatActivity {
    private static final String TAG = "PlayListActivity";
    private BackTitleView backTitleView;
    private PlayListStateView playListStateView;
    private RecyclerView recyclerView;
    private SongAdapter songAdapter;
    public static List<Song> songList;
    private Button searchBtn;
    private TextView textView;
    private Button stopStartBtn;
    private Button previousBtn;
    private Button nextBtn;
    private static MediaPlayer player = new MediaPlayer();
    public static int position;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        //读取数据库
        readSongList();
        //初始化界面
        backTitleView = findViewById(R.id.play_list_back_title_view);
        textView = backTitleView.findViewById(R.id.title_back_text);
        textView.setText(PLAY_LIST);
        playListStateView = findViewById(R.id.play_list_state_view);
        recyclerView = findViewById(R.id.play_list_recycler_view);
        searchBtn = findViewById(R.id.play_list_search_button);
        stopStartBtn = findViewById(R.id.play_list_state_stop_start_button);
        previousBtn = findViewById(R.id.play_list_state_previous_button);
        nextBtn = findViewById(R.id.play_list_state_next_button);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlayListActivity.this, SearchActivity.class));
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        songAdapter = new SongAdapter(this, songList);
        recyclerView.setAdapter(songAdapter);
        songAdapter.notifyDataSetChanged();

        songAdapter.setOnItemClickListener(new SongAdapter.OnItemClickListener()
       {
           @Override
           public void onClick(View view, int pos)
           {
               songAdapter.setPosition(pos);
               songAdapter.notifyDataSetChanged();
               position = pos;
               if(player.isPlaying())
               {
                   player.stop();
                   //stopStartBtn.setBackgroundResource(R.drawable.stop);
               }
               player = new MediaPlayer();
               Thread thread = new Thread(new Runnable()
               {
                   @Override
                   public void run()
                   {
                       try
                       {
                           Thread.sleep(200);
                           player.setDataSource(songAdapter.uri);
                           player.prepare();
                       }catch (Exception e)
                       {
                           e.printStackTrace();
                       }
                       player.start();
                   }
               });
               thread.start();
           }
       });

        stopStartBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(player.isPlaying())
                {
                    stopStartBtn.setBackgroundResource(R.drawable.start);
                    player.pause();
                }
                else
                {
                    stopStartBtn.setBackgroundResource(R.drawable.stop);
                    player.start();
                }
            }
        });

        previousBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                position = (--position) % songList.size();
                songAdapter.setPosition(position);
                Song song = songList.get(position);
                final String uri = song.getUri();
                if(player.isPlaying())
                {
                    player.stop();
                    //stopStartBtn.setBackgroundResource(R.drawable.stop);
                }
                player = new MediaPlayer();
                Thread thread = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(200);
                            player.setDataSource(uri);
                            player.prepare();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        player.start();
                    }
                });
                thread.start();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                position = (++position) % songList.size();
                songAdapter.setPosition(position);
                Song song = songList.get(position);
                final String uri = song.getUri();
                if(player.isPlaying())
                {
                    player.stop();
                    //stopStartBtn.setBackgroundResource(R.drawable.stop);
                }
                player = new MediaPlayer();
                Thread thread = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(200);
                            player.setDataSource(uri);
                            player.prepare();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        player.start();
                    }
                });
                thread.start();

            }
        });

        playListStateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayListActivity.this, PlayActivity.class);
                intent.putExtra("name", songAdapter.name);
                Log.d(TAG, "onClick: " + songAdapter.name);
                intent.putExtra("singer", songAdapter.singer);
                intent.putExtra("uri", songAdapter.uri);
                intent.putExtra("isLocal", true);
                intent.putExtra("position",position);
                startActivity(intent);
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
    public static MediaPlayer getplayer()
    {
        return player;
    }
    public static void setplayer(MediaPlayer mediaPlayer){
        player = mediaPlayer;
    }
    public static List<Song> getSongList(){
        return songList;
    }
    public static void setPosition(int pos){
        position = pos;
    }
}
