package com.example.clashroyale.db;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisCon {

    public String getRedisData(){
        JedisShardInfo shardInfo = new JedisShardInfo("150.117.238.70", 6379);
        shardInfo.setPassword("abc");
        Jedis jedis = new Jedis(shardInfo);
        String res = jedis.get("bag");
        return res;
    }
}
