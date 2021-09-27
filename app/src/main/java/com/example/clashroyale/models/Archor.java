package com.example.clashroyale.models;


import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.R;
import com.example.clashroyale.controller.CardRandom;

import org.json.JSONException;
import org.json.JSONObject;

public class Archor extends CardProperties {

    //Constructor Init "Anchor" Object
    public Archor()
    {
        width = 100;
        height = 100;
        imageResId_ins = R.drawable.archor_instance;
        imageResId_card = R.drawable.archor_card;
        imageId_card = R.id.archor_card;
        cardName = "Archor";
//        getDataFromJson(GlobalConfig.jsonString_troop);
    }
}
