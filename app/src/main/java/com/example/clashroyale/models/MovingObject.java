package com.example.clashroyale.models;

import android.media.Image;
import android.widget.ImageView;

public class MovingObject {
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected int imageResId_ins;
    protected int imageResId_card;
    protected ImageView imgUnit;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getImageResId_ins() {
        return imageResId_ins;
    }

    public int getImageResId_card() {
        return imageResId_card;
    }

//    public void setImage(ImageView image) {
//        this.image = image;
//    }
}
