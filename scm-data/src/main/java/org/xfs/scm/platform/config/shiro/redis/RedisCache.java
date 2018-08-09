package org.xfs.scm.platform.config.shiro.redis;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.scm.common.utils.SerializeUtils;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;

import java.util.*;

/**
 * Created by 神风逐胜 on 2018/2/4 0004.20:58
 * version:1.0
 */
public class RedisCache<K,V>implements Cache<K,V> {

    private final static Logger logger= LoggerFactory.getLogger(RedisCache.class);

    private String keyPrefix="shiro_redis_cache";

    private CacheServiceI cacheService;

    public RedisCache(CacheServiceI cache){
        if (cache == null) {
            throw new IllegalArgumentException("Cache argument cannot be null.");
        }
        this.cacheService = cache;
    }
    private byte[] getByteKey(K key){
        if(key instanceof String){
            String preKey=this.keyPrefix+key;
            return preKey.getBytes();
        }else{
            return key.toString().getBytes();
        }
    }
    public RedisCache(CacheServiceI cache,String prefix){
        this(cache);
        this.keyPrefix = prefix;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    @Override
    public V get(K key) throws CacheException {
        if(logger.isDebugEnabled()){
           logger.debug("根据key从Redis中获取对象 key [" + key + "]");
        }
      //  logger.info("根据key从Redis中获取对象 key [" + key + "]");

        try {
            if (key == null) {
                return null;
            }else{
                byte[] rawValue =this.cacheService.getKey(getByteKey(key));
                if(rawValue==null) {
                	return null;
                }
                @SuppressWarnings("unchecked")
				V value = (V)SerializeUtils.deserialize(rawValue);
                return value;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        logger.debug("根据key从存储 key [" + key + "]");
        try {
            if(key.toString().contains("_retry")){
                this.cacheService.setKey(getByteKey(key), 900,SerializeUtils.serialize(value));
            }else{
                this.cacheService.setKey(getByteKey(key), 1800,SerializeUtils.serialize(value));
            }
            return value;

        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        logger.debug("从redis中删除 key [" + key + "]");
        try {
            V previous = get(key);
            this.cacheService.del(getByteKey(key));
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        logger.debug("从redis中删除所有元素");
        try {
           // this.redisTemplate.
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @SuppressWarnings("unchecked")
	@Override
    public Set<K> keys() {
        try {
            Set<byte[]> keys = this.cacheService.keys(this.keyPrefix + "*");
            if (CollectionUtils.isEmpty(keys)) {
                return Collections.emptySet();
            }else{
                Set<K> newKeys = new HashSet<K>();
                for(byte[] key:keys){
                    newKeys.add((K)key);
                }
                return newKeys;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Collection<V> values() {
        try {
            Set<byte[]> keys = this.cacheService.keys(this.keyPrefix + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (byte[] key : keys) {
                    @SuppressWarnings("unchecked")
					V value = get((K)key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }
}
