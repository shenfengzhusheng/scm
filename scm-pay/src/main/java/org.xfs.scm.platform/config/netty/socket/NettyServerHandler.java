package org.xfs.scm.platform.config.netty.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.scm.platform.config.netty.socket.model.ChannelContants;
import org.xfs.scm.platform.config.netty.socket.model.MessageTypeEnums;
import org.xfs.scm.platform.config.netty.socket.model.RequestInfoVO;

@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<RequestInfoVO> {
    private static final Logger logger= LoggerFactory.getLogger(NettyServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestInfoVO requestInfoVO) throws Exception {
        System.out.println("receive message:"+requestInfoVO);
        requestInfoVO.setSequence(requestInfoVO.getSequence()+1);

        if(requestInfoVO!=null){
            if(requestInfoVO.getType()!= MessageTypeEnums.DEVICE_CONNECT.getCode()){
                if(this.checkCommunication(channelHandlerContext,requestInfoVO)){
                    // return;
                    switch (requestInfoVO.getType()){
                        case 2:
                            System.out.println("设备初始化：");
                            requestInfoVO.setBody("设备初始化命令开始！");
                            channelHandlerContext.channel().writeAndFlush(requestInfoVO);

                            break;
                        case 3:
                            System.out.println("设备状态上报：");
                            requestInfoVO.setBody("设备状态上报接收完毕！");

                            channelHandlerContext.channel().writeAndFlush(requestInfoVO);

                            break;
                        case 4:
                            System.out.println("设备破坏警报：");
                            requestInfoVO.setBody("设备破坏警报接收完毕！");

                            channelHandlerContext.channel().writeAndFlush(requestInfoVO);

                            break;
                        case 5:
                            System.out.println("设备停电警报：");
                            requestInfoVO.setBody("设备停电警报接收完毕！");

                            channelHandlerContext.channel().writeAndFlush(requestInfoVO);

                            break;
                        case 6:
                            System.out.println("设备信号警报：");
                            channelHandlerContext.channel().writeAndFlush(requestInfoVO);

                            break;
                        default:
                            System.out.println("未知操作类型异常！");
                            requestInfoVO.setBody("非法请求！");

                            channelHandlerContext.channel().writeAndFlush(requestInfoVO);
                            //   channelHandlerContext.channel().disconnect();
                            break;
                    }
                }
            }else{
                System.out.println("设备登陆连接：");
                requestInfoVO.setBody("设备连接成功,可以通信了！");
                ChannelContants.add(requestInfoVO.getClientId(),(SocketChannel)channelHandlerContext.channel());
                channelHandlerContext.channel().writeAndFlush(requestInfoVO);
            }

        }
    }
    private boolean  checkCommunication(ChannelHandlerContext channelHandlerContext,RequestInfoVO requestInfoVO){
        Channel channel=ChannelContants.get(requestInfoVO.getClientId());
        System.out.println("channel:"+channel);
        if(channel==null){
            System.out.println("非法连接！");
            requestInfoVO.setBody("非法设备请求！");
            //  ChannelContants.remove(requestInfoVO.getClientId()+"");
            return false;
        }
        return true;
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("["+ctx.channel().id().asShortText()+"]连接异常:");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("["+ctx.channel().id().asShortText()+"]连接断开:");
        ChannelContants.remove((SocketChannel) ctx.channel());
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("["+ctx.channel().id().asShortText()+"]连接未注册:");

    }
}
