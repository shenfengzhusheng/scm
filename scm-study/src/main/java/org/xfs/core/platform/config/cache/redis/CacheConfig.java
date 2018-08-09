package org.xfs.core.platform.config.cache.redis;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.xfs.core.util.string.StringUtil;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

//@PropertySource(value = {"classpath:config/properties/dev/redis.properties"})
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1500)
@EnableCaching
public class CacheConfig {

    private static Logger logger = LoggerFactory.getLogger(CacheConfig.class);

    @Value("${redis.session.url}")
    String REDIS_SESSION_URL;

    @Value("${redis.password}")
    String REDIS_PASSWORD;

    @Value("${redis.timeout}")
    String REDIS_TIMEOUT;

    @Value("${redis.test}")
    String TEST;

    @Value("${reids.urls}")
    String HOSTS;


    @Value("${redis.minevicttime}")
    Long MINEVICTTIME;

    @Value("${redis.fifo}")
    boolean FIFO_FLAG;


    @Value("${jdbc.username}")
    String url;

    // private JedisCluster jedisCluster;
    @Bean(destroyMethod = "destroy")
    public JedisConnectionFactory redisConnectionFactory() {
        logger.info(this.TEST + "--------------init session redis----------------------------------");
        // 单机做法
        // JedisConnectionFactory connection = new JedisConnectionFactory();
        // connection.setPort(6379);
        // connection.setHostName(redis_session_url);
        // connection.setTimeout(Integer.parseInt(redis_timeout));
        // connection.setPassword(redis_password);
        // 集群
        JedisConnectionFactory connection = new JedisConnectionFactory(redisClusterConfiguration(), jedisPoolConfig());

        connection.setUsePool(true);
        connection.setTimeout(2000);
        return connection;
    }

    @Bean(name = "jedisCulster")
    public JedisCluster jedisCulster() {
        if (!"".equals(this.HOSTS) && this.HOSTS != null) {

            GenericObjectPoolConfig redisPoolConfig = new GenericObjectPoolConfig();
            redisPoolConfig.setMaxWaitMillis(1000);
            redisPoolConfig.setMaxTotal(30000);
            redisPoolConfig.setMaxIdle(20);
            redisPoolConfig.setTestOnBorrow(true);
            Set<HostAndPort> hosts = new HashSet<HostAndPort>();
            String[] hs = this.stringArr();
            for (String h : hs) {
                String[] arr = h.split(":");
                hosts.add(new HostAndPort(arr[0], Integer.parseInt(arr[1])));
            }
            // System.out.println("--------------hostsize-------------" + hosts.size());
            return new JedisCluster(hosts, redisPoolConfig);
        }
        return null;

    }

    private String[] stringArr() {
        if (!StringUtil.isEmpty(this.HOSTS)) {
            return this.HOSTS.split(" ");
        }
        return null;
    }

    /**
     * JedisPoolConfig 配置
     * 
     * 配置JedisPoolConfig的各项属性
     * 
     * @return
     */
    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(true);
        // 是否启用pool的jmx管理功能, 默认true

        jedisPoolConfig.setJmxEnabled(true);
        // 默认就好
        jedisPoolConfig.setJmxNameBase("JedisPool");
        // jedis调用returnObject方法时，是否进行有效检查
        jedisPoolConfig.setTestOnBorrow(true);
        // 是否启用后进先出, 默认true
        jedisPoolConfig.setLifo(FIFO_FLAG);
        // 最大空闲连接数, 默认2个
        jedisPoolConfig.setMaxIdle(2);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(3);


        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间, 默认-1
        jedisPoolConfig.setMaxWaitMillis(-1);

        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(MINEVICTTIME);

        // 最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);

        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(3);

        // 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断 (默认逐出策略)
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(MINEVICTTIME);

        // 在获取连接的时候检查有效性, 默认false
        jedisPoolConfig.setTestOnBorrow(false);

        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(false);

        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(-1);


        return jedisPoolConfig;
    }

    /**
     * redis集群配置 配置redis集群的结点及其它一些属性
     * 
     * @return
     */
    private RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setClusterNodes(getClusterNodes());
        redisClusterConfiguration.setMaxRedirects(3);
        return redisClusterConfiguration;
    }

    /**
     * redis集群节点IP和端口的添加 节点： RedisNode redisNode = new RedisNode("127.0.0.1",6379);
     * 
     * @return
     */
    private Set<RedisNode> getClusterNodes() {
        // 添加redis集群的节点
        Set<RedisNode> clusterNodes = new HashSet<RedisNode>();
        if (!"".equals(this.HOSTS) && this.HOSTS != null) {
            String[] hs = this.stringArr();
            for (String h : hs) {
                String[] arr = h.split(":");
                clusterNodes.add(new RedisNode(arr[0], Integer.parseInt(arr[1])));
            }
        }
        return clusterNodes;
    }

    @SuppressWarnings("rawtypes")
    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        // logger.info("-----------------------redisTemplate:");

        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
        // logger.info("-----------------------init cacheManager:");

        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(100);
        return cacheManager;
    }
}
