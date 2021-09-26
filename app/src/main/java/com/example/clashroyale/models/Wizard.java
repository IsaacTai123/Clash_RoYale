package com.example.clashroyale.models;

import com.example.clashroyale.R;

public class Wizard extends MovingObject implements ICard {

    public Wizard() {
        width = 120;
        height = 120;
        imageResId_ins = R.drawable.wizard_instance;
        imageResId_card = R.drawable.wizard_card;
    }
}
