package com.example.clashroyale.Models.Services;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.clashroyale.Controller.BattleActivity;
import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.R;
import com.example.clashroyale.Models.DataModels.ICard;

public class BattleStart {

    private final BattleActivity battleActivity;
    private final ConstraintLayout fragmentPlayground;
    private final InitViewElement initViewElement;
    private final CardDeck cardDeck;


    /**
     * @param main MainActivity 的實例
     *             進入Battle的程式進入點
     */
    public BattleStart(BattleActivity main) throws InterruptedException {

        // 對戰開始畫面參數的初始化
        this.battleActivity = main;
        fragmentPlayground = main.findViewById(R.id.playground_activity);

        Thread t = new Thread(() -> {
            GlobalConfig.initFragment_card(1);
        });
        t.start();
        t.join();

        // 因為去資料庫讀資料需要點時間, 而程式還在繼續 所以資料還沒讀出來他就已經跑下面的程式, 所以帶入會是null繼而得到錯誤訊息
        cardDeck = new CardDeck();
        ICard[] cardsInstance = cardDeck.generateCardInstance(GlobalConfig.cardArray_8);
        cardDeck.reOrganize(cardsInstance);
        initViewElement = new InitViewElement(main, cardDeck);  //Init Element to Object
    }

    /**
     * 把EventListener 做初始化
     */
    public void addEventListener() throws InterruptedException {
        GlobalConfig.initActivity(fragmentPlayground);  //計算遊戲場地的路徑

        InitEventListener initEventListener = new InitEventListener(initViewElement, cardDeck);
        initEventListener.cardButtonEventListener();
        initEventListener.playCardInstance(fragmentPlayground, battleActivity);

    }
}
