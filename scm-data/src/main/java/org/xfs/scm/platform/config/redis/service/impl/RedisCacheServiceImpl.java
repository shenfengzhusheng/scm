package org.xfs.scm.platform.config.redis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.utils.string.StringUtil;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;
import org.xfs.scm.platform.util.json.FastJsonUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Set;

/**
 * Created by 神风逐胜 on 2018/2/1 0001.20:59
 * version:1.0
 */
@Service(value="cacheService")
public class RedisCacheServiceImpl implements CacheServiceI{

    private final Logger logger= LoggerFactory.getLogger(RedisCacheServiceImpl.class);

    @Resource
    private JedisPool jedisPool;

    private int expireTime=1800;

    @SuppressWarnings("unused")
	private static final String KEY_SPLIT = ":"; // 用于隔开缓存前缀与缓存键值

    @Override
    public boolean setObject(String key, Object object) {
        return this.setObject(key,object,-1);
    }

    @Override
    public boolean setObject(String key, Object object, int expireTime) {
        if(StringUtil.isEmpty(key)){
            throw new IllegalArgumentException("key must not null!");
        }
        if (object ==null){
            throw new IllegalArgumentException("object must not null!");
        }
        Jedis jedis=null;
        try{
            String value= Base64.getEncoder().encodeToString(FastJsonUtil.toJsonString(object).getBytes());
            jedis=this.jedisPool.getResource();

            if(expireTime==-1){
                if(jedis.set(key,value).equals("ok")){
                    return true;
                }
            }else{

                if(expireTime==0){
                    expireTime=this.expireTime;
                }
                if(jedis.setex(key,expireTime,value).equals("ok")){
                    return true;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.error("RedisCacheServiceImpl:setObject cache key={},value={},expireTime={}",key,object,expireTime);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        if(StringUtil.isEmpty(key)){
            throw new IllegalArgumentException("key must not null!");
        }
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            String value=jedis.get(key);
            if(!StringUtil.isEmpty(value)){
               // System.out.println("cache value:" + value);

                return FastJsonUtil.toObject( new String(Base64.getDecoder().decode(value)),clazz);
            }

        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:getObject cache key={}",key);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    @Override
    public boolean set(String key, String value) {
        return this.set(key,value,-1);
    }

    @Override
    public boolean set(String key, String value,int expireTime) {
        if(StringUtil.isEmpty(key)){
            throw new IllegalArgumentException("key must not null!");
        }
        if (StringUtil.isEmpty(value)){
            throw new IllegalArgumentException("value must not null!");
        }
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            if(expireTime==-1){
                if(jedis.set(key,value).equalsIgnoreCase("ok")){
                    return true;
                }
            }else{
                if(jedis.setex(key,expireTime,value).equals("ok")){
                    return true;
                }
            }
        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:setWithExpireTime cache key={},value={},expireTime={}",key,value,expireTime);
        }finally {
           if(jedis!=null){
               jedis.close();
           }
        }
        return false;
    }

    @Override
    public String get(String key) {
        if(StringUtil.isEmpty(key)){
            throw new IllegalArgumentException("key must not null!");
        }
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:get cache error key={}!",key);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    @Override
    public boolean remove(String... keys) {
        if(keys==null ){
            throw new IllegalArgumentException("remove keys must not null!");
        }
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            long delCount=jedis.del(keys);
            if(delCount==keys.length){
                return true;
            }
        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:get cache error keys=["+keys+"]!");

        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public boolean setKey(byte[] key, int expire, byte[] value) {
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            if(expire==-1){
                if(jedis.set(key,value).equals("ok")){
                    return true;
                }
            }else{
                if(expire==0){
                    expire=expireTime;
                }
                if(jedis.setex(key,expire,value).equals("ok")){
                    return true;
                }
            }
        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:get cache error keys={},value={},expire={}!",new String(key),new String(value),expire);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public boolean setKey(byte[] key, byte[] value) {
        return this.setKey(key,-1,value);
    }

    @Override
    public byte[] getKey(byte[] key) {
        if(key==null){
            throw new IllegalArgumentException("getKey keys must not null!");
        }
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:get cache error keys={}!",new String(key));
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    @Override
    public Set<byte[]> keys(String keys) {
        return this.keys(keys.getBytes());
    }

    @Override
    public Set<String> keyLike(String key) {
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            return jedis.keys(key);
        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:keyLike cache error keys={}!",new String(key));
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    @Override
    public Set<byte[]> keys(byte[] keys) {
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            return jedis.keys(keys);
        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:keys cache error keys={}!",new String(keys));
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    @Override
    public boolean del(byte[] key) {
        if(key==null){
            throw new IllegalArgumentException("remove keys must not null!");
        }
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            if(jedis.del(key)==1){
                return true;
            }
        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:del cache error key={}!",new String(key));
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public boolean del(String key) {
        if(key==null && key!=""){
            throw new IllegalArgumentException("remove keys must not null!");
        }
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            if(jedis.del(key)==1){
                return true;
            }
        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:del cache error key={}!",key);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public boolean del(String... keys) {
        if(keys==null){
            throw new IllegalArgumentException("remove keys must not null!");
        }
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            if(jedis.del(keys)==keys.length){
                return true;
            }
        }catch (Exception e){
        	 logger.error("RedisCacheServiceImpl:del cache error keys=["+keys+"]!");
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public boolean flushDB() {
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            jedis.flushDB();
            return true;
        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:flushDB error!",e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public boolean incre(String key) {
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            jedis.incr(key);
            return true;
        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:flushDB error!",e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    @Override
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public Long dbSize() {
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            return jedis.dbSize();

        }catch (Exception e){
            logger.error("RedisCacheServiceImpl:dbSize error!",e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return 0L;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }
}
