package com.example.clashroyale.Models.DAO;

import android.util.Log;

import java.util.HashMap;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisShardInfo;

public class RedisCon implements IRedisCon {

    private final static String IPADDR = "150.117.238.70";
    private final static int PORT = 6379;
    private final static String AUTH = "abc";
    /*
    可用連線例項的最大數目，預設為8
    如果賦值為-1，則表示不限制；如果pool已經分配了maxActive個jedis例項，則此
    時pool的狀態為exhausted(耗盡)。
    */
    private final static int MAX_ACTIVE = 1024;

    //控制一個pool最多有多少個狀態為idle(空閒)的jedis例項，預設也是8
    private final static int MAX_IDLE = 200;

    /*
    等待可用連線的最大時間，單位毫秒，預設值為-1，表示永不超時。 1秒 = 1000毫秒
    如果超過等待時間，則直接丟擲JedisConnectionException
     */
    private final static int MAX_WAIT = 10000;
    private final static int TIMEOUT = 10000;

    private static JedisPool jedisPool = null;
//    private JedisShardInfo shardInfo;
//    private Jedis jedis;


    public RedisCon() {
            /**
             * 初始化Redis連線池
             * Jedis的連線池配置需要用到org.apache.commons.pool.impl.GenericObjectPool.Config.class
             * 所以要引入commons-pool.jar
             **/
//            try {
//                new Thread(() -> {
//                    JedisPoolConfig config = new JedisPoolConfig();
//                    config.setMaxTotal(MAX_ACTIVE);//老版本是setMaxActive
//                    config.setMaxIdle(MAX_IDLE);
//                    config.setMaxWaitMillis(MAX_WAIT);//老版本是maxMaxWait
//                    jedisPool = new JedisPool(config, IPADDR, PORT, TIMEOUT, AUTH);//有密碼的時候傳入AUTH
//                    Log.e("Jedis", "連接池(connection pool)連接成功");


//                    jedis = new Jedis(shardInfo);
//                }).start();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
    }


    public void storeXYToRedis(HashMap<String, String> data) {
        // 把新的座標位置用hashmap的資料型態存進Redis
            Jedis jedis = null;
            try {
                JedisShardInfo shardInfo = new JedisShardInfo(IPADDR, PORT);
                shardInfo.setPassword(AUTH);
                jedis = new Jedis(shardInfo);
                Log.e("Jedis", "連接成功");

                HashMap<String, String> hashmap;
//                hashmap = GlobalConfig.storeImgUnitXYData(String.valueOf(imgUnit.getId()), x, y);
                jedis.hmset("imgsPosition", data);
            }
            catch (Exception e) {
//                Log.e("Jedis", "Exception: "  e.printStackTrace());
                e.printStackTrace();
            }
            finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
    }



}
