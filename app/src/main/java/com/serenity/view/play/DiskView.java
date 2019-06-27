package com.serenity.view.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.serenityapp.R;

public class DiskView extends LinearLayout {

    private CircleProgressBar circleProgressBar;
    private TextView currentProgressText;
    private TextView leftProgressText;

    public DiskView(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.play_disk, this);
        circleProgressBar = findViewById(R.id.play_circle_progress_bar);
        currentProgressText = findViewById(R.id.play_current_progress_text);
        leftProgressText = findViewById(R.id.play_left_progress_text);
    }
}
