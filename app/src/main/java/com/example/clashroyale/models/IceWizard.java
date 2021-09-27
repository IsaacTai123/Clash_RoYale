package com.example.clashroyale.models;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.R;

public class IceWizard extends CardProperties {

    public IceWizard() {
        width = 120;
        height = 120;
        imageResId_ins = R.drawable.ice_wizard_instance;
        imageResId_card = R.drawable.ice_wizard_card;
        imageId_card = R.id.iceWizard_card;
        cardName = "IceWizard";
        getDataFromJson(GlobalConfig.jsonString_troop);
    }
}
