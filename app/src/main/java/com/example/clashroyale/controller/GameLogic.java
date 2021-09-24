package com.example.clashroyale.controller;

import android.util.Log;
import android.widget.ImageView;
import android.os.Handler;

import com.example.clashroyale.Enums;
import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.view.MoveAction;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;


public class GameLogic {

    ImageView selectedCard;
    Timer timer = new Timer();
    Handler handler = new Handler();

    // variable
    private MoveAction moveAction;
    private float clickX;
    private float clickY;
    private int step;
    private ImageView img;
    private AtomicInteger[] imgXY;

    public GameLogic() {
    }

    // 儲存玩家點選的卡牌( 下次點選時需使用這個來看該放置哪一張卡牌)
    public void currentCardSelected(ImageView imgB)
    {
        this.selectedCard = imgB;
    }

    public ImageView getSelectedCard() { return selectedCard; }

    public void startTroopCardMovedLogic(MoveAction moveAction, float clickX, float clickY, int step, ImageView img) {
        this.moveAction = moveAction;
        this.clickX = clickX;
        this.clickY = clickY;
        this.step = step;
        this.img = img;
        imgXY = getImageValue(img);


        // 讓移動的動作自成一個支線
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 這邊必須要使用handler 來觸發你的thread (如果不加會出問題)
                handler.post(() -> troopCardMovedLogic());
            }
        }, 0, 20);
    }

    // TODO: 製作移動的規則 (往什麼方向, 斜角? 或是往敵人走去? 遇到敵人則停下攻擊)
    public void troopCardMovedLogic() {

//        AtomicInteger[] imgXY = getImageValue(img);

        // 依照放置的位置判斷行走的方向
        // 若腳色放置的x軸座標是在第1塊的時候要讓他往右上移動 (直到碰到路徑)
        if (clickX < GlobalConfig.pathOne_Left) {
            boolean cond = movingRightUp_logic();
            if (!cond) {
                movingUp_logic();
            }
        }

        if (clickX > GlobalConfig.pathOne_Right && clickX < GlobalConfig.pathMiddle) {
            boolean cond = movingLeftUp_logic();
            if (!cond) {
                movingUp_logic();
            }
        }
;
    }

    public void movingUp_logic() {
//        Timer timer = new Timer();  //每次呼叫都建立一個新的timer 物件, 用來負責各個腳色的移動
//        Handler handler = new Handler();

//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
                int x = imgXY[0].get();
                int y = imgXY[1].getAndAdd(-step);

//                imgX -= step;  //y座標
                    // 防止腳色超出螢幕 && 還有當移動到指定路徑的時候就只要往上走就好
                if (img.getY() < 0) {
                    timer.cancel();
                }
                moveAction.startMoving(Enums.UP, x, y, img);
//            }
//        }, 0, 20);
    }

    public void movingLeft_logic() {
//        Timer timer = new Timer();  //每次呼叫都建立一個新的timer 物件, 用來負責各個腳色的移動
//        Handler handler = new Handler();

//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
                int x = imgXY[0].getAndAdd(-step);
                int y = imgXY[1].get();

//                imgXY[0] -= step; //x 座標
                    // 防止腳色超出螢幕 && 還有當移動到指定路徑的時候就只要往上走就好
                if (img.getX() < 0) {
                    timer.cancel();
                }
                moveAction.startMoving(Enums.LEFT, x, y, img);
//            }
//        }, 0, 20);
    }

    /**
     * @return 用來判斷是否該停止這個方向的動作
     * <br>
     * imgLeftFrame : 這個是圖片左邊的邊線x軸位置.當圖片左邊x軸碰到路徑的時候就停止動作
     */
    public boolean movingRightUp_logic() {
        boolean[] condition = {true};
//        Timer timer = new Timer();  //每次呼叫都建立一個新的timer 物件, 用來負責各個腳色的移動
//        Handler handler = new Handler();

//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
                int imgRightFrame = (int) (img.getX() + img.getWidth()/2);
                int x = imgXY[0].getAndAdd(step); //x 座標
                int y = imgXY[1].getAndAdd(-step); //y 座標

                    // 防止腳色超出螢幕 && 還有當移動到指定路徑的時候就只要往上走就好
                if (imgRightFrame >= GlobalConfig.pathOne_Left) {
                    condition[0] = false;
                    timer.cancel();
                }
                moveAction.startMoving(Enums.RIGHT_UP, x, y, img);
//            }
//        }, 0, 20);
        return condition[0];
    }

    /**
     * @return 用來判斷是否該停止這個方向的動作
     * <br>
     * imgLeftFrame : 這個是圖片左邊的邊線x軸位置.當圖片左邊x軸碰到路徑的時候就停止動作
     */
    public boolean movingLeftUp_logic() {
        boolean[] condition = {true};
//        Timer timer = new Timer();  //每次呼叫都建立一個新的timer 物件, 用來負責各個腳色的移動
//        Handler handler = new Handler();

//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
                int imgLeftFrame = (int) (img.getX() + img.getWidth()/2);
                int x = imgXY[0].getAndAdd(-step); //x 座標
                int y = imgXY[1].getAndAdd(-step); //y 座標

                // 防止腳色超出螢幕 && 當圖片左邊邊界碰到路徑1 時就停止
                if (imgLeftFrame <= GlobalConfig.pathOne_Right) {
                    condition[0] = false;
                    timer.cancel();
                }
                moveAction.startMoving(Enums.LEFT_UP, x, y, img);
//            }
//        }, 0, 20);
        return condition[0];
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
