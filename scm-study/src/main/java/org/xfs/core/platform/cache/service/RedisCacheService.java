package org.xfs.core.platform.cache.service;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StopWatch;
import org.xfs.core.util.SerializeUtils;
import org.xfs.core.util.string.StringUtil;

import redis.clients.jedis.JedisCluster;

import com.alibaba.fastjson.JSON;

@Component
public class RedisCacheService {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheService.class);

    @Resource
    private JedisCluster jedisCluster;

    private static final String KEY_SPLIT = ":"; // 用于隔开缓存前缀与缓存键值

    public long del(final String... keys) {
        return this.jedisCluster.del(keys);
    }

    public boolean set(String key, String value) {
        return this.set(key, value, -1);

    }

    public boolean set(String prefix, String key, String value) {
        return this.set(prefix, key, value, -1);
    }

    public boolean set(String prefix, String key, String value, int expireTime) {
        boolean result = false;
        if (StringUtil.isEmpty(prefix))
            throw new IllegalArgumentException("prefix must not null!");
        if (StringUtil.isEmpty(key))
            throw new IllegalArgumentException("key must not null!");
        if (expireTime != -1) {
            logger.info("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", new Object[] {prefix + KEY_SPLIT + key, value,
                    expireTime});
            if (this.jedisCluster.setex(prefix + KEY_SPLIT + key, expireTime, value).equalsIgnoreCase("ok")) {
                result = true;
            }
        } else {
            logger.info("RedisUtil:set cache key={},value={}", prefix + KEY_SPLIT + key, value);
            if (this.jedisCluster.set(prefix + KEY_SPLIT + key, value).equalsIgnoreCase("ok")) {
                result = true;
            }
        }
        return result;


    }

    /**
     * 获取指定key的缓存
     * 
     * @param prefix
     * @param key
     */
    public String get(String prefix, String key) {
        if (StringUtil.isEmpty(prefix))
            throw new IllegalArgumentException("prefix must not null!");
        if (StringUtil.isEmpty(key))
            throw new IllegalArgumentException("key must not null!");

        String value = jedisCluster.get(prefix + KEY_SPLIT + key);
        logger.debug("RedisUtil:get cache key={},value={}", prefix + KEY_SPLIT + key, value);
        return value;
    }

    /**
     * 删除指定key的缓存
     * 
     * @param prefix
     * @param key
     */
    public void deleteWithPrefix(String prefix, String key) {
        if (StringUtil.isEmpty(prefix))
            throw new IllegalArgumentException("prefix must not null!");
        if (StringUtil.isEmpty(key))
            throw new IllegalArgumentException("key must not null!");

        jedisCluster.del(prefix + KEY_SPLIT + key);
        logger.debug("RedisUtil:delete cache key={}", prefix + KEY_SPLIT + key);
    }

    /**
     * 设值
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public boolean set(String key, String value, int seconds) {
        boolean result = false;
        if (this.jedisCluster != null) {
            if (jedisCluster.set(key, value).equals("ok")) {
                if (seconds != -1) {
                    this.jedisCluster.expire(key, seconds);
                }
                result = true;
            }
        }
        return result;
    }

    /**
     * 设置缓存
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        if (!StringUtil.isEmpty(key)) {
            result = this.jedisCluster.get(key);
        }
        return result;
    }

    /**
     * 删除指定key的缓存
     * @param key
     * @return
     */
    public boolean del(String key) {
        boolean result = false;
        if (!StringUtil.isEmpty(key)) {
            long count = this.jedisCluster.del(key);
            if (count > 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 数量添加
     * @param key
     * @return
     */
    public long incr(String key) {
        return this.jedisCluster.incr(key);
    }

    /**
     * 设置缓存缓存时长
     * @param key
     * @param seconds
     * @return
     */
    public long expire(String key, int seconds) {
        return this.jedisCluster.expire(key, seconds);
    }

    public long tll(String key) {

        return this.jedisCluster.ttl(key);
    }

    public boolean hset(String key, String field, String value) {
        return this.jedisCluster.hset(key, field, value) > 0 ? true : false;
    }

    public boolean setStr(String key, String value, int seconds, boolean override) {

        boolean ok = false;
        try {

            if (override == false && this.jedisCluster.exists(key)) {
                ok = false;
            } else {
                if (seconds < 1) {
                    this.jedisCluster.set(key, value);
                } else {
                    this.jedisCluster.setex(key, seconds, value);
                }
                ok = true;
            }
        } catch (Exception e) {
            // this.jedisCluster.
            e.printStackTrace();
            ok = false;
        } finally {
            // returnResource(jedisPool, jedis);
        }
        return ok;
    }

    public boolean setObj(String key, Object value, int seconds, boolean override) {
        boolean ok = false;

        byte[] data = SerializeUtils.serializer(value);
        if (data == null) {
            logger.info("setObj SerialUtil.encode error");
            return false;
        }

        try {
            if (override == false && this.jedisCluster.exists(key)) {
                ok = false;
            } else {
                if (seconds < 1) {
                    this.jedisCluster.set(key.getBytes(), data);
                } else {
                    this.jedisCluster.setex(key.getBytes(), seconds, data);
                }
                ok = true;
            }
        } catch (Exception e) {
            // jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
            ok = false;
        } finally {
            // returnResource(jedisPool, jedis);
        }
        return ok;
    }

    public Object getObj(String key) {

        byte[] data = null;

        try {
            data = this.jedisCluster.get(key.getBytes());
            if (data == null || data.length == 0) {
                logger.debug("redis data is empty " + key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // returnResource(jedisPool, jedis);
        }
        Object obj = null;
        if (data != null) {
            obj = SerializeUtils.deserializer(data, Object.class);
        }
        return obj;
    }

    public byte[] getObjByte(String key) {
        byte[] data = null;
        try {
            data = this.jedisCluster.get(key.getBytes());
            if (data == null || data.length == 0) {
                logger.debug("redis data is empty " + key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // returnResource(jedisPool, jedis);
        }

        return data;
    }


    public boolean setObject(String key, int timeOut, Object object) {
        StopWatch sw = new StopWatch();
        sw.start("validate:");
        if (StringUtil.isEmpty(key))
            throw new IllegalArgumentException("key must not null!");
        if (object == null)
            throw new IllegalArgumentException("object must not null!");
        sw.stop();
        sw.start("serialize:");

        boolean ok = false;
        try {

            String json = JSON.toJSONString(object);
            sw.stop();
            System.gc();
            String value = Base64Utils.encodeToString(json.getBytes());
            sw.start("cached:");
            if (this.jedisCluster.setex(key, timeOut, value).equalsIgnoreCase("ok")) {
                ok = true;
            }
            sw.stop();
            System.out.println("cache value:" + value);
            // sw.prettyPrint();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    public Object getObject(String key, Class<?> clazz) {
        if (StringUtil.isEmpty(key))
            throw new IllegalArgumentException("key must not null!");
        if (!this.jedisCluster.exists(key)) {
            return null;
        }
        Object obj = null;
        String json = null;
        try {
            json = this.jedisCluster.get(key);
        } catch (Exception e) {
           logger.error("无缓存:",e);
        }
        if (!StringUtil.isEmpty(json)) {
          //  System.out.println("cache json:" + json);
            obj = JSON.parseObject(Base64Utils.decodeFromString(json), clazz);
        }
        return obj;
    }

    public boolean append(String key, String value) {
        boolean result = false;
        // if(this.jedisCluster.exists(key))
        return result;
    }

  
}
