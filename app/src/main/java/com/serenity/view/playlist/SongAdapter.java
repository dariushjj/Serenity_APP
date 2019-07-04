package com.serenity.view.playlist;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serenityapp.R;
import com.serenity.model.Song;
import com.serenity.severconnect.MusicPlayerServer;
import com.serenity.severconnect.MusicServerConnect;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.BIND_AUTO_CREATE;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private static final String TAG = "SongAdapter";
    private List<Song> songList;
    private TextView stateTitleText;
    private TextView stateInfoText;
    private Button stateStopStartBtn;
    public String uri;
    public String name;
    public String singer;
    public String id;
    public Bitmap pic;
    private ImageView stateImageView;
    private int position;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private MediaPlayer player = new MediaPlayer();
    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View songView;
        TextView titleText;
        TextView infoText;
        public ViewHolder(View view){
            super(view);
            songView = view;
            titleText = (TextView)view.findViewById(R.id.song_item_title);
            infoText = (TextView)view.findViewById(R.id.song_item_info);
        }
    }

    public SongAdapter(Context context, List<Song> songList){
        this.context = context;
        this.songList = songList;
        this.position = -1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View parentView = (View) parent.getParent();
        stateTitleText = parentView.findViewById(R.id.play_list_state_title_text);
        stateInfoText = parentView.findViewById(R.id.play_list_state_info_text);
        stateImageView = parentView.findViewById(R.id.play_list_state_image);
        stateStopStartBtn = parentView.findViewById(R.id.play_stop_start_button);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.titleText.setText(song.getName());
        holder.infoText.setText(song.getSinger());
        if(onItemClickListener != null) {
            if(position == this.position){
                holder.titleText.setTextColor(Color.parseColor("#FF9500"));
                holder.infoText.setTextColor(Color.parseColor("#FF9500"));
                name = song.getName();
                singer = song.getSinger();
                uri = song.getUri();

                stateTitleText.setText(name);
                stateInfoText.setText(singer);
//                stateStopStartBtn.setBackgroundResource(R.drawable.stop);
//                stateImageView.setBackground();
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
            }
            else{
                holder.titleText.setTextColor(Color.WHITE);
                holder.infoText.setTextColor(Color.WHITE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(view, holder.getLayoutPosition());
                }
            });

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public int getPosition(){
        return this.position;
    }
    public void setPosition(int position){
        this.position = position;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setSongList(ArrayList<Song> songList){
        this.songList = songList;
        notifyDataSetChanged();
    }

}
