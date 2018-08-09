package org.xfs.platform.net.netty.cs.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.nio.charset.Charset;

public class EchoClient {
	private final String host;
	private final int port;
	public EchoClient(String host,int port) {
		this.host=host;
		this.port=port;
	}
	public void start()throws Exception{
		EventLoopGroup group=new NioEventLoopGroup();
		try {
			Bootstrap bootstrap=new Bootstrap();
			bootstrap.group(group)// 注册线程池
				.channel(NioSocketChannel.class)// 使用NioSocketChannel来作为连接用的channel类
				.remoteAddress(host, port)// 绑定连接端口和host信息
				.handler(new ChannelInitializer<SocketChannel>() {// 绑定连接初始化器
					@Override
					protected void initChannel(SocketChannel channel)throws Exception{
                        System.out.println("正在连接中...");
                        channel.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
                        channel.pipeline().addLast(new EchoClientHandler());
                        channel.pipeline().addLast(new ByteArrayEncoder());
                        channel.pipeline().addLast(new ChunkedWriteHandler());
					}
				});
			ChannelFuture channelFuture=bootstrap.connect().sync();// 异步连接服务器
            System.out.println("服务端连接成功..."); // 连接完成
           // channelFuture.channel().closeFuture().sync(); // 异步等待关闭连接channel
           // System.out.println("连接已关闭.."); // 关闭完成

		}finally {
			System.out.println("----------------------");
          //  group.shutdownGracefully().sync(); // 释放线程池资源
        }
	}
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
        new EchoClient("127.0.0.1", 8888).start(); // 连接127.0.0.1/65535，并启动

	}

}
