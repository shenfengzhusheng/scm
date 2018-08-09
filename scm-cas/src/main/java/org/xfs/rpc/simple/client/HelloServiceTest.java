package org.xfs.rpc.simple.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xfs.rpc.simple.model.Item;
import org.xfs.rpc.simple.service.HelloService;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-zk-rpc-client.xml")
public class HelloServiceTest {
	
	@Autowired
	private RpcProxy rpcProxy;
	
	// @Test
    public void helloTest() {
        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello("World");
        System.out.println("result:"+result);
       // Assert.assertEquals("Hello! World", result);
    }
    @Test
    public void testFindItem(){
        int size=100;
        CountDownLatch latch=new CountDownLatch(size);

        for(int i=1;i<=size;i++){

            new Thread(new Runnable() {
                @Override
                public void run() {
                 //   System.out.println("Now start ["+latch.getCount()+"]thread!"+"      "+rpcProxy);
                  //  System.out.println(helloService+"Now start ["+latch.getCount()+"]thread!"+"      "+rpcProxy);
                    HelloService helloService = rpcProxy.create(HelloService.class);

                    Item reuslt=helloService.findById(Long.parseLong(new Random().nextInt(size)+""));
                    System.out.println(Thread.currentThread().getName()+"       result:     "+reuslt);
                    latch.countDown();
//                    try {
//                        Thread.sleep(300);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }).start();

        }
    //    HelloService helloService = rpcProxy.create(HelloService.class);
       // System.out.println(helloService+"Now start ["+latch.getCount()+"]thread!"+"      "+rpcProxy);

      //  String reuslt=helloService.findById(Long.parseLong(new Random().nextInt(size)+"")).toString();
      //  Item reuslt=helloService.findById(23L);
       // System.out.println("result:"+reuslt);
        System.out.println("-----------------begin-------------------------------------");

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------end-------------------------------------");

    }
}
