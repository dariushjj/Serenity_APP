package com.serenity.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.serenityapp.R;
import com.google.android.material.navigation.NavigationView;

import static com.example.util.ConstantUtil.PLAY;

public class MenuTitleView extends ConstraintLayout {

    public MenuTitleView(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_menu, this);
        Button button = findViewById(R.id.title_menu_button);
        TextView textView = findViewById(R.id.title_menu_text);
        textView.setText(PLAY);
    }
}
