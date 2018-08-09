package org.xfs.platform.net.netty.socket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.xfs.platform.net.netty.socket.model.RequestInfoVO;

import java.util.List;

/**
 * 解码器
 */
public class MessageDecoder extends ByteToMessageDecoder{
    private static final int PROTOCOL_NUMBER=0X1243162D;

    public MessageDecoder(){}

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("请求报文长度为："+in.readableBytes());
        if(in.readableBytes()<18){
            return;
        }
        //标记开始读取位置
        in.markReaderIndex();
        int protocol_number=in.readInt();

        if(PROTOCOL_NUMBER!=protocol_number){
            ctx.close();
            return;
        }
        byte[]request_b=new byte[32];

        ByteBuf id =in.readBytes(request_b,0,32);
        System.out.println("requestId is:"+new String(request_b));

        byte version=in.readByte();
        byte type=in.readByte();

        int clientId=in.readInt();

        int length=in.readInt();

        if(in.readableBytes()<length){
            // 重置到开始读取位置
            in.resetReaderIndex();
            return ;
        }

        byte[]body=new byte[length];
        in.readBytes(body);
        int squence=in.readInt();

        RequestInfoVO request=new RequestInfoVO();
        request.setBody(new String(body,"UTF-8"));
        request.setRequestId(new String(request_b));
        request.setClientId(clientId);
        request.setVersion(version);
        request.setType(type);
        request.setSequence(squence);
        request.setLength(length);
        out.add(request);
    }


}
