package com.example.clashroyale;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.clashroyale.controller.CardDeck;
import com.example.clashroyale.db.IMysqlCon;
import com.example.clashroyale.db.IRedisCon;
import com.example.clashroyale.db.MysqlCon;
import com.example.clashroyale.db.RedisCon;
import com.example.clashroyale.models.Archor;
import com.example.clashroyale.models.Giant;
import com.example.clashroyale.models.ICard;

import java.util.HashMap;
import java.util.Random;

public class GlobalConfig {

    public static int playgroundWidth;
    public static int playgroundHeight;
    public static int playLimit;  //出牌的限制區域
    public static MainActivity mainActivity;
    public static int pathOne_Left;
    public static int pathOne_Right;
    public static int pathMiddle;
    public static int pathTwo_Left;
    public static int pathTwo_Right;
    public static IRedisCon jedisCon;
    public static IMysqlCon mysqlCon;
    public static HashMap<String, String> imgsPosition;
    public static String jsonString_troop;
    public static String jsonString_spell;
    public static ICard[] cardsInstance = new ICard[8];
    private static String[] cardArray;



    /**
     * @param playView 把playground遊戲場地 ConstraintLayout 傳進來<br>
     *             這邊做初始化後面程式需要調動的變數
     */
    public static void initActivity(ConstraintLayout playView) {
        getPlayScreenSize(playView);
        calcPath();
    }

    public static void initFragment_card(int playerId) {
        initRedisConnections();
        initMySqlConnections(playerId);
        initCardsData(playerId);
    }


    /**
     * 計算螢幕的長寬
     */
    public static void getPlayScreenSize(ConstraintLayout playView) {
//        mainActivity = main;
//        WindowManager wm = main.getWindowManager();
//        Display display = wm.getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        screenWidth = size.x;
//        screenHeight = size.y;

        ImageView playground = playView.findViewById(R.id.playground);
        playgroundWidth = playground.getWidth();
        playgroundHeight = playground.getHeight();
    }


    public static MainActivity getMain() {
        return mainActivity;
    }

    /**
     * 計算畫面上能走路徑的X座標
     */
    public static void calcPath() {
        pathOne_Left = 160;
        pathOne_Right = 240;
        pathMiddle = playgroundWidth / 2;
        pathTwo_Left = 650;
        pathTwo_Right = 730;
        playLimit = playgroundHeight / 2 + 50;  //50 是河的一半
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
    public static void initRedisConnections() {
        RedisCon jedisConnection = new RedisCon();
        jedisCon = jedisConnection;
    }

    public static void initMySqlConnections(int playerId) {
            MysqlCon mysql = new MysqlCon();
            mysqlCon = mysql;
    }

    /**
     * 把玩家選定的卡牌組給讀出來(玩家可以有多個卡牌組)
     */
    public static void initCardInstance(int playerId) {
            MysqlCon mysql = new MysqlCon();
            IMysqlCon mysqlCon = mysql;
            cardArray = mysqlCon.getCardDeck(playerId, 1);
            CardDeck cardDeck = new CardDeck();
            cardsInstance = cardDeck.generateCardInstance(cardArray);
    }

    /**
     * @param playerId 玩家的ID號碼<br>
     *                 Mysql的Data抓下來,並儲存到Global Variable
     *                 <pre>cardsInstance 會儲存順序更改過後的ICard Array</pre>
     *
     */
    public static void initCardsData(int playerId) {
            mysqlCon.init();
            int sqlTroopCount = mysqlCon.getCountTroopData(playerId);
            int sqlSpellCount = mysqlCon.getCountSpellData(playerId);
            jsonString_troop = mysqlCon.getCardTroopData(playerId, sqlTroopCount);
            jsonString_spell = mysqlCon.getCardSpellData(playerId, sqlSpellCount);

            String[] cardArray = mysqlCon.getCardDeck(1, 1); //取出這次玩家所使用的8張卡牌
            CardDeck cardDeck = new CardDeck();
            cardsInstance = cardDeck.generateCardInstance(cardArray);
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

    /**
     * @param name Json資料型態裡面的key
     * @param field  資料庫每個值對應的Title (字串列列Array)
     * @param values 從資料庫讀對應讀取下來的值(字串陣列Array)
     * @return String: 回傳JSON格式的字串 <br>
     * 此methods 回傳的是Json資料, 但因為需要用來Loop 多個資料, 所以使用的時候要注意用法
     * 在使用這個轉換的時候, 需再呼叫這個methods的前面加上一個 "{" 然後再呼叫完之後再加上一個結尾"}"
     */
    public static String stringToJsonFormat(String name, String[] field, String[] values) {
       StringBuilder sb = new StringBuilder();
        sb.append("\"").append(name).append("\"").append(":").append("{");
        for (int i=0; i < field.length; i++) {
            sb.append("\"").append(field[i]).append("\"").append(":").append("\"").append(values[i]).append("\"");
            if (i != field.length - 1) sb.append(",");
        }
        sb.append("}");
        return sb.toString();
    }


}
