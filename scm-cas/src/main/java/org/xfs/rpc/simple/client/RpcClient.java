package org.xfs.rpc.simple.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.rpc.simple.model.RpcRequest;
import org.xfs.rpc.simple.model.RpcResponse;
import org.xfs.rpc.simple.util.RpcDecoder;
import org.xfs.rpc.simple.util.RpcEncoder;

public class RpcClient extends SimpleChannelInboundHandler<RpcResponse> {
	private static final Logger logger= LoggerFactory.getLogger(RpcClient.class);
	
	private String host;
	private int port;
	
	private RpcResponse response;
	private final Object obj=new Object();
	
	public RpcClient(String host,int port){
		this.host=host;
		this.port=port;
	}
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
		this.response=response;
		synchronized(obj){
			obj.notifyAll();// 收到响应，唤醒线程
		}
	}
	public RpcResponse send(RpcRequest request)throws Exception{
		EventLoopGroup group=new NioEventLoopGroup();

		try{
			Bootstrap bootstrap=new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline()
									.addLast(new RpcEncoder(RpcRequest.class))// 将 RPC 请求进行编码（为了发送请求）
									.addLast(new RpcDecoder(RpcResponse.class))// 将 RPC 响应进行解码（为了处理响应）
									.addLast(RpcClient.this); // 使用 RpcClient 发送 RPC 请求
						}
					}).option(ChannelOption.SO_KEEPALIVE,true);
			
			ChannelFuture future=bootstrap.connect(host, port).sync();
			future.channel().writeAndFlush(request).sync();
			synchronized(obj){
				obj.wait();// 未收到响应，使线程等待
			}
			
			if(response!=null){
				future.channel().closeFuture().sync();
			}
			
			return response;
			
		} finally {
			group.shutdownGracefully();
		}
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause)throws Exception{
		logger.error("client caught exception",cause);
		ctx.close();
	}

}
