package com.example.redis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedisTest {

    @Test
    public void savingStringWithJedis() {
        // Do not forget to start the Redis server!
        Jedis jedis = new Jedis();
        SetParams setParams = new SetParams();
        setParams.ex(10);
        jedis.set("Key", "Value", setParams);
        assertEquals("Value", jedis.get("Key"));
    }

    @Test
    public void savingHashesWithJedis() {
        // Do not forget to start the Redis server!
        Jedis jedis = new Jedis();
        jedis.hset("key", "field", "value");
        jedis.expire("key", 20);
        assertEquals("value", jedis.hget("key","field"));
    }

}
