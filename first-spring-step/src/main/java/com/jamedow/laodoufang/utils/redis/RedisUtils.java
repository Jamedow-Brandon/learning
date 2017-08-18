package com.jamedow.laodoufang.utils.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    /*Redis是一个开源的使用ANSI C语言编写、遵守BSD协议、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。
     *它通常被称为数据结构服务器，因为值（value）可以是 字符串(String), 哈希(Map), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型*/

    @Qualifier("redisTemplate")
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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