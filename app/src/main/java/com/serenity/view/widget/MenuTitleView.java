package com.serenity.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.serenityapp.R;
import com.google.android.material.navigation.NavigationView;

public class MenuTitleView extends ConstraintLayout {

    private NavigationView navigationView;
    private float downX;

    public MenuTitleView(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_menu, this);
        Button button = findViewById(R.id.title_menu_button);
        navigationView = findViewById(R.id.navigation_view_menu);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(x > navigationView.getWidth()){
                    navigationView.setVisibility(View.GONE);
                }
                downX = x;
                break;
            case MotionEvent.ACTION_UP:
                float dx = x - downX;
                if(Math.abs(dx) > 8 && dx > 0 && downX < 90) {
                    navigationView.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
        return true;
    }
}
