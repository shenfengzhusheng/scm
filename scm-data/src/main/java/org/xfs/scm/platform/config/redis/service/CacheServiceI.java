package org.xfs.scm.platform.config.redis.service;

import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * Created by 神风逐胜 on 2018/2/1 0001.20:52
 * version:1.0
 */
public interface CacheServiceI {

    boolean setObject(String key,Object object);

    boolean setObject(String key,Object object,int expireTimed);

    <T> T getObject(String key,Class<T> clazz);

    boolean set(String key,String value);
    /**
     * save Object
     * @param key
     * @param value
     * @param  expireTime
     * @return
     */
    boolean set(String key,String value, int expireTime);

    String get(String key);



    boolean remove(final String...key);

    boolean setKey(byte[] key, int expire, byte[] value);

    boolean setKey(byte[] key,byte[] value);

    byte[] getKey(byte[] key);

    Set<byte[]> keys(String keys);

    Set<String>keyLike(String key);

    Set<byte[]> keys(byte[] keys);


    boolean del(byte[] key);

    boolean del(String key);

    boolean del(String... keys);

    boolean flushDB();

    boolean incre(String key);

    void setJedisPool(JedisPool jedisPool);

    Long dbSize();
}
