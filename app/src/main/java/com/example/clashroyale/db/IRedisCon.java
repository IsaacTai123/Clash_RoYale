package com.example.clashroyale.db;

import android.widget.ImageView;

import java.util.HashMap;

public interface IRedisCon {

    void storeXYToRedis(HashMap<String, String> data);
//    void closeConnection();
}
