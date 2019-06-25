package com.serenity.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.serenityapp.R;

public class CircleProgressBar extends View {

    private int outsideCircleColor;
    private float outsideCircleRadius;
    private int outsideCircleStyle;
    private float outsideCircleWidth;

    private int insideCircleColor;
    private float insideCircleRadius;
    private int insideCircleStyle;
    private float insideCircleWidth;

    private int progressMax;
    private int progressColor;
    private float progress;

    private float startAngle;
    private boolean useCenter;

    private float dotRadius;
    private int dotColor;

    private double detectInsideRadius;
    private double detectOutsideRadius;

    private RectF oval;

    private Paint outsideCirclePaint;
    private Paint insideCirclePaint;
    private Paint progressPaint;
    private Paint dotPaint;

    private OnProgressChangedListener progressChangedListener;
    public interface OnProgressChangedListener {
        void onProgressChanged(View view, float progress);
    }

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
        outsideCircleColor = typedArray.getColor(R.styleable.CircleProgressBar_outside_circle_color, Color.WHITE);
        outsideCircleRadius = typedArray.getDimension(R.styleable.CircleProgressBar_outside_circle_radius, 100);
        outsideCircleStyle = typedArray.getInt(R.styleable.CircleProgressBar_outside_circle_style, 0);
        outsideCircleWidth = typedArray.getDimension(R.styleable.CircleProgressBar_outside_circle_width, 10);

        insideCircleColor = typedArray.getColor(R.styleable.CircleProgressBar_inside_circle_color, Color.WHITE);
        insideCircleRadius = typedArray.getDimension(R.styleable.CircleProgressBar_inside_circle_radius, 100);
        insideCircleStyle = typedArray.getInt(R.styleable.CircleProgressBar_inside_circle_style, 0);
        insideCircleWidth = typedArray.getDimension(R.styleable.CircleProgressBar_inside_circle_width, 10);

        progressMax = typedArray.getInt(R.styleable.CircleProgressBar_progress_max, 100);
        progress = typedArray.getInt(R.styleable.CircleProgressBar_progress, 0);
        progressColor = typedArray.getColor(R.styleable.CircleProgressBar_progress_color, Color.BLACK);

        startAngle = typedArray.getFloat(R.styleable.CircleProgressBar_start_angle, -90);
        useCenter = typedArray.getBoolean(R.styleable.CircleProgressBar_use_center, false);
        startAngle = startAngle < -360 ? -360 : startAngle;
        startAngle = startAngle > 360 ? 360 : startAngle;

        dotRadius = typedArray.getDimension(R.styleable.CircleProgressBar_dot_radius, 10);
        dotColor = typedArray.getColor(R.styleable.CircleProgressBar_dot_color, Color.WHITE);

        typedArray.recycle();
        initPaint();

        detectInsideRadius = Math.pow(insideCircleRadius - 0.5 * dotRadius, 2);
        detectOutsideRadius = Math.pow(outsideCircleRadius + 0.5 * dotRadius, 2);
    }

    private void initPaint(){
        outsideCirclePaint = new Paint();
        outsideCirclePaint.setColor(outsideCircleColor);
        outsideCirclePaint.setStrokeWidth(outsideCircleWidth);
        outsideCirclePaint.setStyle(this.getPaintStyle(outsideCircleStyle));
        outsideCirclePaint.setAntiAlias(true);

        insideCirclePaint = new Paint();
        insideCirclePaint.setColor(insideCircleColor);
        insideCirclePaint.setStrokeWidth(insideCircleWidth);
        insideCirclePaint.setStyle(this.getPaintStyle(insideCircleStyle));
        insideCirclePaint.setAntiAlias(true);

        progressPaint = new Paint();
        progressPaint.setColor(insideCircleColor);
        progressPaint.setStyle(this.getPaintStyle(insideCircleStyle));
        progressPaint.setStrokeWidth(insideCircleWidth);
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(progressColor);

        dotPaint = new Paint();
        dotPaint.setColor(dotColor);
        dotPaint.setStyle(Paint.Style.FILL);

        oval = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth() / 2;
        float ratio = (float) progress / progressMax;
        canvas.drawCircle(centre, centre, outsideCircleRadius, outsideCirclePaint);
        canvas.drawCircle(centre, centre, insideCircleRadius, insideCirclePaint);

        oval.left = centre - insideCircleRadius;
        oval.top = oval.left;
        oval.right = centre + insideCircleRadius;
        oval.bottom = oval.right;
        canvas.drawArc(oval, startAngle, 360 * ratio, useCenter, progressPaint);

        float dotCentreX = centre + (float) Math.sin(Math.PI * ratio * 2) * insideCircleRadius;
        float dotCentreY = centre - (float) Math.cos(Math.PI * ratio * 2) * insideCircleRadius;
        canvas.drawCircle(dotCentreX, dotCentreY, dotRadius, dotPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = this.getViewSize(100, widthMeasureSpec);
        int height = this.getViewSize(100, heightMeasureSpec);
        if(width < height) {
            height = width;
        }
        else {
            width = height;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float X = event.getX();
        float Y = event.getY();
        int centre = getWidth() / 2;
        double radius = Math.pow(centre - X, 2) + Math.pow(centre - Y, 2);
        if(radius < detectInsideRadius && radius > detectOutsideRadius){
            return false;
        }
        radius = Math.sqrt(radius);
        double ratio = Math.asin(Math.abs(X - centre) / radius) / (2 * Math.PI);
        if(ratio < 0.3){
            ratio = Math.acos(Math.abs(Y - centre) / radius) / (2 * Math.PI);
        }
        if(X <= centre && Y <= centre){
            ratio = 1 - ratio;
        }
        else if(X >= centre && Y >= centre){
            ratio = 0.5 - ratio;
        }
        else if(X <= centre && Y >= centre){
            ratio = 0.5 + ratio;
        }
        this.setProgress((float) ratio * progressMax);
        if (progressChangedListener != null) {
            progressChangedListener.onProgressChanged(this, progress);
        }
        invalidate();
        return true;
    }

    private int getViewSize(int defaultSize, int measureSpec){
        int mySize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode){
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
            default:
                mySize = defaultSize;
                break;
        }
        return mySize;
    }

    private Paint.Style getPaintStyle(int style){
        Paint.Style paintStyle;
        switch (style){
            case 1:
                paintStyle = Paint.Style.STROKE;
                break;
            case 2:
                paintStyle = Paint.Style.FILL_AND_STROKE;
                break;
            default:
                paintStyle = Paint.Style.FILL;
                break;
        }
        return paintStyle;
    }

    public void setProgressMax(int progressMax){
        this.progressMax = progressMax;
    }
    public int getProgressMax(){
        return this.progressMax;
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
    public void setOutsideCircleColor(int color){
        this.outsideCircleColor = color;
    }
    public int getOutsideCircleColor(){
        return this.outsideCircleColor;
    }
    public void setOutsideCircleRadius(int radius){
        this.outsideCircleRadius = radius;
    }
    public float getOutsideCircleRadius(){
        return this.outsideCircleRadius;
    }
    public void setOutsideCircleStyle(int style){
        this.outsideCircleStyle = style;
    }
    public int getOutsideCircleStyle(){
        return this.outsideCircleStyle;
    }
    public void setInsideCircleColor(int color){
        this.insideCircleColor = color;
    }
    public int getInsideCircleColor(){
        return this.insideCircleColor;
    }
    public void setInsideCircleRadius(float radius){
        this.insideCircleRadius = radius;
    }
    public float getInsideCircleRadius(){
        return this.insideCircleRadius;
    }
    public void setInsideCircleStyle(int style){
        this.insideCircleStyle = style;
    }
    public int getInsideCircleStyle(){
        return this.insideCircleStyle;
    }
    public void setDotRadius(float radius){
        this.dotRadius = radius;
    }
    public float getDotRadius(){
        return this.dotRadius;
    }
    public void setDotColor(int color){
        this.dotColor = color;
    }
    public int getDotColor(){
        return dotColor;
    }
    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        this.progressChangedListener = onProgressChangedListener;
    }
}
