package com.serenity.view.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.serenityapp.R;

public class PlayControlView extends ConstraintLayout implements View.OnClickListener {

    private Button previousBtn;
    private Button stopStartBtn;
    private Button nextBtn;

    private boolean isStop;

    public PlayControlView(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.play_control, this);

        previousBtn = findViewById(R.id.play_previous_button);
        stopStartBtn = findViewById(R.id.play_stop_start_button);
        nextBtn = findViewById(R.id.play_next_button);
        previousBtn.setOnClickListener(this);
        stopStartBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        isStop = false;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.play_previous_button:
                break;
            case R.id.play_stop_start_button:
                if(isStop){
                    isStop = false;
                }
                else {
                    isStop = true;
                }
                break;
            case R.id.play_next_button:
                break;
        }
    }

}
