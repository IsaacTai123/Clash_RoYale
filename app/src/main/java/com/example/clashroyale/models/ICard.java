package com.example.clashroyale.models;

import android.widget.ImageView;

 public interface ICard {
//    float x = 0;
//    float y = 0;
//    float width;
//    float height;
//    int imageResId;
//     ImageView imgUnit;

    float getX();
    void setX(float x);
    float getY();
    void setY(float y);
    int getWidth();
    void setWidth(int width);
    int getHeight();
    void setHeight(int height);
    int getImageResId_ins();
    int getImageResId_card();
}

