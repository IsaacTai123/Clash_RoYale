package com.example.clashroyale.models;


import com.example.clashroyale.R;

public class Archor extends MovingObject implements ICard {

    //Constructor Init "Anchor" Object
    public Archor()
    {
        width = 100;
        height = 100;
        imageResId_ins = R.drawable.archor_instance;
        imageResId_card = R.drawable.archor_card;
    }
}
