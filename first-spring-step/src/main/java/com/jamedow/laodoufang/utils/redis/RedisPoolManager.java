package com.jamedow.laodoufang.utils.redis;


import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

@Component
public class RedisPoolManager {
    private static Logger logger = LoggerFactory.getLogger(RedisPoolManager.class);

    /*Redis是一个开源的使用ANSI C语言编写、遵守BSD协议、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。
     *它通常被称为数据结构服务器，因为值（value）可以是 字符串(String), 哈希(Map), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型*/

    //Redis服务器IP
    @Value("${redis.host}")
    private String HOST;

    //Redis的端口号
    @Value("${redis.port}")
    private int PORT;

    //访问密码
    @Value("${redis.auth}")
    private String AUTH;

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    @Value("${redis.maxActive}")
    private int MAX_ACTIVE;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    @Value("${redis.maxIdle}")
    private int MAX_IDLE;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    @Value("${redis.maxWait}")
    private int MAX_WAIT;

    @Value("${redis.timeout}")
    private int TIMEOUT;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    @Value("${redis.testOnBorrow}")
    private boolean TEST_ON_BORROW;

    private Jedis jedis;

    @Qualifier("redisTemplate")
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    protected Jedis createJedis() {
        if (jedis == null) {
            jedis = new Jedis(HOST, PORT, TIMEOUT);
            if (!jedis.isConnected()) {
                jedis.connect();
            }
            if (!StringUtils.isNullOrEmpty(AUTH)) {
                jedis.auth(AUTH);
            }
        }
        jedis.connect();
        return jedis;
    }

    private Boolean expire(String key, final long timeout, final TimeUnit unit) {
        return stringRedisTemplate.expire(key, timeout, unit);
    }

    private void put(String key, Object hashKey, Object value) {
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    private Object get(String key, Object hashKey) {
        return stringRedisTemplate.opsForHash().get(key, hashKey);
    }


    public void setCacheWithSec(String key, String value, long expire) {
        put(key, key, value);
        expire(key, expire, TimeUnit.SECONDS);
    }

    public String getCache(String key) {
        return (String) get(key, key);
    }
}