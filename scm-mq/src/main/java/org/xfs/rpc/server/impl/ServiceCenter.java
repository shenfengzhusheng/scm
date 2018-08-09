package org.xfs.rpc.server.impl;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.xfs.rpc.Constant;
import org.xfs.rpc.server.Server;
import org.xfs.rpc.server.thread.ServerTask;
import org.xfs.rpc.service.HelloServiceI;
import org.xfs.rpc.service.impl.HelloServiceImpl;

public class ServiceCenter implements Server {

	
	private static ExecutorService executor=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	
	private static boolean isRunning=false;
	
	private static int port;
	
	public ServiceCenter(int port){
		ServiceCenter.port=port;
	}

	@Override
	public void stop() {
		isRunning=false;
		executor.shutdown();
	}

	@Override
	public void start() throws Exception {
		ServerSocket server=new ServerSocket();
		server.bind(new InetSocketAddress(port));
		System.out.println("server start");
		try{
			while(true){
				//监听客户端TCP连接，接到TCP连接后将其封装 成task,由线程池执行
				executor.execute(new ServerTask(server.accept()));
				//System.out.println("server starting");

			}
		}finally{
			server.close();
		}
	}

	@Override
	public void register(Class<?> serviceInterface, Class<?> impl) {
		Constant.serviceRegistry.put(serviceInterface.getName(), impl);
	}

	@Override
	public boolean inRunning() {
		return isRunning;
	}

	@Override
	public int getPort() {
		return port;
	}
	
	
	public static void main(String[]args){
		System.out.println(Runtime.getRuntime().availableProcessors());
		Server serviceServer=new ServiceCenter(9099);
		serviceServer.register(HelloServiceI.class, HelloServiceImpl.class);
		try {
			serviceServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
