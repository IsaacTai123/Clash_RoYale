package com.example.clashroyale.models;

import com.example.clashroyale.R;

public class Zap extends MovingObject implements ICard {

    public Zap() {
        width = 100;
        height = 100;
        imageResId_ins = R.drawable.zap_instance;
        imageResId_card = R.drawable.zap_card;
    }
}
