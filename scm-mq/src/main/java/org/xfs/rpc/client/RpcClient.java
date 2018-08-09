package org.xfs.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RpcClient<T> {
	@SuppressWarnings("unchecked")
	public static <T>T remoteInvoke(final Class<?>serviceInterface,final InetSocketAddress addr){
		//1、将本地的接口接口调用转换成JDK动态代理，在动态代理中实现接口运程调用
		return (T)Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, 
				new InvocationHandler(){

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Socket socket=null;
						//调用是先输出参数等待反回，所以这里先定义输出，再定义输入
						ObjectOutputStream output=null;
						ObjectInputStream input=null;
						try{
							//2、创建Socket客户端，根据指定的地址连接远程服务提供者
							socket=new Socket();
							socket.connect(addr);
							
							//3、将远程服务调用所需要的接口类，方法名,参数列表等编码后发送到服务提供者
							output=new ObjectOutputStream(socket.getOutputStream());
							output.writeUTF(serviceInterface.getName());
							output.writeUTF(method.getName());
							
							output.writeObject(method.getParameterTypes());
							output.writeObject(args);
							
							
							//4、同步阻塞服务器应答，获取应答后返回
							//socket.setSoTimeout(1000*150);
							input=new ObjectInputStream(socket.getInputStream());
							return input.readObject();

						}finally{
							if(socket!=null){
								socket.close();
							}
							if(output!=null){
								output.close();
							}
							if(input!=null){
								input.close();
							}
							
							
						}
					}
			
		});
	}
}
