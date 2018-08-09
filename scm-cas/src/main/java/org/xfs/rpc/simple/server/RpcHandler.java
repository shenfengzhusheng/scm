package org.xfs.rpc.simple.server;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.rpc.simple.model.RpcRequest;
import org.xfs.rpc.simple.model.RpcResponse;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

/**
 *  RPC服务端:请求处理过程
 * @author xixingyingzhongdui
 *
 */
public class RpcHandler extends SimpleChannelInboundHandler<RpcRequest> {
	
	private static final Logger logger=LoggerFactory.getLogger(RpcHandler.class);
	
	private final Map<String,Object>handlerMap;
	
	public RpcHandler(Map<String,Object>handlerMap){
		this.handlerMap=handlerMap;
	} 
	@Override
	protected void channelRead0(final ChannelHandlerContext ctx, RpcRequest request) throws Exception {
		RpcResponse response=new RpcResponse();
		response.setReqeustId(UUID.randomUUID().toString().replaceAll("-", ""));
		try{
			Object result=handler(request);
			response.setResult(result);
		}catch(Throwable t){
			response.setError(t);
		}
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
	
	private Object handler(RpcRequest request)throws Throwable{
		String className=request.getClassName();
		Object serviceBean=handlerMap.get(className);
		Class<?>serviceClass=serviceBean.getClass();
		String methodName=request.getMethodName();
		Class<?>[]parameterType=request.getParameterTypes();
		Object[]parameters=request.getParameters();
		
		FastClass serviceFastClass=FastClass.create(serviceClass);
		FastMethod serviceFastMethod=serviceFastClass.getMethod(methodName, parameterType);
		return serviceFastMethod.invoke(serviceBean, parameters);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
		logger.error("server caught exception", cause);
		ctx.close();
	}
}
