package org.xfs.platform.net.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx,Object msg)throws Exception{
		ByteBuf in = (ByteBuf) msg;
		try {
		    //写给客户端  
            String response = "server receiver: message"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());  
            ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));  
            byte[] data = new byte[in.readableBytes()];  
            in.readBytes(data);  
            String request = new String(data, "utf-8");  
//			while (in.isReadable()) {
//				System.out.print((char) in.readByte());
//				System.out.flush();
//			}
            System.out.println("Server: " + request);  
        
           // ctx.flush();
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}
    @Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause)throws Exception{
		cause.printStackTrace();
		ctx.close();
	}
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive: " );  

        ctx.fireChannelActive();
        String response = "12client id is:"+ctx.channel().id().asLongText();  
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));  
    }
}
