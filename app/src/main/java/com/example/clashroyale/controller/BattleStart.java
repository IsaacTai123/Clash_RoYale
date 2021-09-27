package com.example.clashroyale.controller;

import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.MainActivity;
import com.example.clashroyale.R;
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

import java.util.concurrent.TimeUnit;

public class BattleStart {

    private final MainActivity mainActivity;
    private final ConstraintLayout constraintLayout;
    private final InitViewElement initViewElement;
    private final CardRandom cardRn;


    /**
     * @param main MainActivity 的實例
     *             進入Battle的程式進入點
     */
    public BattleStart(MainActivity main){

        // 競賽開始畫面參數的初始化
        this.mainActivity = main;
        constraintLayout = main.findViewById(R.id.mainView);  //抓整個Layout

        GlobalConfig.init(main, 1);
        cardRn = new CardRandom();
        ICard[] cards = GlobalConfig.cardsInstance;
        cardRn.reOrganize(cards);
        initViewElement = new InitViewElement(main, cardRn);  //Init Element to Object
    }

    /**
     * 把EventListener 做初始化
     */
    public void addEventListener() throws InterruptedException {
        InitEventListener initEventListener = new InitEventListener(initViewElement, cardRn);
        initEventListener.cardButtonEventListener();
        initEventListener.playCardInstance(constraintLayout, mainActivity);
    }
}
