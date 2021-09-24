package com.example.clashroyale.view;

import android.media.Image;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.MainActivity;
import com.example.clashroyale.R;
import com.example.clashroyale.controller.GameLogic;

public class CreateCardInstance {

    public void createCardInstance(ConstraintLayout conLay, MainActivity mainActivity, ImageView img, float clickX, float clickY)
    {
        // 新增圖片
        ImageView cardInstance = new ImageView(mainActivity);

        String[] idName = new String[8];
        idName[0] = "archor_instance";

        // 將現在選擇的卡牌對應到相應的腳色人物
        switch (img.getId())
        {
            case R.id.archor_card:
                // 建立一個archor 的實力物件 (立體圖)
                cardInstance.setImageResource(R.drawable.archor_instance);
                // TODO: 給每一個新增的實力一個獨一的ID - 方便後面操作
                break;

            case R.id.giant_card:
                // 建立一個archor 的實力物件 (立體圖)
                cardInstance.setImageResource(R.drawable.giant_instance);
                break;

//            case R.id.giant_card:
//                // 建立一個archor 的實力物件 (立體圖)
//                cardInstance.setImageResource(R.drawable.giant_instance);
//                break;
//
//            case R.id.giant_card:
//                // 建立一個archor 的實力物件 (立體圖)
//                cardInstance.setImageResource(R.drawable.giant_instance);
//                break;
//
//            case R.id.archor_card:
//                // 建立一個archor 的實力物件 (立體圖)
//                cardInstance.setImageResource(R.drawable.archor_instance);
//                break;
//
//            case R.id.giant_card:
//                // 建立一個archor 的實力物件 (立體圖)
//                cardInstance.setImageResource(R.drawable.giant_instance);
//                break;
//
//            case R.id.giant_card:
//                // 建立一個archor 的實力物件 (立體圖)
//                cardInstance.setImageResource(R.drawable.giant_instance);
//                break;
//
//            case R.id.giant_card:
//                // 建立一個archor 的實力物件 (立體圖)
//                cardInstance.setImageResource(R.drawable.giant_instance);
//                break;
        }

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(150, 150);
        params.leftMargin = (int) (clickX - 75);  //因為透過getX抓到的座標位置是滑鼠點擊的地方, 所以產生的圖片會是從左上角開始(這邊調整至中)
        params.topMargin = (int) (clickY - 75);
        params.startToStart = conLay.getId(); // 取得layout最外層的ID, 也就是activity_main.xml 的parent.
        params.topToTop = conLay.getId();

        cardInstance.setLayoutParams(params);
        conLay.addView(cardInstance);

        // 讓腳色開始移動
//        MoveCard moveCard = new MoveCard(clickX, clickY, cardInstance);
//        Thread thread = new Thread(moveCard);
//        thread.start();
        MoveAction moveAction = new MoveAction();
        GameLogic gameLogic = new GameLogic();
        gameLogic.troopCardMovedLogic(moveAction, clickX, clickY, 1, cardInstance);
    }

}

class MoveCard implements Runnable {

    private float clickX;
    private float clickY;
    private ImageView cardInstance;

    public MoveCard(float clickX, float clickY, ImageView cardInstance) {
        this.cardInstance = cardInstance;
        this.clickX = clickX;
        this.clickY = clickY;
    }

    @Override
    public void run() {
            MoveAction moveAction = new MoveAction();
            GameLogic gameLogic = new GameLogic();
            gameLogic.troopCardMovedLogic(moveAction, clickX, clickY, 1, cardInstance);
    }
}