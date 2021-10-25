package com.example.clashroyale.Models.DataModels;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.R;

public class Bowler extends CardProperties {

    public Bowler() {
        width = 120;
        height = 120;
        imageResId_ins = R.drawable.bowler_instance;
        imageResId_card = R.drawable.bowler_card;
        imageId_card = R.id.bowler_card;
        cardName = "Bowler";
        getDataFromJson_troop(GlobalConfig.jsonString_troop);
    }
}
