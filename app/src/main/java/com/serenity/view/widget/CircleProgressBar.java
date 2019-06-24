package com.serenity.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.android.serenityapp.R;

public class CircleProgressBar extends View {

    private int progressMax;
    private int circleColor;
    private int progressColor;
    private float circleWidth;
    private float circleRadius;
    private float progress;
    private float startAngle;
    private boolean useCenter;

    private Paint circlePaint;
    private Paint progressPaint;

    public CircleProgressBar(Context context){
        super(context, null);
        init(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs){
        super(context, attrs, 0);
        init(context, attrs);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        circleColor = typedArray.getColor(R.styleable.CircleProgressBar_circle_color, Color.WHITE);
        progressMax = typedArray.getInt(R.styleable.CircleProgressBar_progress_max, 100);
        circleWidth = typedArray.getDimension(R.styleable.CircleProgressBar_circle_width, 5);
        circleRadius = typedArray.getDimension(R.styleable.CircleProgressBar_circle_radius, 100);
        progress = typedArray.getFloat(R.styleable.CircleProgressBar_progress, 0);
        startAngle = typedArray.getFloat(R.styleable.CircleProgressBar_start_angle, -90);
        useCenter = typedArray.getBoolean(R.styleable.CircleProgressBar_use_center, false);
        progressColor = typedArray.getColor(R.styleable.CircleProgressBar_progress_color, Color.BLACK);
        startAngle = startAngle < -360 ? -360 : startAngle;
        startAngle = startAngle > 360 ? 360 : startAngle;
        typedArray.recycle();
        initPaint();
    }

    private void initPaint(){
        circlePaint = new Paint();
        circlePaint.setColor(circleColor);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circlePaint.setAntiAlias(true);

        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(circleWidth);
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(progressColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth() / 2;
        canvas.drawCircle(centre, centre, circleRadius, circlePaint);

        RectF oval = new RectF();
        oval.left = centre - circleRadius;
        oval.top = oval.left;
        oval.right = centre + circleRadius;
        oval.bottom = oval.right;
        canvas.drawArc(oval, startAngle, 360 * (progress / progressMax), useCenter, progressPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            width = size;
        }
        else {
            width = (int) (2 * (circleRadius + circleWidth));
        }
        size = MeasureSpec.getSize(heightMeasureSpec);
        mode = MeasureSpec.getMode(heightMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            height = size;
        }
        else {
            height = (int) (2 * (circleRadius + circleWidth));
        }
        setMeasuredDimension(width, height);
    }

    public void setProgressMax(int progressMax){
        this.progressMax = progressMax;
    }
    public int getProgressMax(){
        return this.progressMax;
    }
    public void setCircleColor(int circleColor){
        this.circleColor = circleColor;
    }
    public int getCircleColor(){
        return this.circleColor;
    }
    public void setCircleWidth(float circleWidth){
        this.circleWidth = circleWidth;
    }
    public float getCircleWidth(){
        return this.circleWidth;
    }
    public void setOutsideCircleRadius(float radius){
        this.circleRadius = radius;
    }
    public float getOutsideCircleRadius(){
        return this.circleRadius;
    }
    public void setProgress(float progress){
        this.progress = progress;
    }
    public float getProgress(){
        return this.progress;
    }
    public void setStartAngle(float startAngle){
        this.startAngle = startAngle;
    }
    public float getStartAngle(){
        return this.startAngle;
    }
    public void setUseCenter(boolean useCenter){
        this.useCenter = useCenter;
    }
    public boolean getUseCenter(){
        return this.useCenter;
    }
    public void setProgressColor(int color){
        this.progressColor = color;
    }
    public int getProgressColor(){
        return this.progressColor;
    }
}
