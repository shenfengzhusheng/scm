package org.xfs.netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.xfs.api.model.CustomProtocol;

public class MessageEncoder extends MessageToByteEncoder<CustomProtocol> {
    private static final String DEFAULT_ENCODE="UTF-8";
    private static final int PROTOCOL_NUMBER=0X1243162D;

    public MessageEncoder(){}
    @Override
    protected void encode(ChannelHandlerContext ctx, CustomProtocol requestInfoVO, ByteBuf out) throws Exception {
        ByteBufOutputStream writer=new ByteBufOutputStream(out);

        byte[]body=null;

        if(null!=requestInfoVO && null !=requestInfoVO.getBody()&& ""!=requestInfoVO.getBody()){
            body=requestInfoVO.getBody().getBytes(DEFAULT_ENCODE);
        }
        //String content=
        writer.writeInt(PROTOCOL_NUMBER);
        writer.write(requestInfoVO.getRequestId().getBytes("UTF-8"));
        writer.write(requestInfoVO.getVersion());//version
        writer.write(requestInfoVO.getType());
        writer.writeInt(requestInfoVO.getClientId());
        if(null ==body || 0==body.length){
            writer.writeInt(0);
        }else{
            writer.writeInt(body.length);
            writer.write(body);
        }
        writer.writeInt(requestInfoVO.getSequence());

    }
}
