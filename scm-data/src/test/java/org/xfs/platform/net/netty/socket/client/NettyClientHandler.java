package org.xfs.platform.net.netty.socket.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.xfs.platform.net.netty.socket.model.RequestInfoVO;

public class NettyClientHandler extends SimpleChannelInboundHandler<RequestInfoVO> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestInfoVO msg) throws Exception {
        System.out.println(msg.getBody());
        RequestInfoVO req = new RequestInfoVO();
        req.setSequence(msg.getSequence());
        req.setType(msg.getType());
        if (2 == msg.getType()) {
            req.setBody("client");
            ctx.channel().writeAndFlush(req);
        } else if (3 == msg.getType()) {
            req.setBody("zpksb");
            ctx.channel().writeAndFlush(req);
        }

    }

}