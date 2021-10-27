package com.example.clashroyale.controller;

import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.clashroyale.BattleActivity;
import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.MainActivity;
import com.example.clashroyale.R;
import com.example.clashroyale.db.MysqlCon;
import com.example.clashroyale.models.Archor;
import com.example.clashroyale.models.Bowler;
import com.example.clashroyale.models.FireBall;
import com.example.clashroyale.models.Giant;
import com.example.clashroyale.models.ICard;
import com.example.clashroyale.models.IceWizard;
import com.example.clashroyale.models.Peeka;
import com.example.clashroyale.models.Wizard;
import com.example.clashroyale.models.Zap;
import com.example.clashroyale.view.InitViewElement;
import com.example.clashroyale.view.MoveAction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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
