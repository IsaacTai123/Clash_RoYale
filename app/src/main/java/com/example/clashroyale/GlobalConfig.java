package com.example.clashroyale;

import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.example.clashroyale.db.IRedisCon;
import com.example.clashroyale.db.RedisCon;
import com.google.android.material.internal.DescendantOffsetUtils;

import java.util.HashMap;
import java.util.Random;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class GlobalConfig {

    public static int screenWidth;
    public static int screenHeight;
    public static MainActivity mainActivity;
    public static int pathOne_Left;
    public static int pathOne_Right;
    public static int pathMiddle;
    public static int pathTwo_Left;
    public static int pathTwo_Right;
    public static IRedisCon jedisCon;
    public static HashMap<String, String> imgsPosition;


    public GlobalConfig() {
       calcPath();
    }

    public static void init(MainActivity main) {
        getScreenSize(main);
        calcPath();
        initializeRedisConnections();
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

    public static void calcPath() {
        pathOne_Left = (screenWidth / 4) - 70;
        pathOne_Right = (pathOne_Left + 60);
        pathMiddle = screenWidth / 2;
        pathTwo_Left = (screenWidth / 4 * 3) + 10;
        pathTwo_Right = (pathTwo_Left + 60);
    }

    public static void storeImgUnitXYData(String imgId, int x, int y) {
        String str = x + ", " + y;
        imgsPosition = new HashMap<>();
        imgsPosition.put(imgId, str);
    }

    public static void initializeRedisConnections() {
        RedisCon jedisConnection = new RedisCon();
        jedisCon = jedisConnection;
    }

    public static String generateStringId(int keylen) {
        String str = "1234567890ab;cdef:ghijklmn<o>p{qrs}tuzwxymz/[]?!@#$%&*()=-";
        // 將字串變成Array 陣列
        char[] chr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            chr[i] = str.charAt(i);
        }

        Random random = new Random();
        StringBuilder sb = new StringBuilder(keylen);

        for (int i=0; i < keylen; i++) {
            int rdint = random.nextInt(chr.length);
            sb.append(chr[rdint]);
        }

        return sb.toString();
      }

    public static int generateIntId(int keylen) {
        String str = "12395678903123451784920";
        // 將字串變成Array 陣列
        char[] chr = str.toCharArray();

        Random random = new Random();
        StringBuilder sb = new StringBuilder(keylen);

        for (int i=0; i < keylen; i++) {
            int rdint = random.nextInt(chr.length);
            sb.append(chr[rdint]);
        }
        return Integer.parseInt(sb.toString());
    }
}
