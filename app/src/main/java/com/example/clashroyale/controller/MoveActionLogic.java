package com.example.clashroyale.controller;

import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

public class MoveActionLogic {

    private final int screenWidth;
    private final int screenHeight;
    private float imgX;
    private float imgY;
    private ImageView img;
    private final Timer timer = new Timer();
    private Handler handler = new Handler();

    public MoveActionLogic(int screenHeight, int screenWidth, ImageView img)
    {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.imgX = img.getX();
        this.imgY = img.getY();
        this.img = img;


    }

    // 將物件向上移動
    public void changePosUp()
    {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post((Runnable) () -> {
                    // Up
                    imgY -= 1;
                    if (img.getY() < 0)
                    {
                        timer.cancel();
                    }
                    img.setY(imgY);
                });
            }
        }, 0, 20);
    }

    // TODO: 製作移動的規則 (往什麼方向, 斜角? 或是往敵人走去? 遇到敵人則停下攻擊)
    public void movingUp() {

    }
}
