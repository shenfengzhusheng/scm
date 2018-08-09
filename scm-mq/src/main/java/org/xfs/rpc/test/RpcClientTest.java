package org.xfs.rpc.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

import org.xfs.rpc.client.RpcClient;
import org.xfs.rpc.server.Server;
import org.xfs.rpc.server.impl.ServiceCenter;
import org.xfs.rpc.service.HelloServiceI;
import org.xfs.rpc.service.impl.HelloServiceImpl;

public class RpcClientTest {
	private static int success=0;
	private static int fail=0;
	public static void main(String[] args) {
//		HelloServiceI service=RpcClient.remoteInvoke(HelloServiceI.class, new InetSocketAddress("127.0.0.1",9099));
//		System.out.println("result:"+service.sayHi(" Jeken"));
//		new Thread(new Runnable(){
//
//			@Override
//			public void run() {
//				 Server serviceServer = new ServiceCenter(8088);
//                 serviceServer.register(HelloServiceI.class, HelloServiceImpl.class);
//                 try {
//					serviceServer.start();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}				
//			} 
//	    }).start();
	
		CountDownLatch latch=new CountDownLatch(100);
		int size=800;
		for(int i=1;i<=size;i++){
			final int seq=i;
			new Thread(new Runnable(){
				@Override
				public void run() {
					latch.countDown();
					try{
						HelloServiceI service = RpcClient.remoteInvoke(HelloServiceI.class, new InetSocketAddress("localhost", 9099));
						String result=service.sayHi("test"+seq);
						if(result!=null ){
							success++;
							System.out.println(Thread.currentThread().getName()+"		result:		"+result);				
						}else{
							fail++;
						}
					}catch(Exception e){
						fail++;

					}
				}
			}).start();
			
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("------------------------------------------------------");
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("total :"+size+"		success:"+success+"			fail:"+fail);

	}

}
