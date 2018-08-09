package org.xfs.scm.socket.demo1;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;


public class SocketClient {
	// 要连接的服务端IP地址和端口

	private static String host="127.0.0.1";
	private static int port = 8888;
	public static void main(String[] args)throws Exception {
//		simpleClient(3);

		  int size = 50;
	        CountDownLatch latch = new CountDownLatch(size);
	        for (int i = 0; i < size; i++) {
	        	int c=i+1;
		        System.out.println("------------------------------------------>"+c);

	            new Thread(new Runnable() {

	                @Override
	                public void run() {
                        latch.countDown();

	            		try {
	            			customClient(c);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
//		StringBuffer message=new StringBuffer();
//		for(int i=0;i<50000;i++) {
//			message.append(i).append(",");
//		}
//		byte[]sendBytes=message.toString().getBytes("UTF-8");
//		//System.out.println("content length:"+(sendBytes.length>>2));
//	//	System.out.println("content 8 length:"+(sendBytes.length));
//
//		simpleClient3(message.toString());
	}
	
	public static void simpleClient3(String message)throws Exception{
	    // 与服务端建立连接
		Socket socket=new Socket(host,port);
	    // 建立连接后获得输出流
		OutputStream outputStream=socket.getOutputStream();
	    //首先需要计算得知消息的长度
		byte[]sendBytes=message.getBytes("UTF-8");
	    //然后将消息的长度优先发送出去
		outputStream.write(sendBytes.length>>8);
		outputStream.write(sendBytes.length);
	    //然后将消息再次发送出去
		outputStream.write(sendBytes);
		outputStream.flush();
	    //==========此处重复发送一次，实际项目中为多个命名，此处只为展示用法
	    message = "第二条消息";
	    sendBytes=message.getBytes("UTF-8");
	    outputStream.write(sendBytes.length>>8);
	    outputStream.write(sendBytes.length);
	    outputStream.write(sendBytes);
	    outputStream.flush();
	    //==========此处重复发送一次，实际项目中为多个命名，此处只为展示用法
	    message = "the third message!";
	    sendBytes = message.getBytes("UTF-8");
	    outputStream.write(sendBytes.length >>8);
	    outputStream.write(sendBytes.length);
	    outputStream.write(sendBytes);    
	    
	    outputStream.close();
	    socket.close();
	}
	public static void simpleClient2()throws Exception{
	    //1、与服务端建立连接

		Socket socket=new Socket(host,port);
	    //2、建立连接后获得输出流
		OutputStream outputStream=socket.getOutputStream();
		String message="你好，服务器!";
		outputStream.write(message.getBytes("UTF-8"));
	    //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
		socket.shutdownOutput();
		
		InputStream inputStream=socket.getInputStream();	
		byte[]bytes=new byte[1024];
		int len;
		StringBuffer sb=new StringBuffer();
		while((len=inputStream.read(bytes))!=-1) {
			//注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
			sb.append(new String(bytes,0,len,"UTF-8"));
		}
	    System.out.println("get message from server: " + sb);
	    inputStream.close();
	    outputStream.close();
	    socket.close();
	}	
	public static void simpleClient(int c)throws Exception {
	
		
		// 与服务端建立连接
		Socket socket = new Socket(host, port);

		// 建立连接后获得输出流
		OutputStream outputStream = socket.getOutputStream();
		String message = "你好：服务器！"+c;
		outputStream.write(message.getBytes("UTF-8"));
		socket.shutdownOutput();
		InputStream input=socket.getInputStream();
		byte[]bytes=new byte[1024];
		int len;
		StringBuffer sb=new StringBuffer();
		while((len=input.read(bytes))!=-1) {
			sb.append(new String(bytes,0,len,"UTF-8"));
		}
		System.out.println(Thread.currentThread().getName()+"	reciver service response:"+sb);
		input.close();
		outputStream.close();
		socket.close();
	}

	
	public static void customClient(int c)throws Exception {
		// 与服务端建立连接
		Socket socket = new Socket(host, port);
	//	socket.setSoTimeout(60*1000);
		// 建立连接后获得输出流
		OutputStream outputStream = socket.getOutputStream();

		InputStream inputStream=socket.getInputStream();
		byte[]bytes;
		while(true) {
			//Thread.sleep(new Random().nextInt(5)*500);
//			String message = "你好：服务器！"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//			byte[]sendBytes=message.getBytes("UTF-8");
//			outputStream.write(sendBytes.length>>8);
//			outputStream.write(sendBytes.length);
//			outputStream.write(sendBytes);
//			outputStream.flush();
			int first = inputStream.read();
			System.out.println("first is:"+first);
			// 如果读取的值为-1 说明到了流的末尾，Socket已经被关闭了，此时将不能再去读取
			if (first == -1) {
				break;
			}
			int second = inputStream.read();
			System.out.println((first << 8)+"second is:"+second);

			int length = (first << 8) + second;
			// 然后构造一个指定长的byte数组
			bytes = new byte[length];
			// 然后读取指定长度的消息即可
			inputStream.read(bytes);
			String sb=new String(bytes,"UTF-8");
			System.out.println("get message from server: " + sb);


			// 	socket.close();
			if(sb.equals("shutdown")) {
				inputStream.close();
				socket.close();
			}
		}
	}
}
