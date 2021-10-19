package com.example.clashroyale.controller;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.clashroyale.models.Archor;
import com.example.clashroyale.models.Bowler;
import com.example.clashroyale.models.FireBall;
import com.example.clashroyale.models.Giant;
import com.example.clashroyale.models.ICard;
import com.example.clashroyale.models.IceWizard;
import com.example.clashroyale.models.Peeka;
import com.example.clashroyale.models.Wizard;
import com.example.clashroyale.models.Zap;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CardDeck {

    private static int currentElixir = 500;
    private ProgressBar elixirBar;
    private TextView elixirCount;
    Handler handler = new Handler();
    private int nextInt = 3;
    private ImageView[] imgArray;  //玩家手中的卡牌(type img)
    protected ICard[] cardReOrganize = new ICard[8];
    protected ArrayList<ICard> currentCard = new ArrayList<>();  //記錄當下有哪些卡片在使用者手上(記錄他的index)
    private ArrayList<Integer> currentCardIndex = new ArrayList<Integer>();  //記錄當下有哪些卡片在使用者手上(記錄他的index)
    private ICard selectedCard;
    private ImageButton selectedButton;


    public ICard[] generateCardInstance(String[] cardDeck) {

        ICard[] cards = new ICard[cardDeck.length];
        for (int i=0; i<cardDeck.length; i++) {

            cards[i] = checkCard(cardDeck[i]);
        }
        return cards;
    }

    public ICard checkCard(String cardName) {
        ICard card = null;
        switch (cardName)
        {
            case "Archor":
                card = new Archor();
                break;
            case "Giant":
                card = new Giant();
                break;
            case "Bowler":
                card = new Bowler();
                break;
            case "IceWizard":
                card = new IceWizard();
                break;
            case "Peeka":
                card = new Peeka();
                break;
            case "Wizard":
                card = new Wizard();
                break;
            case "FireBall":
                card = new FireBall();
                break;
            case "Zap":
                card = new Zap();
                break;
        }

        return card;
    }


    // 將陣列裡面的資料 隨機排序
    public void reOrganize(ICard[] cardArray) {
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

    public void setStartImageResources(ImageButton[] img) {
        for(int i=0; i<5; i++) {
            img[i].setImageResource(cardReOrganize[i].getImageResId_card());
            img[i].setId(cardReOrganize[i].getImageId_card());

            // 把當下玩家手中的四張牌紀錄起來
            if (i<4) {  //最後一個next不要
                cardReOrganize[i].setImgButton(img[i]);  //把圖片按鈕添加到icard裡面
                currentCard.add(cardReOrganize[i]);
                currentCardIndex.add(i);
            }
        }
        imgArray = img;
    }

    // 出牌之後 下一張牌要填補上來
    public void nextCard(ImageButton selectedButton) {

        // 先看看你選擇的卡牌是哪個(cardOne, cardTwo.. or cardFour)
        int selectedIndex = findIndex(currentCard, selectedButton);
        if (selectedIndex == -1) {
            Log.e("Error", "辨別卡排的位置失敗");
        }
        else {
            // 如果現有的卡牌已經有在玩家手上則跳下一個 and 或是超過7則跳回0
            do {
                nextInt ++;
                if (nextInt > 7) {
                    nextInt = 0;
                }
            }
            while (currentCardIndex.contains(nextInt));

            // 把出的牌換成next的卡牌
            selectedButton.setImageResource(cardReOrganize[nextInt].getImageResId_card());
            selectedButton.setId(cardReOrganize[nextInt].getImageId_card());
            // 也要同時更新currentCard and currentCardIndex
            cardReOrganize[nextInt].setImgButton(selectedButton);  // (跟上面一樣) 把圖片按鈕添加到icard裡面
            currentCardIndex.set(selectedIndex, nextInt);
            currentCard.set(selectedIndex, cardReOrganize[nextInt]);


            // 把next的卡牌換成再下一張
            int len = imgArray.length;
            int nextInt_2 = nextInt;
            do {
                nextInt_2++;
                if (nextInt_2 > 7) {
                    nextInt_2 = 0;
                }
            }
            while (currentCardIndex.contains(nextInt_2));

            imgArray[len-1].setImageResource(cardReOrganize[nextInt_2].getImageResId_card());
            imgArray[len-1].setId(cardReOrganize[nextInt_2].getImageId_card());
        }
    }

    public int findIndex(ArrayList<ICard> arr, ImageButton target) {
        for (int i=0; i<4; i++) {
            if (arr.get(i).getImageId_card() == target.getId()) {
                return i;
            }
        }
        return -1;
    }

    public void checkCurrentElixirForCard(int  elixir) {
        int currentElixir = elixir / 100;
        ColorMatrix matrix = new ColorMatrix();

        // 能出的四張牌中判斷當下的elixir 是否足夠你出牌
        for(ICard c : currentCard) {
            // 如果卡牌的聖水多餘當下的聖水則把圖片反灰
            if (c.getElixir() > currentElixir) {
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                c.getImgButton().setColorFilter(filter);
                c.setActivate(false);
            }
            else {
                c.getImgButton().clearColorFilter();  //把colorfilter清掉 回復原狀
                c.setActivate(true);
            }
        }
    }

    public void startElixir(ProgressBar elixirBar, TextView elixirCount) {
        this.elixirBar = elixirBar;
        this.elixirCount = elixirCount;

        // set the default progressbar 預設是5格聖水開始
        elixirBar.setProgress(currentElixir);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (currentElixir >= 1000) {
                    currentElixir += 0;
                } else {
                    currentElixir += 1;
                }
                elixirBar.setSecondaryProgress(currentElixir);
                if (currentElixir % 10 == 0) {
                    checkCurrentElixirForCard(currentElixir);  //progressbar 每跑10格就檢查一次, 把無法出牌的圖片反灰
                }
                if (currentElixir % 100 == 0) {
                    elixirBar.setProgress(currentElixir);
                    int i = currentElixir / 100;
                    handler.post(() -> {
                        elixirCount.setText(String.format("%d", i));  //更改畫面上聖水的總計
                    });
                }
            }
        }, 0, 20);
    }

    public void reduceElixir(int elixir) {
        int elixir_100 = elixir * 100; //因為現實的1在這邊是100格
        if (currentElixir > elixir_100) {
            currentElixir -= elixir_100;

            if (currentElixir > 100) {
                int quotient = currentElixir / 100;
                elixirBar.setProgress(quotient * 100);
                elixirCount.setText(String.format("%d", quotient));
            } else {
                elixirBar.setProgress(0);
                elixirCount.setText(String.format("%d", 0));
            }
        }
    }

    /**
     * @param imgV 點擊圖片按鈕傳回來的View物件<br>
     *             抓取當下玩家點選的圖片, 並記錄起來
     *             儲存玩家點選的卡牌(當玩家點擊場地時, 需使用這個來看該放置哪一張卡牌)
     */
    public void currentSelectedCard(View imgV)
    {
        for (ICard c : currentCard) {
            for (int i=0; i<4; i++) {
                if (c.getImageId_card() == imgV.getId()) {
                    selectedCard = c;
                    selectedButton = c.getImgButton();
                }
            }
        }
    }

    /**
     * 當玩家把牌放置到場上後, 呼叫這個method來清空selectedCard. 因為選一次牌只能出一次
     */
    public void cleanSelectedCard() {this.selectedCard = null; }

    public ICard getSelectedCard() { return selectedCard; }
    public ImageButton getSelectedButton() { return selectedButton; }
}
