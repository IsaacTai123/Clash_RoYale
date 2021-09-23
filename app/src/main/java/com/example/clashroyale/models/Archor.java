package com.example.clashroyale.models;

import android.widget.ImageView;

public class Archor extends MovingObject {

    //Constructor Init "Anchor" Object
    public Archor(ImageView img)
    {
        ImageView archor = img;
        setX(archor.getX());
        setY(archor.getY());
        setHeight(archor.getHeight());
        setWidth(archor.getWidth());
        setImage(archor);
    }
}
