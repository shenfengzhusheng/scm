package org.xfs.scm.mongo;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * mogodb 配置
 * MongoDB工具类 Mongo实例代表了一个数据库连接池，即使在多线程的环境中，一个Mongo实例对我们来说已经足够了<br>
 * 注意Mongo已经实现了连接池，并且是线程安全的。 <br>
 * 设计为单例模式， 因 MongoDB的Java驱动是线程安全的，对于一般的应用，只要一个Mongo实例即可，<br>
 * Mongo有个内置的连接池（默认为10个） 对于有大量写和读的环境中，为了确保在一个Session中使用同一个DB时，<br>
 * Created by 神风逐胜 on 2017/10/14 0014.16:08
 * version:1.0
 */
public class MongoDemo {
    private static final Logger logger= LoggerFactory.getLogger(MongoDemo.class);
    private static MongoClient mongoClient;
    static{
        mongoClient=MongoDemo.initMongo();
    }
    public static MongoClient initMongo(){
       try{

           ServerAddress serverAddress=new ServerAddress("192.168.40.130",27017);
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
           return new MongoClient(seeds,builder.build());

         //  MongoClientOptions mongoClientOptions=new MongoClientOptions();
           // mongoClient.
       }catch(Exception e){
           logger.error("init mongo 配置异常:",e);
        }
        return null;
    }
    /**
     * 获取DB实例 - 指定DB
     *
     * @param dataBaseName
     * @return
     */
    public MongoDatabase getDB(String dataBaseName) {
        if (dataBaseName != null && !"".equals(dataBaseName)) {
            MongoDatabase db=mongoClient.getDatabase(dataBaseName);
            System.out.println("db:"+db.getName());
            return db;
        }
        return null;
    }
    public static void main(String []args)throws Exception{
        MongoDemo mc = new MongoDemo();
//        //创建集合 参数为 “集合名称”
//       // mc.getDb("test").createCollection("collectionName");
      //   mc.getDbs();
         //mc.listCollections("test");
       // mc.insert("test");
        mc.list("test","runob").forEach(printBlock);
//       //  System.out.println(mc.getDb("test"));

    }

    private void listCollections(String dbName){
        MongoDatabase db=this.getDB(dbName);
        MongoIterable<String> list =db.listCollectionNames();
        for(String str:list){
            System.out.println(str);
        }
    }
    public boolean insert(String dbName){
        MongoDatabase db=this.getDB(dbName);
        MongoCollection<Document>collection=db.getCollection("runob");
        Document document = new Document("_id", 1999).append("title", "MongoDB Insert Demo")
                .append("description","database")
                .append("likes", 30)
                .append("by", "yiibai point")
                .append("url", "http://www.yiibai.com/mongodb/");
        collection.insertOne(document);
        return true;
    }

    public  FindIterable<Document> list(String dbName,String collectionName){
        MongoCollection<Document>collection=this.getDB(dbName).getCollection(collectionName);
        FindIterable<Document> list= collection.find();
        return list;
    }
    public void getDbs(){
        List<String> list=this.mongoClient.getDatabaseNames();

        for(String dbName:list){
            System.out.println("db:"+dbName);
        }
        try{
            this.mongoClient.getDB("test").getCollectionNames();
        }catch (Exception e){
            logger.error("错误：{}",e);
        }
    }

    static Block<Document> printBlock = new Block<Document>() {
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };

}
