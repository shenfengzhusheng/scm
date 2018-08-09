package org.xfs.rpc.server.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.text.SimpleDateFormat;

import org.xfs.rpc.Constant;

public class ServerTask implements Runnable {
	Socket client=null;
	public ServerTask(Socket client){
		this.client=client;
	}
	@Override
	public void run() {
		//System.out.println("now is 		"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new java.util.Date())+"		run thread is:			"+Thread.currentThread().getName());
		ObjectInputStream input=null;
		ObjectOutputStream output=null;
		try{
			//2、将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果
			InputStream inputStream=client.getInputStream();
			input=new ObjectInputStream(inputStream);
			String serviceName=input.readUTF();
			String methodName=input.readUTF();
			Object parameterType=input.readObject();
			Object parameter=input.readObject();
			//System.out.println(serviceName+"parameterType is :"+parameterType.getClass().getName());
			//System.out.println(serviceName+"parameters is :"+parameter);

			Class<?>[]parameterTypes =(Class<?>[])parameterType;
			Object[]arguments=(Object[])parameter;
			//System.out.println("reqeust args:"+arguments);
			Class<?>serviceClass=Constant.serviceRegistry.get(serviceName);
			//System.out.println("serviceClass:"+serviceClass);
			if(serviceClass==null){
				throw new ClassNotFoundException(serviceName+"not found");
			}
			Method method=serviceClass.getMethod(methodName, parameterTypes);
			Object result=method.invoke(serviceClass.newInstance(),arguments);
			
			//3、将执行结果反序列化，通过Socket反馈给客户端
			output=new ObjectOutputStream(client.getOutputStream());
			output.writeObject(result);
		//	output.flush();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(output!=null){
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(client!=null){
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
