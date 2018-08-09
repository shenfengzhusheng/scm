package org.xfs.scm;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xfs.scm.platform.config.aop.AopConfig;
import org.xfs.scm.platform.config.data.db.DataSourceConfig;
import org.xfs.scm.platform.config.data.mongo.MongoConfig;
import org.xfs.scm.platform.config.fdfs.FdfsConfig;
import org.xfs.scm.platform.config.mq.active.ActiveMqConsumer;
import org.xfs.scm.platform.config.redis.RedisConfig;
import org.xfs.scm.platform.config.spring.SpringRootConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(classes = {SpringRootConfig.class, DataSourceConfig.class, MongoConfig.class,RedisConfig.class, ActiveMqConsumer.class,
        AopConfig.class,FdfsConfig.class})
public class BaseTest {
    protected String userName;
    protected Long begin;
    @Autowired
    private StorageClient storageClient;
    @Before
    public void beforeTest(){
        this.userName="刘治";
        this.begin=System.nanoTime();
    }
   // @Test
    public void doTest(){
        System.out.println("dddd"+this.storageClient);

        File img=new File("C:\\Users\\Administrator\\Desktop\\秀吉.jpg");
        try {
            FileInputStream fileInputStream=new FileInputStream(img);
            byte[] bytes =new byte[(int)img.length()];
            fileInputStream.read(bytes);
            NameValuePair[] meta_list = new NameValuePair[4];
            meta_list[0] = new NameValuePair("fileName", "123");
            meta_list[1] = new NameValuePair("fileLength", img.length()+"");
            meta_list[2] = new NameValuePair("fileExt", "jpg");
            meta_list[3] = new NameValuePair("fileAuthor", "Jeken.Liu");
            String[] uploadResults =this.storageClient.upload_file(bytes,"jpg",meta_list);
            System.out.println("uploadResults"+uploadResults.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }catch (MyException e){
            e.printStackTrace();
        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    @After
    public void afterTest(){
        System.out.println("测试总耗时【"+(System.nanoTime()-this.begin)+"】纳秒！");
        System.out.println("测试总耗时【"+(System.nanoTime()-this.begin)/1000/1000+"】毫秒！");
    }
}
