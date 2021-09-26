package com.example.clashroyale;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.example.clashroyale.db.IRedisCon;
import com.example.clashroyale.db.RedisCon;

import java.util.HashMap;
import java.util.Random;

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


    /**
     * @param main 把MainActivity傳進來<br>
     *             這邊做初始化後面程式需要調動的變數
     */
    public static void init(MainActivity main) {
        calcPath();
        getScreenSize(main);
        calcPath();
        initializeRedisConnections();
    }

    /**
     * 計算螢幕的長寬
     */
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

    /**
     * 計算畫面上能走路徑的X座標
     */
    public static void calcPath() {
        pathOne_Left = (screenWidth / 4) - 70;
        pathOne_Right = (pathOne_Left + 60);
        pathMiddle = screenWidth / 2;
        pathTwo_Left = (screenWidth / 4 * 3) + 10;
        pathTwo_Right = (pathTwo_Left + 60);
    }

    /**
     * @param imgId 創建出來的卡牌實例的Id<br>
     * @param x 卡牌實例當下在畫面上的x座標<br>
     * @param y 卡牌實例當下在畫面上的y座標
     */
    public static void storeImgUnitXYData(String imgId, int x, int y) {
        String str = x + ", " + y;
        imgsPosition = new HashMap<>();
        imgsPosition.put(imgId, str);
    }

    /**
     * 建立連接redis資料庫的實例
     */
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

    /**
     * @param keylen 你要產生的uniqueId 的長度
     * @return 獨立的int Type Unique ID.
     */
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
