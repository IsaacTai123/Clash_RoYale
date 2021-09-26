package com.example.clashroyale.models;

import com.example.clashroyale.R;

public class FireBall extends MovingObject implements ICard {

    public FireBall() {
        width = 100;
        height = 100;
        imageResId_ins = R.drawable.fireball_instace;
        imageResId_card = R.drawable.fireball_card;
    }
}
