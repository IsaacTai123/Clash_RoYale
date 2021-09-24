package com.example.clashroyale.db;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisCon {

    private Jedis jedis;

    public RedisCon() {
        JedisShardInfo shardInfo = new JedisShardInfo("150.117.238.70", 6379);
        shardInfo.setPassword("abc");
        jedis = new Jedis(shardInfo);
    }

    public String getRedisData(){
        String res = jedis.get("bag");
        return res;
    }

    public void setRedisData() {

    }
}
