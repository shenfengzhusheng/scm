package org.xfs.netty.server.advance.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.netty.server.advance.config.NettyConfig;

import java.util.Map;
import java.util.Set;

public class NettyTCPServer extends AbstractNettyServer {
    private static final Logger logger = LoggerFactory.getLogger(NettyTCPServer.class);
    private ServerBootstrap serverBootstrap;

    public NettyTCPServer(NettyConfig nettyConfig, ChannelInitializer<? extends Channel>channelInitializer){
        super(nettyConfig,channelInitializer);
    }

    @Override
    public void startServer() throws Exception {
        try{
            this.serverBootstrap=new ServerBootstrap();
            Map<ChannelOption<?>,Object>channelOptions=nettyConfig.getChannelOptions();
            if(null!=channelOptions){
                Set<ChannelOption<?>>keySet=channelOptions.keySet();
                // 获取configuration配置到channelOption
                for (ChannelOption option:keySet){
                    // 获取configuration配置到channelOption
                    serverBootstrap.option(option,channelOptions.get(channelOptions));
                }
            }
            // reactor多线程模型，配置bossGroup和workGroup
            // bossGroup和workGroup使用spring容器管理
            serverBootstrap.group(getBossGroup(),getWorkerGroup())
                    .channel(NioServerSocketChannel.class)
                    .childHandler(getChannelInitializer());
            // 绑定端口，启动并监听
            Channel serverChannel=serverBootstrap.bind(nettyConfig.getSocketAddress()).sync().channel();
            ALL_CHANNELS.add(serverChannel);
        }catch (Exception e){
            logger.error("TCP Server start error {}, going to shut down", e);
            super.stopServer();
            throw e;
        }
    }
    @Override
    public TransmissionProtocol getTransmissionProtocol() {
        return TRANSMISSION_PROTOCOL.TCP;
    }
    @Override
    public void setChannelInitializer(ChannelInitializer<? extends Channel> initializer) {
        this.channelInitializer=initializer;
    }

    @Override
    public String toString() {
        return "NettyTCPServer[" +
                "nettyConfig=" + nettyConfig +
                ", channelInitializer=" + channelInitializer +
                ']';
    }
}
