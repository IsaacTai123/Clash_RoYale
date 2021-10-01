package com.example.clashroyale.view;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.MainActivity;
import com.example.clashroyale.R;
import com.example.clashroyale.controller.CardRandom;
import com.example.clashroyale.controller.TimerCountDown;
import com.example.clashroyale.models.ICard;

import org.w3c.dom.Text;

import java.util.Timer;

public class InitViewElement {

    public ImageButton cardOne, cardTwo, cardThree, cardFour, nextCard;

    public InitViewElement(MainActivity main, CardRandom cardRn)
    {
        TextView timerText = main.findViewById(R.id.timerClock);
        cardOne = main.findViewById(R.id.cardOne);
        cardTwo = main.findViewById(R.id.cardTwo);
        cardThree = main.findViewById(R.id.cardThree);
        cardFour = main.findViewById(R.id.cardFour);
        nextCard = main.findViewById(R.id.nextCard);

        ImageView[] imgs = {cardOne, cardTwo, cardThree, cardFour, nextCard};
        cardRn.setStartImageResources(imgs);
        new TimerCountDown(timerText, main);

    }
}
