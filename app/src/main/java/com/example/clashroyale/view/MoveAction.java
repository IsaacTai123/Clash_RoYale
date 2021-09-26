package com.example.clashroyale.view;

import android.util.Log;
import android.widget.ImageView;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import android.os.Handler;

import com.example.clashroyale.Enums;
import com.example.clashroyale.GlobalConfig;

public class MoveAction {

    public MoveAction()
    {
    }

    // TODO: 看來只要在這邊呼叫timer.cancel 所有的動作都會停下 (因為我用的timer都是同一個物件, 如要分開就改在執行的時候再new)

    /**
     * @param newPosX 圖片新的座標x軸位置
     * @param newPosY 圖片新的座標y軸位置
     * @param img  新的圖片實例
     */
    public void startMoving(int newPosX, int newPosY, ImageView img) {
        img.setX(newPosX);
        img.setY(newPosY);

//        new Thread(() -> GlobalConfig.jedisCon.storeXYToRedis(img, newPosX, newPosY)).start();
        String imgUnitId = String.valueOf(img.getId());
        GlobalConfig.storeImgUnitXYData(imgUnitId, newPosX, newPosY);
        Log.e("imgPostiong", "Image Position : " + imgUnitId + GlobalConfig.imgsPosition.get(imgUnitId));
    }
}
