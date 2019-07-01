package com.serenity.view.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.serenityapp.R;

public class MainHomeView extends ConstraintLayout implements View.OnClickListener {

    public MainHomeView(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.home_main, this);

        /*
        CardView musicCard = findViewById(R.id.music_card);
        CardView timerCard = findViewById(R.id.timer_card);
        CardView sleepCard = findViewById(R.id.sleep_card);
        CardView othersCard = findViewById(R.id.others_card);*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.music_card:
                break;
            case R.id.timer_card:
                break;
            case R.id.sleep_card:
                break;
            default:
        }
    }
}
