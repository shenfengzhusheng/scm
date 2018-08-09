package org.xfs.rpc.test;


import org.xfs.rpc.server.Server;
import org.xfs.rpc.server.impl.ServiceCenter;
import org.xfs.rpc.service.HelloServiceI;
import org.xfs.rpc.service.impl.HelloServiceImpl;

public class RpcServerStart {

	public static void main(String[] args) {
		Server serviceServer=new ServiceCenter(9099);
		serviceServer.register(HelloServiceI.class, HelloServiceImpl.class);
		try {
			serviceServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
