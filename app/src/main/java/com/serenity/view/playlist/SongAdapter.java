package com.serenity.view.playlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serenityapp.R;
import com.serenity.model.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private Context context;
    private List<Song> songList;
    private TextView stateTitleText;
    private TextView stateInfoText;
    private ImageView stateImageView;
    private int position;

    private OnItemClickListener onItemClickListener;
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
            titleText = view.findViewById(R.id.song_item_title);
            infoText = view.findViewById(R.id.song_item_info);
        }
    }

    public SongAdapter(Context context, List<Song> songList){
        this.context = context;
        this.songList = songList;
        this.position = -1;
        View view = LayoutInflater.from(context).inflate(R.layout.activity_play, null);
        stateTitleText = view.findViewById(R.id.play_list_state_title_text);
        stateInfoText = view.findViewById(R.id.play_list_state_info_text);
        stateImageView = view.findViewById(R.id.play_list_state_image);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_play, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int pos) {
        Song song = songList.get(pos);
        holder.titleText.setText(song.getName());
        holder.infoText.setText(song.getSinger());
        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int p = holder.getLayoutPosition();
                    Song s = songList.get(p);
                    stateImageView.setImageBitmap(s.getAlbumImage());
                    stateInfoText.setText(s.getSinger());
                    stateTitleText.setText(s.getName());
                    position = p;
                    notifyDataSetChanged();
                    onItemClickListener.onClick(holder.itemView, p);
                }
            });
        }
        if(position == pos){
            holder.titleText.setTextColor(0xFF9500);
            holder.infoText.setTextColor(0xFF9500);
        }
        else{
            holder.titleText.setTextColor(0xffffff);
            holder.infoText.setTextColor(0xaaa);
        }
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
}
