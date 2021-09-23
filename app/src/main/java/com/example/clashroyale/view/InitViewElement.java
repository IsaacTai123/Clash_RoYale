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

    public MainActivity main;
    public ImageView archor;
    public ImageButton card_archor;
    public ImageButton card_hogrider;
    public int screenWidth;
    public int screenHeight;

    public InitViewElement(MainActivity main)
    {
        archor = (ImageView) main.findViewById(R.id.archor);
        card_archor = (ImageButton) main.findViewById(R.id.archor_card);
        card_hogrider = (ImageButton) main.findViewById(R.id.hogRider_card);

        getScreenSize(main);
    }

     public void getScreenSize(MainActivity main)
     {
         WindowManager wm = main.getWindowManager();
         Display disp = wm.getDefaultDisplay();
         Point size = new Point();
         disp.getSize(size);
         screenWidth = size.x;
         screenHeight = size.y;
     }
}
