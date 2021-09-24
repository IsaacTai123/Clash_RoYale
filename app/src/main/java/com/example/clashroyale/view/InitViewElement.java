package com.example.clashroyale.view;

import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.clashroyale.MainActivity;
import com.example.clashroyale.R;

public class InitViewElement {

    public ImageView archor;
    public ImageButton card_archor;
    public ImageButton card_hogrider;

    public InitViewElement(MainActivity main)
    {
        archor = (ImageView) main.findViewById(R.id.archor);
        card_archor = (ImageButton) main.findViewById(R.id.archor_card);
        card_hogrider = (ImageButton) main.findViewById(R.id.hogRider_card);

    }

}
