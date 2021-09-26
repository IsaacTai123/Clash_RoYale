package com.example.clashroyale.models;

import android.widget.ImageView;

import com.example.clashroyale.R;

public class Archor extends MovingObject implements ICard {

    //Constructor Init "Anchor" Object
    public Archor(ImageView img)
    {
        width = 100;
        height = 100;
        imageResId = R.drawable.archor_instance;
        imgUnit = img;
    }
}
