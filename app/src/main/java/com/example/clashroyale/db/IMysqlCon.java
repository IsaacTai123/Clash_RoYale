package com.example.clashroyale.db;

import com.example.clashroyale.models.ICard;

public interface IMysqlCon {

    String getCardTroopData(int playerId);
    String getCardSpellData(int playerId);
    String getSpecificCardData(ICard card, int playerId);
    void setCardDeck(int playerId, ICard[] cards);
    String[] getCardDeck(int playerId, int DeckId);
}
