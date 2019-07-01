package com.serenity.view.playlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.serenityapp.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class PlayListStateView extends ConstraintLayout {

    private CircleImageView circleImageView;
    private TextView titleText;
    private TextView infoText;
    private Button previousBtn;
    private Button nextBtn;
    private Button stopStartBtn;

    public PlayListStateView(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.play_list_state, this);

        circleImageView = findViewById(R.id.play_list_state_image);
        titleText = findViewById(R.id.play_list_state_title_text);
        infoText = findViewById(R.id.play_list_state_info_text);
        previousBtn = findViewById(R.id.play_list_state_previous_button);
        nextBtn = findViewById(R.id.play_list_state_next_button);
        stopStartBtn = findViewById(R.id.play_list_state_stop_start_button);
    }
}
