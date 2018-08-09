package org.xfs.scm.platform.config.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;

/**
 * Created by 神风逐胜 on 2018/1/30 0030.20:49
 * version:1.0
 */
public class RedisConfig {
    @Value("${redis.session.url}")
    private String REDIS_SESSION_URL;

    @Value("${redis.password}")
    private String REDIS_PASSWORD;

    @Value("${redis.timeout}")
    private String REDIS_TIMEOUT;


    @Value("${redis.port}")
    private String PORT;

    @Value("${redis.test}")
    private String TEST;

    @Value("${reids.urls}")
    private String HOSTS;


    @Value("${redis.minevicttime}")
    private Long MINEVICTTIME;

    @Value("${redis.fifo}")
    boolean FIFO_FLAG;

    @Value("${redis.database}")
    int database;

    @Bean(destroyMethod = "destroy")
    public JedisPool jedisPool(){
        if(this.REDIS_SESSION_URL!=null &&this.REDIS_SESSION_URL!=""){
            GenericObjectPoolConfig genericObjectPoolConfig=new GenericObjectPoolConfig();
            genericObjectPoolConfig.setMaxWaitMillis(1000);
            genericObjectPoolConfig.setMaxIdle(20);
            genericObjectPoolConfig.setMaxTotal(20000);

            JedisPool jedisPool=new JedisPool(genericObjectPoolConfig,REDIS_SESSION_URL,Integer.parseInt(PORT),Integer.parseInt(REDIS_TIMEOUT),REDIS_PASSWORD,database);
            return jedisPool;
        }
        return null;
    }
}
