package com.example.clashroyale.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.clashroyale.MainActivity;
import com.example.clashroyale.R;
import com.example.clashroyale.controller.CardRandom;

import java.sql.Connection;
import java.util.zip.Inflater;

public class InitViewElement {

    public ImageButton cardOne, cardTwo, cardThree, cardFour, nextCard;

    public InitViewElement(ConstraintLayout view, CardRandom cardRn)
    {
        cardOne = view.findViewById(R.id.cardOne);
        cardTwo = view.findViewById(R.id.cardTwo);
        cardThree = view.findViewById(R.id.cardThree);
        cardFour = view.findViewById(R.id.cardFour);
        nextCard = view.findViewById(R.id.nextCard);

        ImageView[] imgs = {cardOne, cardTwo, cardThree, cardFour, nextCard};
        cardRn.setStartImageResources(imgs);

    }
}
