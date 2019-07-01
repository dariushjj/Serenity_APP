package com.serenity.view.alarmclock;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serenityapp.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{
    private List<Music> mMusicList;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder {
        View musicView;
        //ImageView fruitImage;
        TextView musicName;
        Button select_alarm_button;

        public ViewHolder(View view) {
            super(view);
            musicView = view;
            //fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            musicName = (TextView) view.findViewById(R.id.music_name);
            select_alarm_button=(Button) view.findViewById(R.id.select_alarm_music);
        }
    }
    public MusicAdapter(List<Music> musicList,Context context) {
        mMusicList = musicList;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                music_item, parent, false);
        final MusicAdapter adapter;
        final ViewHolder holder = new ViewHolder(view);
        holder.musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Music music = mMusicList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + music.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        holder.select_alarm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Music music = mMusicList.get(position);
                String data=music.getName();
                /*
                Intent intent=new Intent(ChooseMusicActivity.this,SetAlarmClockActivity.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);
                */
            }
        });
/*
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = holder.getAdapterPosition();
                final Fruit fruit = mFruitList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("want to delete the alarm?");//设置标题
                builder .setCancelable(false);//设置选项
                builder    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        Log.d("asdasd",fruit.getName());
                        mFruitList.remove(fruit.getName());
                        //remove from the database
                        mFruitList.remove(position);//集合移除该条
                        notifyItemRemoved(position);//通知移除该条
                        notifyItemRangeChanged(position,mFruitList.size()-position);

                    }
                });
                builder.show();

            }

        });
        */

        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Music music=mMusicList.get(position);
        holder.musicName.setText(music.getName());

    }
    @Override
    public int getItemCount() {
        return mMusicList.size();
    }


}
