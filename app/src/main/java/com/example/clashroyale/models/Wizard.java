package com.example.clashroyale.models;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.R;

public class Wizard extends CardProperties {

    public Wizard() {
        width = 120;
        height = 120;
        imageResId_ins = R.drawable.wizard_instance;
        imageResId_card = R.drawable.wizard_card;
        imageId_card = R.id.wizard_card;
        cardName = "Wizard";
        getDataFromJson(GlobalConfig.jsonString_troop);
    }
}
