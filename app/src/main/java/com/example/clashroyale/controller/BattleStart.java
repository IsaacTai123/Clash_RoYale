package com.example.clashroyale.controller;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.clashroyale.MainActivity;
import com.example.clashroyale.R;
import com.example.clashroyale.models.Archor;
import com.example.clashroyale.view.InitViewElement;

public class BattleStart {

    private MainActivity mainActivity;
    private ConstraintLayout constraintLayout;
    private InitEventListener initEventListener;
    private InitViewElement initViewElement;

    // Variable
    private int screenWidth;
    private int screenHeight;


    public BattleStart(MainActivity main) {

        this.mainActivity = main;
        constraintLayout = (ConstraintLayout) main.findViewById(R.id.mainView);  //抓整個Layout
        initViewElement = new InitViewElement(main);  //Init Element to Object

        // get Screen Size
        int[] screen = getScreenSize();
        screenWidth = screen[0];
        screenHeight = screen[1];

    }

    public int[] getScreenSize() {
        WindowManager wm = mainActivity.getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        int[] screen = {screenWidth, screenHeight};

        return screen;
    }


    public void moveCard() {
        Archor archor = new Archor(initViewElement.archor);
        MoveActionLogic moveLogic = new MoveActionLogic(
                screenHeight,
                screenWidth,
                archor.getImage()
        );
        moveLogic.changePosUp();

        initEventListener = new InitEventListener(initViewElement.screenHeight, initViewElement.screenWidth, initViewElement);
        initEventListener.cardButtonEventListener();
        initEventListener.playCardInstance(constraintLayout, mainActivity);
    }
}
