package org.xfs.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.xfs.api.model.CustomProtocol;

public class NettyClientHandler extends SimpleChannelInboundHandler<CustomProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CustomProtocol msg) throws Exception {
        System.out.println("客户端获取信息："+msg);
        CustomProtocol req = new CustomProtocol();
        req.setSequence(msg.getSequence());
        req.setType(msg.getType());
        req.setClientId(msg.getClientId());
        req.setSequence(msg.getSequence()+1);
        req.setRequestId(msg.getRequestId());
        if (2 == msg.getType()) {
            req.setBody("client");
            ctx.channel().writeAndFlush(req);
        } else if (3 == msg.getType()) {
            req.setBody("zpksb");
            ctx.channel().writeAndFlush(req);
        }

    }

}