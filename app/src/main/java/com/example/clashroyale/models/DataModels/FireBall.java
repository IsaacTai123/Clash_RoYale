package com.example.clashroyale.Models.DataModels;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.R;

public class FireBall extends CardProperties {

    public FireBall() {
        width = 100;
        height = 100;
        imageResId_ins = R.drawable.fireball_instace;
        imageResId_card = R.drawable.fireball_card;
        imageId_card = R.id.fireBall_card;
        cardName = "FireBall";
        getDataFromJson_spell(GlobalConfig.jsonString_spell);
    }
}
