package com.serenity.view.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class CircleProgressBar extends ProgressBar {

    private int progressMax;
    private int circleColor;
    private Paint paint;

    public CircleProgressBar(Context context){
        super(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs){
        super(context, attrs, 0);
    }
}
