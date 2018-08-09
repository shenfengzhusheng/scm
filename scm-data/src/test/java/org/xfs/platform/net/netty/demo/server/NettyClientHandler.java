package org.xfs.platform.net.netty.demo.server;

import org.xfs.platform.net.netty.demo.model.BaseMessage;
import org.xfs.platform.net.netty.demo.model.LoginMsg;
import org.xfs.platform.net.netty.demo.model.MessageTypeEnum;
import org.xfs.platform.net.netty.demo.model.PingMsg;
import org.xfs.platform.net.netty.demo.model.ReplyClientBody;
import org.xfs.platform.net.netty.demo.model.ReplyMsg;
import org.xfs.platform.net.netty.demo.model.ReplyServerBody;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMessage> {
	//利用写空闲发送心跳检测消息
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"send ping to server-from "+ctx.channel().id().asShortText());

            switch (e.state()) {
                case WRITER_IDLE:
                    PingMsg pingMsg=new PingMsg();
                    ctx.writeAndFlush(pingMsg);
                    System.out.println("send ping to server-from "+ctx.channel().id().asShortText());
                    break;
                default:
                    break;
            }
        }
    }
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, BaseMessage msg) throws Exception {
		MessageTypeEnum msgType=msg.getType();
        switch (msgType){
            case LOGIN:{
                //向服务器发起登录
                LoginMsg loginMsg=new LoginMsg();
                loginMsg.setPassword("yao");
                loginMsg.setUserName("robin");
                ctx.writeAndFlush(loginMsg);
            }break;
            case PING:{
                System.out.println("receive ping from server-222");
            }break;
            case ASK:{
                ReplyClientBody replyClientBody=new ReplyClientBody("client info **** !!!");
                ReplyMsg replyMsg=new ReplyMsg();
                replyMsg.setBody(replyClientBody);
                ctx.writeAndFlush(replyMsg);
            }break;
            case REPLY:{
                ReplyMsg replyMsg=(ReplyMsg)msg;
                ReplyServerBody replyServerBody=(ReplyServerBody)replyMsg.getBody();
                System.out.println("receive client msg: "+replyServerBody.getServerInfo());
            }
            default:break;
        }
        ReferenceCountUtil.release(msgType);
		
	}

}
