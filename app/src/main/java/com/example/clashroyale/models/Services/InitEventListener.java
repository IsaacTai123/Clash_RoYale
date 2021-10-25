package com.example.clashroyale.Models.Services;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.clashroyale.Controller.BattleActivity;
import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.Models.DataModels.ICard;
import com.example.clashroyale.Models.Services.CardDeck;
import com.example.clashroyale.Models.Services.InitViewElement;
import com.example.clashroyale.View.CreateCardInstance;

public class InitEventListener {

    private ImageButton cardOne;
    private ImageButton cardTwo;
    private ImageButton cardThree;
    private ImageButton cardFour;

    final float[] clickX = new float[1];
    final float[] clickY = new float[1];
//    GameLogic gameLogic = new GameLogic();
    CreateCardInstance createCard;
    CardDeck cardDeck;

    public InitEventListener(InitViewElement ive, CardDeck cardDeck) {
        createCard = new CreateCardInstance();
        this.cardDeck = cardDeck;
        this.cardOne = ive.cardOne;
        this.cardTwo = ive.cardTwo;
        this.cardThree = ive.cardThree;
        this.cardFour = ive.cardFour;
    }

    public void cardButtonEventListener() {
        View.OnClickListener clickListener = v1 -> {
            cardDeck.currentSelectedCard(v1);
        };

        cardOne.setOnClickListener(clickListener);
        cardTwo.setOnClickListener(clickListener);
        cardThree.setOnClickListener(clickListener);
        cardFour.setOnClickListener(clickListener);
    }

    // 點擊螢幕召喚卡牌
    public void playCardInstance(@NonNull ConstraintLayout playgroundView, BattleActivity battleActivity) {

        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ICard currentSelectedCard = cardDeck.getSelectedCard();
                clickX[0] = event.getX();
                clickY[0] = event.getY();

                // TODO: 判斷若當前聖水不足則不能出牌
                if (currentSelectedCard != null && currentSelectedCard.getActivate() && clickY[0] > GlobalConfig.playLimit )
                {
                    // 扣除這張卡牌消耗的聖水
                    cardDeck.reduceElixir(currentSelectedCard.getElixir());

                    // 創建卡牌腳色
                    createCard.createCardInstance(
                            playgroundView,
                            battleActivity,
                            currentSelectedCard,
                            clickX[0],
                            clickY[0]
                    );

                    // 選擇的牌只能出一次, 所以建立完之後就清掉
                    cardDeck.cleanSelectedCard();
                    // 清掉之後還要把下一張牌填到這個空位
                    cardDeck.nextCard(cardDeck.getSelectedButton());
                }
                Log.e("click", "Left: array: "+event.getX());
                Log.e("click", "Top: array: "+event.getY());
                return false;
            }
        };
        playgroundView.setOnTouchListener(touchListener);
    }
}

