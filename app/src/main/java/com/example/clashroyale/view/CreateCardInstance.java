package com.example.clashroyale.view;

import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.MainActivity;
import com.example.clashroyale.controller.GameLogic;
import com.example.clashroyale.models.ICard;

public class CreateCardInstance {

    /**
     * @param conLay 整個畫面的Layout
     * @param mainActivity MainActivity的實例
     * @param card 你所擁有的卡牌 ex. Archor, Giant etc...
     * @param clickX 卡排放到場上的初始位置x座標
     * @param clickY 卡排放到場上的初始位置y座標
     */
    public void createCardInstance(ConstraintLayout conLay, MainActivity mainActivity, ICard card, float clickX, float clickY)
    {
        // 新增圖片
        ImageView cardInstance = new ImageView(mainActivity);
        int id = GlobalConfig.generateIntId(6);
        cardInstance.setId(id);

        cardInstance.setImageResource(card.getImageResId_ins());

        int cardWidth = card.getWidth();
        int cardHeight = card.getHeight();
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(cardWidth, cardHeight);
        params.leftMargin = (int) (clickX - cardWidth/2);  //因為透過getX抓到的座標位置是滑鼠點擊的地方, 所以產生的圖片會是從左上角開始(這邊調整至中)
        params.topMargin = (int) (clickY - cardHeight/2);
        params.startToStart = conLay.getId(); // 取得layout最外層的ID, 也就是activity_main.xml 的parent.
        params.topToTop = conLay.getId();

        cardInstance.setLayoutParams(params);
        conLay.addView(cardInstance);

        // 讓腳色開始移動
        MoveAction moveAction = new MoveAction();
        GameLogic gameLogic = new GameLogic();
        // TODO : 要把step依照卡牌輸入
        gameLogic.startTroopCardMovedLogic(moveAction, clickX, clickY, cardInstance, card);
    }

}