package org.xfs.platform.net.netty.demo.server;

import org.xfs.platform.net.netty.demo.model.AskMsg;
import org.xfs.platform.net.netty.demo.model.BaseMessage;
import org.xfs.platform.net.netty.demo.model.LoginMsg;
import org.xfs.platform.net.netty.demo.model.MessageTypeEnum;
import org.xfs.platform.net.netty.demo.model.NettyChannelMap;
import org.xfs.platform.net.netty.demo.model.PingMsg;
import org.xfs.platform.net.netty.demo.model.ReplyClientBody;
import org.xfs.platform.net.netty.demo.model.ReplyMsg;
import org.xfs.platform.net.netty.demo.model.ReplyServerBody;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMessage> {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //channel失效，从Map中移除
		NettyChannelMap.remove((SocketChannel)ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, BaseMessage baseMsg) throws Exception {
		 if(MessageTypeEnum.LOGIN.equals(baseMsg.getType())){
	            LoginMsg loginMsg=(LoginMsg)baseMsg;
	            if("robin".equals(loginMsg.getUserName())&&"yao".equals(loginMsg.getPassword())){
	                //登录成功,把channel存到服务端的map中
	                NettyChannelMap.add(loginMsg.getClientId(),(SocketChannel)ctx.channel());
	                System.out.println("client"+loginMsg.getClientId()+" 登录成功");
	            }
	        }else{
	            if(NettyChannelMap.get(baseMsg.getClientId())==null){
	                    //说明未登录，或者连接断了，服务器向客户端发起登录请求，让客户端重新登录
	                    LoginMsg loginMsg=new LoginMsg();
	                    ctx.channel().writeAndFlush(loginMsg);
	            }
	        }
	        switch (baseMsg.getType()){
	            case PING:{
	                PingMsg pingMsg=(PingMsg)baseMsg;
	                PingMsg replyPing=new PingMsg();

	                NettyChannelMap.get(pingMsg.getClientId()).writeAndFlush(replyPing);
	            }break;
	            case ASK:{
	                //收到客户端的请求
	                AskMsg askMsg=(AskMsg)baseMsg;
				//	System.out.println("recevier from["+askMsg.getClientId()+"] message:"+askMsg.getParams().getAuth());
					ReplyMsg replyMsg=new ReplyMsg();
					if("authToken".equals(askMsg.getParams().getAuth())){
	                    replyMsg.setBody(new ReplyServerBody("server info $$$$!!!"));
	                }else{
						replyMsg.setBody(new ReplyServerBody("server receive   from["+askMsg.getClientId()+"] message :"+askMsg.getParams().getAuth()));
					}
					NettyChannelMap.get(askMsg.getClientId()).writeAndFlush(replyMsg);
	            }break;
	            case REPLY:{
	                //收到客户端
	                ReplyMsg replyMsg=(ReplyMsg)baseMsg;
	                ReplyClientBody clientBody=(ReplyClientBody)replyMsg.getBody();
	                System.out.println("receive client msg: "+clientBody.getClientInfo());
	            }break;
	            default:break;
	        }
	        ReferenceCountUtil.release(baseMsg);
	}

}
