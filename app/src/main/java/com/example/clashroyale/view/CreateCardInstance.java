package com.example.clashroyale.view;

import android.media.Image;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.MainActivity;
import com.example.clashroyale.R;
import com.example.clashroyale.controller.GameLogic;
import com.example.clashroyale.models.Archor;
import com.example.clashroyale.models.ICard;

public class CreateCardInstance {

    public void createCardInstance(ConstraintLayout conLay, MainActivity mainActivity, ImageView img, float clickX, float clickY)
    {
        // 新增圖片
        ImageView cardInstance = new ImageView(mainActivity);
        int id = GlobalConfig.generateIntId(6);
        cardInstance.setId(id);

//        String[] idName = new String[8];
//        idName[0] = "archor_instance";

        // 將現在選擇的卡牌對應到相應的腳色人物
        switch (img.getId())
        {
            case R.id.archor_card:
                // 建立一個archor 的實力物件 (立體圖)
                cardInstance.setImageResource(R.drawable.archor_instance);
//                Archor archor = new Archor(cardInstance);
//                cardInstance.setImageResource(archor.getImageRedId());

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

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(100, 100);
        params.leftMargin = (int) (clickX - 50);  //因為透過getX抓到的座標位置是滑鼠點擊的地方, 所以產生的圖片會是從左上角開始(這邊調整至中)
        params.topMargin = (int) (clickY - 50);
        params.startToStart = conLay.getId(); // 取得layout最外層的ID, 也就是activity_main.xml 的parent.
        params.topToTop = conLay.getId();

        cardInstance.setLayoutParams(params);
        conLay.addView(cardInstance);

        // 讓腳色開始移動
        MoveAction moveAction = new MoveAction();
        GameLogic gameLogic = new GameLogic();
        // TODO : 要把step依照卡牌輸入
        gameLogic.startTroopCardMovedLogic(moveAction, clickX, clickY, 1, cardInstance);
    }

}