package com.example.guoxw.myapplication;

import android.content.Context;
import android.graphics.*;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RainView extends View {

    private static final String TAG = "RainView";
    private int rainNum = 50;
    private Paint mPaint;
    private int speed = 30;
    private int length = 20;
    private int rainColor;


    public RainView(Context context) {
        super(context);
    }

    public RainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rainColor=getResources().getColor(R.color.rainColor);
        mPaint = new Paint();
        mPaint.setColor(rainColor);
        timer = new Timer();
        timer.schedule(timerTask, 300, 150);
    }

    public RainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pointX = getWidth();
        pointY = getHeight();
        init();
        for (int i = 0; i < rainNum; i++) {
            mPaint.setAlpha(rains.get(i).alpha);
            DrawRain(rains.get(i).startX,
                    rains.get(i).startY,
                    rains.get(i).endX,
                    rains.get(i).endY,
                    canvas);
        }
        postInvalidate();


    }


    int alpha = 55;

    List<Rain> rains = new ArrayList<>();

    private int pointX, pointY;

    private void init() {
        for (int i = 0; i < rainNum; i++) {
            int startX = (int) (Math.random() * pointX);
            int startY = (int) (Math.random() * pointY);
            int alpha = (int) (Math.random() * 100) + 50;
            if (rains.size() < rainNum) {
                rains.add(new Rain(startX, startY, alpha, length));
            }
        }

    }

    private Timer timer;

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            for (int i = 0; i < rainNum; i++) {
                rains.get(i).startX = rains.get(i).startX;
                rains.get(i).startY = rains.get(i).startY + speed;
                rains.get(i).endX = rains.get(i).startX + length;
                rains.get(i).endY = rains.get(i).startY + length * 2;
                rains.get(i).alpha = rains.get(i).alpha + 5;
                if (rains.get(i).startY > pointY || rains.get(i).startX > pointX) {
                    rains.get(i).startX = (int) (Math.random() * pointX);
                    rains.get(i).startY = (int) (Math.random() * pointY);
                    rains.get(i).endX = rains.get(i).startX - length;
                    rains.get(i).endY = rains.get(i).startY + length;
                }
                if (rains.get(i).alpha > 255) {
                    rains.get(i).alpha = 100;
                }
            }
            postInvalidate();


        }
    };


    Path mPath = new Path();

    private void DrawRain(int startX, int startY, int endX, int endY, Canvas canvas) {
        int y = (endY+startY-endX+startX)/2;
        int x = startX;
        int x2 = endX;
        int y2 = (endY+startY+endX-startX)/2;
        RectF rectF = new RectF(x, y, x2, y2);
        canvas.drawArc(rectF, 0, 180, true, mPaint);
        mPath = new Path();
        mPath.moveTo((startX +endX)/2, startY);
        mPath.lineTo(startX, (startY+endY) / 2);
        mPath.lineTo(endX, (startY+endY) / 2);
        canvas.drawPath(mPath, mPaint);
    }


    public void setRainNum(int rainNum) {
        this.rainNum = rainNum;
        postInvalidate();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        postInvalidate();
    }

    public void setLength(int length) {
        this.length = length;
        postInvalidate();
    }

    public void setRainColor(int rainColor) {
        this.rainColor = rainColor;
        postInvalidate();
    }
}
