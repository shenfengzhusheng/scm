package org.xfs.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.xfs.api.enums.MessageTypeEnums;
import org.xfs.api.model.CustomProtocol;
import org.xfs.api.model.WebMessage;
import org.xfs.netty.constant.ChannelContants;
import org.xfs.netty.server.advance.service.MqService;
import org.xfs.netty.server.advance.spring.AppContext;
import org.xfs.scm.common.utils.BeanUtils;
import org.xfs.scm.common.utils.date.DateUtil;


@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<CustomProtocol> {
    private static final Logger logger= LoggerFactory.getLogger(NettyServerHandler.class);
//
//

    public NettyServerHandler(){

    }
    public NettyServerHandler(JmsTemplate jmsTemplate){
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CustomProtocol request) throws Exception {
        request.setSequence(request.getSequence()+1);
        if(request!=null){
            if(request.getType()<100){
                MqService mqService=AppContext.getBean(MqService.class);
                WebMessage webMessage=new WebMessage();
                webMessage.setReceiveTime(DateUtil.getNowTime());
                BeanUtils.copyNotNullProperties(request,webMessage);
                mqService.sendMessage(webMessage);
            }
            if(request.getType()!= MessageTypeEnums.DEVICE_CONNECT.getCode()){
                switch (request.getType()){
                    case 2:
                        System.out.println("设备初始化：");
                        request.setBody("设备初始化命令开始！");
                        channelHandlerContext.channel().writeAndFlush(request);

                        break;
                    case 3:
                        System.out.println("设备状态上报：");
                        request.setBody("设备状态上报接收完毕！");

                        channelHandlerContext.channel().writeAndFlush(request);

                        break;
                    case 4:
                        System.out.println("设备破坏警报：");
                        request.setBody("设备破坏警报接收完毕！");

                        channelHandlerContext.channel().writeAndFlush(request);

                        break;
                    case 5:
                        System.out.println("设备停电警报：");
                        request.setBody("设备停电警报接收完毕！");

                        channelHandlerContext.channel().writeAndFlush(request);

                        break;
                    case 6:
                        System.out.println("设备信号警报：");
                        channelHandlerContext.channel().writeAndFlush(request);

                        break;
                    default:
                        System.out.println("未知操作类型异常！");
                        request.setBody("非法请求！");

                        channelHandlerContext.channel().writeAndFlush(request);
                        //   channelHandlerContext.channel().disconnect();
                        break;
                }
            }else{
                System.out.println("设备登陆连接：");
                request.setBody("设备连接成功,可以通信了！");
                ChannelContants.add(request.getClientId(),(SocketChannel)channelHandlerContext.channel());
                channelHandlerContext.channel().writeAndFlush(request);

            }
        }
   //     System.out.println("-------------最终处理---------------------->");


    }
    private boolean  checkCommunication(ChannelHandlerContext channelHandlerContext,CustomProtocol request){
        Channel channel=ChannelContants.get(request.getClientId());
       // System.out.println("channel:"+channel);
        if(channel==null){
            System.out.println("非法连接！");
            request.setBody("非法设备请求！");
            ChannelContants.remove(request.getClientId());
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
