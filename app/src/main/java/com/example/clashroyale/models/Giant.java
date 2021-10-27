package com.example.clashroyale.models;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.R;

public class Giant extends CardProperties {

    public Giant() {
        width = 140;
        height = 140;
        imageResId_ins = R.drawable.giant_instance;
        imageResId_card = R.drawable.giant_card;
        imageId_card = R.id.giant_card;
        cardName = "Giant";
        getDataFromJson_troop(GlobalConfig.jsonString_troop);
    }
}
