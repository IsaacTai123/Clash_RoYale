package com.example.clashroyale.Models.DAO;

import java.util.HashMap;

public interface IRedisCon {

    void storeXYToRedis(HashMap<String, String> data);
//    void closeConnection();
}
