package com.example.clashroyale.controller;

import android.widget.ImageButton;
import android.widget.ImageView;

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

import java.util.Random;

public class CardRandom {

    private int nextInt = 4;
//    private int[][] cardReOrganize = new int[8][2];
    private ICard[] cardReOrganize;
    private ImageView[] imgArray;

    // 將陣列裡面的資料 隨機排序
    public void reOrganize(ICard[] cardArray) {
//        Archor archor = new Archor();
//        Giant giant = new Giant();
//        FireBall fireBall = new FireBall();
//        IceWizard iceWizard = new IceWizard();
//        Peeka peeka = new Peeka();
//        Bowler bowler = new Bowler();
//        Wizard wizard = new Wizard();
//        Zap zap = new Zap();
//
//       // archor, giant, fireball, iceWizard, knight, prince, wizard, zap
//        int[][] cardArray = {
//                {archor.getImageResId_card(), R.id.archor_card},
//                {giant.getImageResId_card(), R.id.giant_card},
//                {fireBall.getImageResId_card(), R.id.fireBall_card},
//                {iceWizard.getImageResId_card(), R.id.iceWizard_card},
//                {peeka.getImageResId_card(), R.id.peeka_card},
//                {bowler.getImageResId_card(), R.id.bowler_card},
//                {wizard.getImageResId_card(), R.id.wizard_card},
//                {zap.getImageResId_card(), R.id.zap_card}
//        };
        Random random = new Random();

        for (int i=7; i > 0; i--) {

            int s = random.nextInt(i+1);

            // Swap array[i] with the element at random index

            ICard temp = cardArray[i];
            cardArray[i] = cardArray[s];
            cardArray[s] = temp;
        }
        cardReOrganize = cardArray;
    }

    public void setStartImageResources(ImageView[] img) {
        for(int i=0; i<5; i++) {
            int id = GlobalConfig.generateIntId(6);
            img[i].setImageResource(cardReOrganize[i].getImageResId_card());
            img[i].setId(cardReOrganize[i].getImageId_card());
        }
        imgArray = img;
    }

    // 出牌之後 下一張牌要填補上來
    public void nextCard(ImageButton selectedButton) {

        if (nextInt > 7) nextInt = 0;
        // 把出的牌換成next的卡牌
        selectedButton.setImageResource(cardReOrganize[nextInt].getImageResId_card());
        selectedButton.setId(cardReOrganize[nextInt].getImageId_card());

        // 把next的卡牌換成再下一張
        int len = imgArray.length;
        int cardIndex = nextInt + 1;
        if (cardIndex > 7) cardIndex = 0;
        imgArray[len-1].setImageResource(cardReOrganize[cardIndex].getImageResId_card());
        imgArray[len-1].setId(cardReOrganize[cardIndex].getImageId_card());
        nextInt++;
    }
}
