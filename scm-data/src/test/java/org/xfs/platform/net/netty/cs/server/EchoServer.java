package org.xfs.platform.net.netty.cs.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class EchoServer {
	private final int port;
	public EchoServer(int port) {
		this.port=port;
				
	}

	public static void main(String[] args) throws Exception {

		new EchoServer(8888).start(); // 启动
	}
	public void start()throws Exception{
		EventLoopGroup bossGroup=new NioEventLoopGroup();
		EventLoopGroup workerGroup=new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap=new ServerBootstrap();
			bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			// 通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
			bootstrap.option(ChannelOption.TCP_NODELAY, true);
			// 保持长连接状态
			
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.group(bossGroup, workerGroup)							// 绑定线程池
				.channel(NioServerSocketChannel.class)						// 指定使用的channel
				.localAddress(this.port)									// 绑定监听端口
				.childHandler(new ChannelInitializer<SocketChannel>() { 	// 绑定客户端连接时候触发操作

					
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						System.out.println("报告");
						System.out.println("信息：有一客户端链接到本服务端");
						System.out.println("ip:"+channel.localAddress().getHostName());
                        System.out.println("报告完毕");
                        channel.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
                        channel.pipeline().addLast(new EchoServerHandler());// 客户端触发操作
                        channel.pipeline().addLast(new ByteArrayEncoder());
					}
					
				});
			ChannelFuture channelFuture=bootstrap.bind().sync();// 服务器异步创建绑定
			if(channelFuture.isSuccess()) {
				   System.out.println(EchoServer.class + " 启动正在监听： " + channelFuture.channel().localAddress());
			}
         //   channelFuture.channel().closeFuture().sync();// 关闭服务器通道
		} finally {
			//workerGroup.shutdownGracefully().sync(); // 释放线程池资源
			//bossGroup.shutdownGracefully().sync();
        }
		

	}
	
}
