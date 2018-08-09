package org.xfs.platform.net.netty.socket;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.xfs.platform.net.netty.socket.model.RequestInfoVO;
import org.xfs.platform.net.netty.socket.util.SocketUtil;

public class CustomSocketClient {
	private static int port=10000;
	private static int protocol_number=0X1243162D;
	public static void main(String[] args)throws Exception {
		int size=1;
//		CountDownLatch latch=new CountDownLatch(size);
//		for(int i=0;i<size;i++) {
//			final int c=i+1;
//			new Thread(new Runnable() {
//
//				@Override
//				public void run() {
//                    latch.countDown();
//					System.out.println("start ["+c+"]thread");
//
//					try {
//						CustomSocketClient.customSend(c);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//				}
//			}).start();
//		}
//        latch.await();
		CustomSocketClient.customSend(885);

		//byte[]requestId_b=UUID.randomUUID().toString().replaceAll("-", "").getBytes();
		//System.out.println(requestId_b.length);
	}

	@SuppressWarnings("resource")
	public static void customSend(int thread)throws Exception{
		Socket socket=new Socket("127.0.0.1",port);
		socket.setKeepAlive(true);

		OutputStream out=socket.getOutputStream();
		InputStream input=socket.getInputStream();
		///connect(out,input,thread);
		while(true) {
			TimeUnit.SECONDS.sleep(2);
			byte[]protocol_b=int2ByteArrays(protocol_number);//协议
			String requestId=UUID.randomUUID().toString().replaceAll("-", "");
			byte[]requestId_b=requestId.getBytes();
			//System.out.println("requestId is:"+requestId);
			byte version_version=1;//版本
			byte opt_type=(byte)new Random().nextInt(8);//操作类型
			byte[]clientId_b=int2ByteArrays(thread);//客户端号
			StringBuffer content=new StringBuffer("测试数据");//发送内容
			content.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			content.append("----");
			for(int i=0;i<3;i++) {
				content.append("a");
			}
			content.append("刘金财");
			byte[]sendBytes=content.toString().getBytes("UTF-8");
			byte[]length_b=int2ByteArrays(sendBytes.length);//内容长度
			byte[]sequence_b=int2ByteArrays(new Random().nextInt(900));//校验位
			ByteArrayOutputStream baos = new ByteArrayOutputStream(30);
			baos.write(protocol_b,0,protocol_b.length);
			baos.write(requestId_b,0,requestId_b.length);
			baos.write(version_version);
			//  baos.write(opt_type);
			baos.write(opt_type);
			baos.write(clientId_b, 0, clientId_b.length);
			baos.write(length_b, 0, length_b.length);
			baos.write(sendBytes, 0, sendBytes.length);
			baos.write(sequence_b, 0, sequence_b.length);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);

			bufferedOutputStream.write(baos.toByteArray());
			bufferedOutputStream.flush();
			readStream(input,requestId);

			// socket.close();
			//  break;
		}
		//  socket.close();
	}

	public static void connect(OutputStream out,InputStream input,int thread)throws Exception{
		byte[]protocol_b=int2ByteArrays(protocol_number);//协议
		String requestId=UUID.randomUUID().toString().replaceAll("-", "");
		byte[]requestId_b=requestId.getBytes();
		//System.out.println("requestId is:"+requestId);
		byte version_version=1;//版本
		byte opt_type=(byte)new Random().nextInt(8);//操作类型
		byte[]clientId_b=int2ByteArrays(thread);//客户端号
		StringBuffer content=new StringBuffer("登陆");//发送内容
		content.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		byte[]sendBytes=content.toString().getBytes("UTF-8");
		byte[]length_b=int2ByteArrays(sendBytes.length);//内容长度
		byte[]sequence_b=int2ByteArrays(new Random().nextInt(900));//校验位
		ByteArrayOutputStream baos = new ByteArrayOutputStream(30);
		baos.write(protocol_b,0,protocol_b.length);
		baos.write(requestId_b,0,requestId_b.length);
		baos.write(version_version);
		//  baos.write(opt_type);
		baos.write(1);
		baos.write(clientId_b, 0, clientId_b.length);
		baos.write(length_b, 0, length_b.length);
		baos.write(sendBytes, 0, sendBytes.length);
		baos.write(sequence_b, 0, sequence_b.length);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);

		bufferedOutputStream.write(baos.toByteArray());
		bufferedOutputStream.flush();
		readStream(input,requestId);
	}

	public static byte[] int2ByteArrays(int i) {
		byte[] result = new byte[4];
		result[0] = (byte) ((i >> 24) & 0xFF);
		result[1] = (byte) ((i >> 16) & 0xFF);
		result[2] = (byte) ((i >> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);
		return result;
	}

	public static void readStream(InputStream inputStream,String requestId)throws Exception {
		if(inputStream!=null) {

			byte[]protocol_bytes=new byte[4];//报文头
			inputStream.read(protocol_bytes);

			int protocol=SocketUtil.byteArrayToInt(protocol_bytes);

			if(protocol_number!=protocol) {
				System.out.println("protocol is :"+protocol);
				return ;
			}
			byte[]requestId_bytes=new byte[32];//requestId
			inputStream.read(requestId_bytes);
			String returnId=new String(requestId_bytes);

			System.out.println("requestId is:"+returnId);
			int version=inputStream.read();
			int type=inputStream.read();

			byte[]clientId_bytes=new byte[4];
			inputStream.read(clientId_bytes);
			int clientId=SocketUtil.byteArrayToInt(clientId_bytes);

			byte[]length_bytes=new byte[4];
			inputStream.read(length_bytes);
			int length=SocketUtil.byteArrayToInt(length_bytes);

			byte[]content_bytes=new byte[length];
			inputStream.read(content_bytes);
			String content=new String(content_bytes,"UTF-8");

			byte[]sequence_bytes=new byte[4];
			inputStream.read(sequence_bytes);
			int sequence=SocketUtil.byteArrayToInt(sequence_bytes);
			RequestInfoVO vo=new RequestInfoVO();
			vo.setBody(content);
			vo.setClientId(clientId);
			vo.setLength(length);
			vo.setProtocol(protocol);
			vo.setRequestId(requestId);
			vo.setSequence(sequence);
			vo.setType((byte)type);
			vo.setVersion((byte)version);
			System.out.println("response is:"+vo);


		}
	}




















	public static void nativeSocket()throws Exception {
		Socket socket=new Socket("127.0.0.1",port);
		OutputStream out=socket.getOutputStream();
		//InputStream input=socket.getInputStream();
		//while(true) {
		TimeUnit.SECONDS.sleep(3);
		//
		byte[]send_p="0X1243162D".getBytes("UTF-8");
		System.out.println((send_p.length>>8)+"	protol length is:"+send_p.length);
		out.write(0X1243162D);
		//	out.write(0X1243162D);//协议号
		out.write(new Byte("13"));//version
		out.write(new Byte("9"));//type
		int customId=new Random().nextInt(8000);
		//	out.write(customId>>8);//设备号
		out.write((customId+"").getBytes("UTF-8"));
		String content="佛山我想念了！";
		content+=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		byte[]sendBytes=content.getBytes("UTF-8");
		//	out.write(sendBytes.length>>8);
		out.write(sendBytes.length);
		out.write(sendBytes);
		out.write(18859);
		out.flush();

		//}
	}



}
