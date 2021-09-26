package com.example.clashroyale.models;

import com.example.clashroyale.R;

public class Bowler extends MovingObject implements ICard {

    public Bowler() {
        width = 120;
        height = 120;
        imageResId_ins = R.drawable.bowler_instance;
        imageResId_card = R.drawable.bowler_card;
    }
}
