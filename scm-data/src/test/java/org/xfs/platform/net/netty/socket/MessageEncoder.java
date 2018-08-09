package org.xfs.platform.net.netty.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.xfs.platform.net.netty.socket.model.RequestInfoVO;

public class MessageEncoder extends MessageToByteEncoder<RequestInfoVO> {
    private static final String DEFAULT_ENCODE="UTF-8";
    private static final int PROTOCOL_NUMBER=0X1243162D;

    public MessageEncoder(){}
    @Override
    protected void encode(ChannelHandlerContext ctx, RequestInfoVO requestInfoVO, ByteBuf out) throws Exception {
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
