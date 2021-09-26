package com.example.clashroyale.models;

import android.media.Image;
import android.widget.ImageView;

public class MovingObject {
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected int imageResId;
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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getImageRedId() {
        return imageResId;
    }

//    public void setImage(ImageView image) {
//        this.image = image;
//    }
}
