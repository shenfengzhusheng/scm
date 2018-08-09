package org.xfs.rpc.simple.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * RPC编码
 * @author xixingyingzhongdui
 *
 */
@SuppressWarnings("rawtypes")
public class RpcEncoder extends MessageToByteEncoder{
	private Class<?>genericClass;
	public RpcEncoder( Class<?>genericClass){
		this.genericClass=genericClass;
	}
	@Override
	protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
		if(genericClass!=null){
			byte[]data=SerializationUtil.serialize(in);
			out.writeInt(data.length);
			out.writeBytes(data);
		}
	}

}
