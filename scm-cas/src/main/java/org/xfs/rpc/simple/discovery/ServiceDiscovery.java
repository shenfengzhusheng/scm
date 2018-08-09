package org.xfs.rpc.simple.discovery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.rpc.simple.Constant;

/**
 * 服务发现:连接ZK,添加watch事件
 * @author xixingyingzhongdui
 *
 */
public class ServiceDiscovery {
	private static final Logger logger=LoggerFactory.getLogger(ServiceDiscovery.class);
	
	private CountDownLatch latch=new CountDownLatch(1);
	
	private volatile List<String>dataList=new ArrayList<String>();
	private String registryAddress;
	public ServiceDiscovery(String registryAddress){
		this.registryAddress=registryAddress;
		ZooKeeper zk=connectServer();
		if(zk!=null){
			watchNode(zk);
		}
	}
	/**
	 * 建立zk连接
	 * project:rpc-demo
	 * @return
	 * author:xifengshan
	 * date:2017年10月21日下午4:11:50
	 */
	private ZooKeeper connectServer(){
		ZooKeeper zk=null;
		try{
			zk=new ZooKeeper(registryAddress,Constant.ZK_SESSION_TIMEOUT,new Watcher(){

				@Override
				public void process(WatchedEvent event) {
					if(event.getState()==Event.KeeperState.SyncConnected){
						latch.countDown();
					}
				}
				
			});
			latch.await();
		}catch(Exception e){
			logger.error("",e);
		}
		return zk;
	}
	
	private void watchNode(final ZooKeeper zk){
		try{
			List<String>nodeList=zk.getChildren(Constant.ZK_REGISTRY_PATH, new Watcher(){

				@Override
				public void process(WatchedEvent event) {
					logger.info("registery watch:"+event.toString());
					if(event.getType()==Event.EventType.NodeChildrenChanged){
						watchNode(zk);
					}
				}
				
			});
			
			List<String>dataList=new ArrayList<String>();
			for(String node:nodeList){
				byte[]bytes=zk.getData(Constant.ZK_REGISTRY_PATH+"/"+node, false, null);
				dataList.add(new String(bytes));
			}
			logger.debug("node data:{}",dataList);
			this.dataList=dataList;
		}catch(Exception e){
			logger.error("监听错误 ",e);
		}
	}
	public String discover(){
		String data=null;
		int size=dataList.size();
		if(size>0){
			if(size==1){
				data=dataList.get(0);
				logger.info("using only data:{}",data);
			}else{
				data=dataList.get(ThreadLocalRandom.current().nextInt(size));
				logger.info("using random data: {}", data);
						
			}
		}
		return data;
	}
}
