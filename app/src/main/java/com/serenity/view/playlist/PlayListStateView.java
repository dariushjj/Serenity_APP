package com.serenity.view.playlist;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.serenityapp.R;
import com.serenity.severconnect.MusicPlayerServer;

import de.hdodenhof.circleimageview.CircleImageView;


public class PlayListStateView extends ConstraintLayout {

    private CircleImageView circleImageView;
    private TextView titleText;
    private TextView infoText;
    private Button previousBtn;
    private Button nextBtn;
    private Button stopStartBtn;


    public PlayListStateView(final Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.play_list_state, this);

        circleImageView = findViewById(R.id.play_list_state_image);
        titleText = findViewById(R.id.play_list_state_title_text);
        infoText = findViewById(R.id.play_list_state_info_text);
        previousBtn = findViewById(R.id.play_list_state_previous_button);
        nextBtn = findViewById(R.id.play_list_state_next_button);
        stopStartBtn = findViewById(R.id.play_list_state_stop_start_button);

        stopStartBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intentService = new Intent(context, MusicPlayerServer.class);
//                intentService.putExtra("uri", songAdapter.uri);
//                intentService.putExtra("isLocal", true);
//                startService(intentService);
            }
        });

    }
}
