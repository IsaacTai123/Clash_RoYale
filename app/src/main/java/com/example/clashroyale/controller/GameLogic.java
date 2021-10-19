package com.example.clashroyale.controller;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.os.Handler;

import com.example.clashroyale.Enums;
import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.R;
import com.example.clashroyale.models.Archor;
import com.example.clashroyale.models.FireBall;
import com.example.clashroyale.models.Giant;
import com.example.clashroyale.models.ICard;
import com.example.clashroyale.models.IceWizard;
import com.example.clashroyale.models.Peeka;
import com.example.clashroyale.models.Bowler;
import com.example.clashroyale.models.Wizard;
import com.example.clashroyale.models.Zap;
import com.example.clashroyale.view.MoveAction;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;


public class GameLogic {

    Timer timer = new Timer();
    Handler handler = new Handler();

    // variable
    private MoveAction moveAction;
    private float clickX;
    private float clickY;
    private int step;
    private ImageView imgUnit;
    private AtomicInteger[] imgXY;
    private int imgWidth, imgHeight;
    boolean condition = true;
    private boolean cardIsAlive;
    private int[] imgUnitLeft_up, imgUnitLeft_down, imgUnitRight_up, imgUnitRight_down;  //分別對應圖形的四點座標

    /**
     * @param moveAction 設定圖片新的座標位置
     * @param clickX 卡排放到場上的初始位置x座標
     * @param clickY 卡排放到場上的初始位置y座標
     * @param img 新產生出的卡牌實例<br>
     *            負責場面上卡牌的移動
     */
    // 負責給別人呼叫( 物件移動的進入點)
    public void startTroopCardMovedLogic(MoveAction moveAction, float clickX, float clickY, ImageView img, ICard card) {
        this.moveAction = moveAction;
        this.clickX = clickX;
        this.clickY = clickY;
        this.imgUnit = img;
        this.imgWidth = img.getWidth();
        this.imgHeight = img.getHeight();
        imgXY = getImageValue(img);

        // 用來轉換卡牌的速度
        String speed = card.getSpeed().toUpperCase(Locale.ROOT);
        if (speed.equals("fast".toUpperCase(Locale.ROOT))) {
            this.step = 3;
        }
        else if (speed.equals("slow".toUpperCase(Locale.ROOT))) {
            this.step = 1;
        }
        else if (speed.equals("medium".toUpperCase(Locale.ROOT))) {
            this.step = 2;
        }

        // 讓移動的動作自成一個支線
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 這邊必須要使用handler 來觸發你的thread (如果不加會出問題)
                handler.post(() -> troopCardMovedLogic());
            }
        }, 0, 40);
    }

    /**
     * 卡牌移動的邏輯
     */
    // TODO: 製作移動的規則 (往什麼方向, 斜角? 或是往敵人走去? 遇到敵人則停下攻擊)
    public void troopCardMovedLogic() {
        // 依照放置的位置判斷行走的方向
        // 若腳色放置的x軸座標是在第1塊的時候要讓他往右上移動 (直到碰到路徑)
        if (clickX < GlobalConfig.pathOne_Left) {
            movingRightUp_logic(Enums.PATH_ONE);
        }
        else if (clickX > GlobalConfig.pathOne_Right && clickX < GlobalConfig.pathMiddle) {
            movingLeftUp_logic(Enums.PATH_ONE);
        }
        else if (clickX > GlobalConfig.pathMiddle && clickX < GlobalConfig.pathTwo_Left) {
            movingRightUp_logic(Enums.PATH_TWO);
        }
        else if (clickX > GlobalConfig.pathTwo_Right)  {
            movingLeftUp_logic(Enums.PATH_TWO);
        }
        else {
            movingUp_logic();
        }

        if (!condition) {
            movingUp_logic();
        }
    }

    /**
     * 負責偵測敵人卡牌的座標位置, 以及我方卡牌的位置, 防止重疊
     */
    public void detectCollision() {
        // 當物件在移動時 依照自己的攻擊範圍來查看 周圍的物件
        calcFourCoordinate();

        // range (攻擊範圍)
        // 若是四個
//        if ()

    }

    /**
     * 計算圖片四個角的座標位置
     */
    public void calcFourCoordinate() {
        int x = imgXY[0].get();
        int y = imgXY[1].get();

        this.imgUnitLeft_up = new int[] {x, y};  //左上角
        this.imgUnitLeft_down = new int[] {x, y + imgHeight};  //左下角
        this.imgUnitRight_up = new int[] {x + imgWidth, y};  //右上角
        this.imgUnitRight_down = new int[] {x + imgWidth, y + imgHeight};  //右下角
    }

    public void movingUp_logic() {
        int x = imgXY[0].get();
        int y = imgXY[1].getAndAdd(-step);

        // 防止腳色超出螢幕 && 還有當移動到指定路徑的時候就只要往上走就好
        if (imgUnit.getY() < 0) {
            timer.cancel();
            new Thread(() -> GlobalConfig.jedisCon.storeXYToRedis(GlobalConfig.imgsPosition)).start();
        }
        moveAction.startMoving(x, y, imgUnit);
    }

    public void movingLeft_logic() {
        int x = imgXY[0].getAndAdd(-step);
        int y = imgXY[1].get();

        // 防止腳色超出螢幕 && 還有當移動到指定路徑的時候就只要往上走就好
        if (imgUnit.getX() < 0) {
            timer.cancel();
        }
        moveAction.startMoving(x, y, imgUnit);
    }

    /**
     * @return 用來判斷是否該停止這個方向的動作
     * <br>
     * imgLeftFrame : 這個是圖片左邊的邊線x軸位置.當圖片左邊x軸碰到路徑的時候就停止動作
     */
    public void movingRightUp_logic(Enums path) {

        if (condition) {
            int x = imgXY[0].getAndAdd(step); //x 座標
            int y = imgXY[1].getAndAdd(-step); //y 座標
            int imgRightFrame = (int) (imgUnit.getX() + imgUnit.getWidth() / 2);

            switch (path) {
                case PATH_ONE:
                    // 防止腳色超出螢幕 && 當圖片右邊邊界碰到路徑1 時就停止
                    if (imgRightFrame >= GlobalConfig.pathOne_Left) {
                        condition = false; // 如過碰到回傳false 停止這部分
                    }
                    break;
                case PATH_TWO:
                    // 防止腳色超出螢幕 && 當圖片右邊邊界碰到路徑2 時就停止
                    if (imgRightFrame >= GlobalConfig.pathTwo_Left) {
                        condition = false;
                    }
                    break;
            }
            moveAction.startMoving(x, y, imgUnit);

            // 把圖片當下的位置座標存入Redis
        }
    }

    /**
     * @return 用來判斷是否該停止這個方向的動作
     * <br>
     * imgLeftFrame : 這個是圖片左邊的邊線x軸位置.當圖片左邊x軸碰到路徑的時候就停止動作
     */
    public void movingLeftUp_logic(Enums path) {

        // 只有true 的時後才執行
        if (condition) {
            int x = imgXY[0].getAndAdd(-step); //x 座標
            int y = imgXY[1].getAndAdd(-step); //y 座標
            int imgLeftFrame = (int) imgUnit.getX() + imgUnit.getWidth() / 2;

            switch (path) {
                case PATH_ONE:
                    // 防止腳色超出螢幕 && 當圖片左邊邊界碰到路徑1 時就停止
                    if (imgLeftFrame <= GlobalConfig.pathOne_Right) {
                        condition = false; //如果是碰到了就回傳false
                    }
                    break;
                case PATH_TWO:
                    // 防止腳色超出螢幕 && 當圖片左邊邊界碰到路徑2 時就停止
                    if (imgLeftFrame <= GlobalConfig.pathTwo_Right) {
                        condition = false;
//                        timer.cancel();
                    }
                    break;
            }
            moveAction.startMoving(x, y, imgUnit);
            // 把圖片當下的位置座標存入Redis
        }
    }

    public AtomicInteger[] getImageValue(ImageView img) {
        AtomicInteger imgX = new AtomicInteger();
        AtomicInteger imgY = new AtomicInteger();
        img.post(() -> {
            imgX.set((int) img.getX());
            imgY.set((int) img.getY());
            Log.e("getX", ""+img.getX());
        });

        AtomicInteger[] res = {imgX, imgY};

        return res;
    }


}
