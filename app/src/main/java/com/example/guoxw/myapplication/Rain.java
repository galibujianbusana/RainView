package com.example.guoxw.myapplication;

public class Rain {
    public int startX;
    public int startY;
    public int endX;
    public int endY;
    public int length;
    public int alpha;

    public Rain(int startX, int startY,int alpha,int length) {
        this.startX = startX;
        this.startY = startY;
        this.length=length;
        this.endX = startX+length;
        this.endY = startY+length*2;
        this.alpha=alpha;

    }


}
