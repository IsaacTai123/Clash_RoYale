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
import com.example.clashroyale.view.InitViewElement;
import com.example.clashroyale.view.MoveAction;

import java.util.concurrent.TimeUnit;

public class BattleStart {

    private MainActivity mainActivity;
    private ConstraintLayout constraintLayout;
    private InitEventListener initEventListener;
    private InitViewElement initViewElement;


    public BattleStart(MainActivity main) {

        this.mainActivity = main;
        constraintLayout = (ConstraintLayout) main.findViewById(R.id.mainView);  //抓整個Layout
        initViewElement = new InitViewElement(main);  //Init Element to Object

        GlobalConfig.init(main);
//        Log.e("screen", ""+GlobalConfig.screenWidth);
//        Log.e("screen", ""+GlobalConfig.pathOne_Left);

    }

    public void moveCard() throws InterruptedException {
        Archor archor = new Archor(initViewElement.archor);
//        MoveAction moveAction = new MoveAction(
//                GlobalConfig.screenHeight,
//                GlobalConfig.screenWidth,
//                archor.getImage()
//        );
//        moveLogic.movingUp(1);
//        moveLogic.movingLeft(1, 270);

//        GameLogic gameLogic = new GameLogic(screenWidth, screenHeight);
//        gameLogic.movingUp_logic(moveAction, 1, 270, archor.getImage());
//        gameLogic.troopCardMovedLogic(moveAction, );

        initEventListener = new InitEventListener(initViewElement);
        initEventListener.cardButtonEventListener();
        initEventListener.playCardInstance(constraintLayout, mainActivity);
    }
}
