package com.example.clashroyale.models;

import com.example.clashroyale.R;

public class Giant extends MovingObject implements ICard {

    public Giant() {
        width = 140;
        height = 140;
        imageResId_ins = R.drawable.giant_instance;
        imageResId_card = R.drawable.giant_card;

    }
}
