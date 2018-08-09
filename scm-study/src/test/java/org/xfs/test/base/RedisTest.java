package org.xfs.test.base;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisTest {
	
	private final static Logger logger=LoggerFactory.getLogger(RedisTest.class);
	
	private static String hosts="127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002";
	public static void main(String[] args) {
		JedisCluster jc=getJedisCulster();
		logger.info("===="+jc);
		// TODO Auto-generated method stub
		jc.set("tt", "JedisCluster");
		System.out.println(jc.get("tt"));
	}
	
	
	public static JedisCluster getJedisCulster(){
      if(!"".equals(hosts) && hosts!=null){
      	
      	GenericObjectPoolConfig redisPoolConfig = new GenericObjectPoolConfig();
          redisPoolConfig.setMaxWaitMillis(1000);
          redisPoolConfig.setMaxTotal(20000);
          redisPoolConfig.setMaxIdle(20);
          redisPoolConfig.setTestOnBorrow(true);
          
          Set<HostAndPort>hostss=new HashSet<HostAndPort>();
          String[] hs=hosts.split(" ");
          logger.info("-----------------------hs:"+hs.length);
          for(String h:hs){
          	String[]arr=h.split(":");
          	hostss.add(new HostAndPort(arr[0],Integer.parseInt(arr[1])));
          }
          return new JedisCluster(hostss,redisPoolConfig);
      }
      return null;
//
	}
}
