package com.example.clashroyale;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class GlobalConfig {

    public static int screenWidth;
    public static int screenHeight;
    public static MainActivity mainActivity;
    public static int pathOne_Left;
    public static int pathOne_Right;
    public static int pathTwo_Left;
    public static int pathTwo_Right;

    public GlobalConfig() {
       calcPath();
    }

    public static void getScreenSize(MainActivity main) {
        mainActivity = main;
        WindowManager wm = main.getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }

    public static MainActivity getMain() {
        return mainActivity;
    }

    public void calcPath() {
        pathOne_Left = (screenWidth / 4) - 20;
        pathOne_Right = (pathOne_Left + 40);
        pathTwo_Left = (screenWidth / 4 * 3) - 20;
        pathTwo_Right = (pathTwo_Left + 40);
    }
}
