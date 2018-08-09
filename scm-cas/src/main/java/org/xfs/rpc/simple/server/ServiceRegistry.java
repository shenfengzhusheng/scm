package org.xfs.rpc.simple.server;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.rpc.simple.Constant;

public class ServiceRegistry {
	private static final Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);
	
	private CountDownLatch latch=new CountDownLatch(1);
	
	private String registryAddress;
	
	
	public ServiceRegistry(String registryAddress){
		this.registryAddress=registryAddress;
		
	}
	
	public void register(String data){
		if(data!=null){
			ZooKeeper zk=connectServer();
			if(zk!=null){
				createNode(zk,data);
			}
		}
	}
	
	private ZooKeeper connectServer(){
		ZooKeeper zk=null;
		try{
			zk=new ZooKeeper(registryAddress,Constant.ZK_SESSION_TIMEOUT,new Watcher(){

				@Override
				public void process(WatchedEvent event) {
					 // 判断是否已连接ZK,连接后计数器递减.
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
					
				}
			});
			// 若计数器不为0,则等待.
			latch.await();
		}catch(Exception e){
			logger.error("",e);
		}
		return zk;
	}
	 
	private void createNode(ZooKeeper zk,String data){
		try{
			byte[]bytes=data.getBytes();
			
			String path=zk.create(Constant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			logger.info("create zookeeper node ({} => {})", path, data);

		}catch(Exception e){
			logger.error("",e);
		}
		
	}
}
