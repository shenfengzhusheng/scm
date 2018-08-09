package org.xfs.rpc.simple.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.xfs.rpc.simple.MemoryCache;
import org.xfs.rpc.simple.annotation.RpcService;
import org.xfs.rpc.simple.model.Item;
import org.xfs.rpc.simple.model.RpcRequest;
import org.xfs.rpc.simple.model.RpcResponse;
import org.xfs.rpc.simple.util.RpcDecoder;
import org.xfs.rpc.simple.util.RpcEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 启动并注册服务
 * @author xixingyingzhongdui
 *
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {
	private static final Logger logger=LoggerFactory.getLogger(RpcServer.class);
	
	private String serverAddress;
	private ServiceRegistry serviceRegistry;
	
	//// 存放接口名与服务对象之间的映射关系
	private Map<String,Object>handlerMap=new HashMap<String,Object>();
	
	public RpcServer(String serverAddress){
		this.serverAddress=serverAddress;
	}
	public RpcServer(String serverAddress,ServiceRegistry serviceRegistry){
		this.serverAddress=serverAddress;
		this.serviceRegistry=serviceRegistry;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		try{
			if(MemoryCache.items==null ){
				MemoryCache.items=new ArrayList<Item>();
			}
			String date=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
			for(int i=0;i<50;i++){
				//Item(Long itemId, String itemCode, String itemName, String specs, String memo, String pd)
				Item item=new Item(new Long(i),"a"+i,"商品"+i,"通品","b就测试",date);
				MemoryCache.items.add(item);
			}
		}catch(Exception e){
			logger.error("init item error:{}",e);
		}
		EventLoopGroup bossGroup=new NioEventLoopGroup();
		EventLoopGroup workerGroup=new NioEventLoopGroup();
		try{
			ServerBootstrap bootstrap=new ServerBootstrap();
			bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					channel.pipeline()
						.addLast(new RpcDecoder(RpcRequest.class)) // 将 RPC 请求进行解码（为了处理请求）
						.addLast(new RpcEncoder(RpcResponse.class))// 将 RPC 响应进行编码（为了返回响应）
						.addLast(new RpcHandler(handlerMap));// 处理 RPC 请求
				}
				
			}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
			
			String[]array=serverAddress.split(":");
			String host=array[0];
			int port=Integer.parseInt(array[1]);
			
			ChannelFuture future=bootstrap.bind(host, port);
			logger.debug("------------->server started on port {}", port);
			if(serviceRegistry!=null){
				serviceRegistry.register(serverAddress);// 注册服务地址
			}
			future.channel().closeFuture().sync();
		}finally{
			workerGroup.shutdownGracefully();
	        bossGroup.shutdownGracefully();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		Map<String,Object>serviceBeanMap=ctx.getBeansWithAnnotation(RpcService.class);
		if(MapUtils.isNotEmpty(serviceBeanMap)){
			for(Object serviceBean:serviceBeanMap.values()){
				String interfaceName=serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
				handlerMap.put(interfaceName, serviceBean);
				
			}
			
		}
	}

}
