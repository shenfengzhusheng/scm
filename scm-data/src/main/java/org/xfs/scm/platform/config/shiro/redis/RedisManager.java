package org.xfs.scm.platform.config.shiro.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class RedisManager {

    private static final Logger logger= LoggerFactory.getLogger(RedisManager.class);
    // 0 - never expire
    private int expire = 0;

    private JedisPool jedisPool;

    public RedisManager(){}
    public RedisManager(JedisPool jedisPool,int expire){
        this.jedisPool=jedisPool;
        this.expire=expire;
    }
    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        if(key==null){
            throw new IllegalArgumentException("remove keys must not null!");
        }
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            logger.error("RedisManager:get cache error key={}!",new String(key));
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value) {
       return this.set(key,value,-1);
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            if(expire==-1|| expire==0){
                if(jedis.set(key,value).equals("ok")){
                    return value;
                }
            }else{
                if(jedis.setex(key,expire,value).equals("ok")){
                    return value;
                }
            }
        }catch (Exception e){
            System.out.println("设值异常！");

            logger.error("RedisManager:set cache error keys={},value={},expire={},error={}!",new String(key),new String(value),expire,e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     *
     * del
     *
     * @param key
     */
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
            logger.error("RedisManager:del cache error key={}!",new String(key));
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    /**
     * flush
     */
    public void flushDB(String regex) {

        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            jedis.flushDB();
        }catch (Exception e){
            logger.error("RedisManager:flushDB error key={}!",e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    /**
     * size
     */
    public Long dbSize() {
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            return jedis.dbSize();
        }catch (Exception e){
            logger.error("RedisManager:dbSize error key={}!",e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * keys
     *
     * @param pattern
     * @return
     */
    public Set<byte[]> keys(String pattern) {
        Jedis jedis=null;
        try{
            jedis=this.jedisPool.getResource();
            return jedis.keys(pattern.getBytes());
        }catch (Exception e){
            logger.error("RedisManager:keys error key={}!",pattern);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
       return null;
    }


    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
