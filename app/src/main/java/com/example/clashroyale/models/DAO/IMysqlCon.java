package com.example.clashroyale.Models.DAO;

import com.example.clashroyale.Models.DataModels.ICard;

public interface IMysqlCon {

    void init();
    int getCountTroopData(int playerId);
    int getCountSpellData(int playerId);
    String getCardTroopData(int playerId, int sqlCount);
    String getSpecificCardData(ICard card, int playerId, int sqlCount);
    String getCardSpellData(int playerId, int sqlCount);
    void setCardDeck(int playerId, ICard[] cards);
    String[] getCardDeck(int playerId, int DeckId);
}
