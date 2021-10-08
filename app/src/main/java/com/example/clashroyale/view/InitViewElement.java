package com.example.clashroyale.view;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.clashroyale.BattleActivity;
import com.example.clashroyale.MainActivity;
import com.example.clashroyale.R;
import com.example.clashroyale.controller.CardDeck;

public class InitViewElement {

    public ImageButton cardOne, cardTwo, cardThree, cardFour, nextCard;


    public InitViewElement(BattleActivity main, CardDeck cardDeck)
    {
        ConstraintLayout fragmentCard = main.findViewById(R.id.cardDeck_activity);  //抓Fragment

        cardOne = fragmentCard.findViewById(R.id.cardOne);
        cardTwo = fragmentCard.findViewById(R.id.cardTwo);
        cardThree = fragmentCard.findViewById(R.id.cardThree);
        cardFour = fragmentCard.findViewById(R.id.cardFour);
        nextCard = fragmentCard.findViewById(R.id.nextCard);
        ProgressBar elixirBar = fragmentCard.findViewById(R.id.elixir);
        TextView elixirCount = fragmentCard.findViewById(R.id.currentElixir);

        ImageButton[] imgs = {cardOne, cardTwo, cardThree, cardFour, nextCard};
        cardDeck.setStartImageResources(imgs);
        cardDeck.startElixir(elixirBar, elixirCount);
    }
}
