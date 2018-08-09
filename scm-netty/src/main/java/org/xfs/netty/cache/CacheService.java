package org.xfs.netty.cache;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.scm.common.utils.json.FastJsonUtil;
import org.xfs.scm.common.utils.string.StringUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Base64;
import java.util.Set;

public class CacheService {
    private static final Logger LOGGER= LoggerFactory.getLogger(CacheService.class);
    private static int CACHE_EXPIRETIME=1800;
    public static boolean setObject(String key, Object object) {
        return setObject(key,object,-1);
    }

    public static JedisPool jedisPool(){
        GenericObjectPoolConfig genericObjectPoolConfig=new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxWaitMillis(1000);
        genericObjectPoolConfig.setMaxIdle(20);
        genericObjectPoolConfig.setMaxTotal(20000);

        JedisPool jedisPool=new JedisPool(genericObjectPoolConfig,"127.0.0.1",6379,3000,"qht_bj",6);
        return jedisPool;
    }

    public static boolean setObject(String key, Object object, int expireTime) {
        if(StringUtil.isEmpty(key)){
            throw new IllegalArgumentException("key must not null!");
        }
        if (object ==null){
            throw new IllegalArgumentException("object must not null!");
        }
        Jedis jedis=null;
        try{
            String value= Base64.getEncoder().encodeToString(FastJsonUtil.toJsonString(object).getBytes());
            jedis=jedisPool().getResource();

            if(expireTime==-1){
                if(jedis.set(key,value).equals("ok")){
                    return true;
                }
            }else{
                if(expireTime==0){
                    expireTime=CACHE_EXPIRETIME;
                }
                if(jedis.setex(key,expireTime,value).equals("ok")){
                    return true;
                }
            }

        }catch (Exception e){
            LOGGER.error("RedisCacheServiceImpl:setObject cache key={},value={},expireTime={}",new Object[]{key,object,expireTime,e});
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    public static boolean del(String key) {
        if(key==null && key!=""){
            throw new IllegalArgumentException("remove keys must not null!");
        }
        Jedis jedis=null;
        try{
            jedis=jedisPool().getResource();
            if(jedis.del(key)==1){
                return true;
            }
        }catch (Exception e){
            LOGGER.error("RedisCacheServiceImpl:del cache error key={}!",key,e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return false;
    }

    public static Set<String> keys(String key){
        if(key==null && key!=""){
            throw new IllegalArgumentException("keys key must not null!");
        }
        Jedis jedis=null;
        try{
            jedis=jedisPool().getResource();
            return jedis.keys(key+"*");
        }catch (Exception e){
            LOGGER.error("RedisCacheServiceImpl:keys cache error keys={}!",key);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

}
