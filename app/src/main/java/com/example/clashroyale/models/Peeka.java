package com.example.clashroyale.models;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.R;

public class Peeka extends CardProperties {

    public Peeka() {
        width = 140;
        height = 140;
        imageResId_ins = R.drawable.peeka_instance;
        imageResId_card = R.drawable.peeka_card;
        imageId_card = R.id.peeka_card;
        cardName = "Peeka";
        getDataFromJson_troop(GlobalConfig.jsonString_troop);
    }
}
