package com.example.clashroyale.controller;

import android.provider.Settings;

import com.example.clashroyale.Enums;
import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.models.Archor;
import com.example.clashroyale.models.Bowler;
import com.example.clashroyale.models.FireBall;
import com.example.clashroyale.models.Giant;
import com.example.clashroyale.models.ICard;
import com.example.clashroyale.models.IceWizard;
import com.example.clashroyale.models.Peeka;
import com.example.clashroyale.models.Wizard;
import com.example.clashroyale.models.Zap;

import java.lang.reflect.WildcardType;
import java.util.Locale;

public class CardDeck {


    public void setCardDeck() {

    }

    public ICard[] generateCardInstance(String[] cardDeck) {

        ICard[] cards = new ICard[cardDeck.length];
        for (int i=0; i<cardDeck.length; i++) {

            cards[i] = checkCard(cardDeck[i]);
        }
        return cards;
    }

    public ICard checkCard(String cardName) {
        ICard card = null;
        switch (cardName)
        {
            case "Archor":
                card = new Archor();
//                card.initTroopData();
                break;
            case "Giant":
                card = new Giant();
//                card.initTroopData();
                break;
            case "Bowler":
                card = new Bowler();
//                card.initTroopData();
                break;
            case "IceWizard":
                card = new IceWizard();
//                card.initTroopData();
                break;
            case "Peeka":
                card = new Peeka();
//                card.initTroopData();
                break;
            case "Wizard":
                card = new Wizard();
//                card.initTroopData();
                break;
            case "FireBall":
                card = new FireBall();
//                card.initSpellData();
                break;
            case "Zap":
                card = new Zap();
//                card.initSpellData();
                break;
        }

        return card;
    }
}
