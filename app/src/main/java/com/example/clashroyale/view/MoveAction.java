package com.example.clashroyale.view;

import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import android.os.Handler;

import com.example.clashroyale.Enums;

public class MoveAction {


//    private float imgX;
//    private float imgY;
//    private ImageView img;

    private final Timer timer = new Timer();
    private Handler handler = new Handler();

    public MoveAction()
    {
//        this.imgX = img.getX();
//        this.imgY = img.getY();
//        this.img = img;
    }

    // 將物件向上移動
//    public void movingUp(int step) {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
////                handler.post(() -> {
//                    imgY -= step;
//
//                    // 防止腳色超出螢幕
//                    if (img.getY() < 0) {
//                        timer.cancel();
//                    }
//                    img.setY(imgY);
////                });
//            }
//        }, 0 , 20);
//    }
//
//    /**
//     * @param step 腳色移動的速度 (也就是一步的距離)
//     * @param pathX 場地固定行走路線的x座標
//     */
//    public void movingRight(int step, int pathX) {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(() -> {
//
//                    // 腳色圖片的右邊邊界x座標
//                    int rightFrame = (int) (img.getX() + img.getWidth());
//                    imgX += step;
//
//                    // 防止腳色超出螢幕 && 還有當移動到指定路徑的時候就只要往上走就好
//                    if (rightFrame > screenWidth || rightFrame > pathX) {
//                        timer.cancel();
//                    }
//                    img.setX(imgX);
//                });
//            }
//        }, 0 , 20);
//    }
//
//    public void movingLeft(int step, int pathX) {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
////                handler.post(() -> {
//
//                    // 腳色圖片的右邊邊界y座標
//                    int leftFrame = (int) img.getX();
//                    imgX -= step;
//
//                    // 防止腳色超出螢幕 && 還有當移動到指定路徑的時候就只要往上走就好
//                    if (img.getX() < 0 || leftFrame < pathX) {
//                        timer.cancel();
//                    }
//                    img.setX(imgX);
////                });
//            }
//        }, 0 , 20);
//    }
//
//    public void movingDown(int step) {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(() -> {
//                    imgY += step;
//
//                    // 防止腳色超出螢幕
//                    if (img.getX() < 0) {
//                        timer.cancel();
//                    }
//                    img.setY(imgY);
//                });
//            }
//        }, 0 , 20);
//    }
//
//    public void stopMoving() {
//        timer.cancel();
//    }

    // TODO: 看來只要在這邊呼叫timer.cancel 所有的動作都會停下 (因為我用的timer都是同一個物件, 如要分開就改在執行的時候再new)

    public void startMoving(Enums direction, int newPosX, int newPosY, ImageView img) {

//        switch (direction)
//        {
//            case LEFT:
//                img.setX(newPosX);
//                break;
//            case RIGHT:
//                img.setX(newPosX);
//                break;
//            case UP:
//                img.setY(newPosY);
//                break;
//            case DOWN:
//                img.setY(newPosY);
//                break;
//            case RIGHT_UP:
//                img.setX(newPosX);
//                img.setY(newPosY);
//                break;
//            case RIGHT_DOWN:
//                img.setX(newPosX);
//                img.setY(newPosY);
//                break;
//            case LEFT_UP:
//                img.setX(newPosX);
//                img.setY(newPosY);
//                break;
//            case LEFT_DOWN:
//                img.setX(newPosX);
//                img.setY(newPosY);
//                break;
//        }

        if (direction.equals(Enums.LEFT) || direction.equals(Enums.RIGHT)) {
            img.setX(newPosX);
        }
        else if (direction.equals(Enums.UP) || direction.equals(Enums.DOWN)) {
            img.setY(newPosY);
        }
        else {
            img.setX(newPosX);
            img.setY(newPosY);
        }
    }

}
