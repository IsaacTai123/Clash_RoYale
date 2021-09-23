package com.example.clashroyale.controller;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.clashroyale.MainActivity;
import com.example.clashroyale.R;
import com.example.clashroyale.view.InitViewElement;

public class InitEventListener {
    private ImageButton card_archor;
    private ImageButton card_hogrider;
    private int screenHeight;
    private int screenWidth;
    final float[] clickX = new float[1];
    final float[] clickY = new float[1];
    GameLogic methods;

    public InitEventListener(int screenHeight, int screenWidth, InitViewElement ive) {
        this.card_archor = ive.card_archor;
        this.card_hogrider = ive.card_hogrider;
        methods = new GameLogic();
    }

    public void cardButtonEventListener() {
        View.OnClickListener clickListener = v1 -> {
            ImageView currentImageView = null;
            methods.currentCardSelected(currentImageView);

            switch (v1.getId())
            {
                case R.id.archor_card:
                    Log.e("card", "this is archor");
                    currentImageView = v1.findViewById(R.id.archor_card);
                    break;

                case R.id.hogRider_card:
                    Log.e("card", "this is hog");
                    currentImageView = v1.findViewById(R.id.hogRider_card);
                    break;
            }

            //TODO : 把current ImageView 丟給currentCardSelected
            if (currentImageView != null) {
                methods.currentCardSelected(currentImageView);
                ImageView v = methods.getSelectedCard();
                Log.e("selectCard", ""+v.getTag());
            }
        };

        card_archor.setOnClickListener(clickListener);
        card_hogrider.setOnClickListener(clickListener);
    }

    // 點擊螢幕召喚卡牌
    public void playCardInstance(ConstraintLayout mainView, MainActivity mainActivity) {

        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageView currentImage = methods.selectedCard;

                if (currentImage != null)
                {
                    clickX[0] = event.getX();
                    clickY[0] = event.getY();
                    methods.createCardInstance(
                            mainView,
                            mainActivity,
                            methods.getSelectedCard(),
                            clickX[0],
                            clickY[0]
                    );
                }
//                Log.e("click", "Left: array: "+event.getX());
//                Log.e("click", "Top: array: "+event.getY());
                return false;
            }
        };
        mainView.setOnTouchListener(touchListener);
    }
}

