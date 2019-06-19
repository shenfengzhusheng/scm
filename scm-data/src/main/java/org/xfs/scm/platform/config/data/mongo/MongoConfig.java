package org.xfs.scm.platform.config.data.mongo;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 神风逐胜 on 2017/10/14 0014.21:30
 * version:1.0
 */

public class MongoConfig {
    private static final Logger logger= LoggerFactory.getLogger(MongoConfig.class);

    @Value("${mongo.dbname}")
    private String DB_NAME;

    @Value("${mongo.password}")
    private String MONGODB_PWD;

    @Value("${mongo.username}")
    private String MONGODB_USERNAME;

    @Value("${mongo.host}")
    private String MONGODB_URL;

    @Value("${mongo.port}")
    private String MONGODB_PORT;
    @Bean
    public MongoTemplate mongoTemplate(){
        MongoTemplate mongoTemplate=new MongoTemplate(new SimpleMongoDbFactory(this.initMongoClient(),DB_NAME));
        return mongoTemplate;
    }

    /**
     * 初始化mongodb客户端连接
     * @return
     */
    @SuppressWarnings("deprecation")
	private MongoClient initMongoClient(){
        try{
            //UserCredentials userCredentials=new UserCredentials(MONGODB_USERNAME,MONGODB_PWD);
            MongoCredential credential= MongoCredential.createCredential(MONGODB_USERNAME,DB_NAME,MONGODB_PWD.toCharArray());
            ServerAddress serverAddress=new ServerAddress(MONGODB_URL,Integer.parseInt(MONGODB_PORT));
            List<ServerAddress> seeds=new ArrayList<ServerAddress>();
            seeds.add(serverAddress);;

            MongoClientOptions.Builder builder=MongoClientOptions.builder();
            builder.connectionsPerHost(300);// 连接池设置为300个连接,默认为100
            builder.connectTimeout(30*1000);// 连接超时，推荐>3000毫秒
            builder.maxWaitTime(15*1000);
            builder.socketTimeout(60*1000);// 套接字超时时间，0无限制
            builder.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
            builder.threadsAllowedToBlockForConnectionMultiplier(5);
            builder.writeConcern(WriteConcern.SAFE);
            //初始化连接
            return new MongoClient(seeds,credential,builder.build());

        }catch(Exception e){
            logger.error("init mongo 配置异常:",e);
        }
        return null;
    }

}
