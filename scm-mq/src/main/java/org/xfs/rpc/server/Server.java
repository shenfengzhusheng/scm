package org.xfs.rpc.server;

public interface Server {
	void stop();
	void start()throws Exception;
	
	void register(Class<?> serviceInterface, Class<?> impl);
	
	boolean inRunning();
	int getPort();
}
