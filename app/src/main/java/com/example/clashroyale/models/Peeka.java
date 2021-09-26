package com.example.clashroyale.models;

import com.example.clashroyale.R;

public class Peeka extends MovingObject implements ICard {

    public Peeka() {
        width = 140;
        height = 140;
        imageResId_ins = R.drawable.peeka_instance;
        imageResId_card = R.drawable.peeka_card;
    }
}
