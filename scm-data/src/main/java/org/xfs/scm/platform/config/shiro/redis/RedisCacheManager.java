package org.xfs.scm.platform.config.shiro.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RedisCacheManager implements CacheManager {
    private static final Logger logger= LoggerFactory.getLogger(RedisCacheManager.class);
    // fast lookup by name map
    @SuppressWarnings("rawtypes")
	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    private String keyPrefix = "shiro_redis_cache:";

    private CacheServiceI cacheService;

    public RedisCacheManager(){}
    public RedisCacheManager(CacheServiceI cacheService,String keyPrefix){
        this.cacheService =cacheService;
        this.keyPrefix=keyPrefix;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public <K, V> Cache<K, V> getCache(String name)throws CacheException {
        logger.debug("获取名称为: " + name + " 的RedisCache实例");
        Cache c = caches.get(name);
        if(logger.isDebugEnabled()){
            logger.debug("--------RedisCacheManager------getCache------------>{}", cacheService);
        }
        if (c == null) {
            // create a new cache instance
            c = new RedisCache<K, V>(this.cacheService, keyPrefix);
            // add it to the cache collection
            caches.put(name, c);
        }
        return c;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }


}
