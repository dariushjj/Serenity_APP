package com.serenity.view.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.serenityapp.R;

public class BackTitleView extends ConstraintLayout {

    private Button backBtn;

    public BackTitleView(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_back, this);
        backBtn = findViewById(R.id.title_back_button);
        backBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) getContext()).finish();
            }
        });
    }
}
