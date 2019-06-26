package com.serenity.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.serenityapp.R;

public class MenuTitleView extends ConstraintLayout {

    public MenuTitleView(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_menu, this);
    }
}
