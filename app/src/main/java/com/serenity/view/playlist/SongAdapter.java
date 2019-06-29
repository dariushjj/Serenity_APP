package com.serenity.view.playlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serenityapp.R;
import com.serenity.model.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private List<Song> songList;
    private TextView stateTitleText;
    private TextView stateInfoText;
    public String uri;
    public String name;
    public String singer;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View songView;
        TextView titleText;
        TextView infoText;
        public ViewHolder(View view){
            super(view);
            songView = view;
            titleText = view.findViewById(R.id.song_item_title);
            infoText = view.findViewById(R.id.song_item_info);
        }
    }

    public SongAdapter(List<Song> songList){
        this.songList = songList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        View parentView = (View) parent.getParent();
        stateTitleText = parentView.findViewById(R.id.play_list_state_title_text);
        stateInfoText = parentView.findViewById(R.id.play_list_state_info_text);
        final ViewHolder holder = new ViewHolder(view);
        holder.songView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Song song = songList.get(position);
                uri = song.getUri();
                name = song.getName();
                singer = song.getSinger();
                stateTitleText.setText(name);
                stateInfoText.setText(singer);
                //play music
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.titleText.setText(song.getName());
        holder.infoText.setText(song.getSinger());
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }
}
