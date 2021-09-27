package com.example.clashroyale.models;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.R;

public class Zap extends CardProperties {

    public Zap() {
        width = 100;
        height = 100;
        imageResId_ins = R.drawable.zap_instance;
        imageResId_card = R.drawable.zap_card;
        imageId_card = R.id.zap_card;
        cardName = "zap";
//        getDataFromJson(GlobalConfig.jsonString_troop);
    }
}
