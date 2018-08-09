package org.xfs.netty.server.advance.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.netty.server.advance.base.NettyServer;
import org.xfs.netty.server.advance.config.NettyConfig;

import java.net.InetSocketAddress;

/**
 * 基本服务器配置类
 */
public abstract class AbstractNettyServer implements NettyServer {
    private static final Logger logger = LoggerFactory.getLogger(AbstractNettyServer.class);
    //用于管理所有的channel
    public static final ChannelGroup ALL_CHANNELS = new DefaultChannelGroup("NADRON-CHANNELS", GlobalEventExecutor.INSTANCE);
    protected final NettyConfig nettyConfig;
    protected ChannelInitializer<? extends Channel> channelInitializer;
    public AbstractNettyServer(NettyConfig nettyConfig,ChannelInitializer<? extends Channel> channelInitializer){
        this.nettyConfig=nettyConfig;
        this.channelInitializer=channelInitializer;
    }

    @Override
    public void startServer(int port) throws Exception {
        nettyConfig.setPortNumber(port);
    }

    @Override
    public void startServer(InetSocketAddress socketAddress) throws Exception {
        nettyConfig.setSocketAddress(socketAddress);
        startServer();
    }

    @Override
    public void stopServer() throws Exception {
        logger.debug("In stopServer method of class: {}", this.getClass() .getName());
        ChannelGroupFuture futures=ALL_CHANNELS.close();
        try{
            futures.await();
        } catch (InterruptedException e){
            logger.error( "Execption occurred while waiting for channels to close: {}", e);
        } finally{
            if (null != nettyConfig.getBossGroup()){
                nettyConfig.getBossGroup().shutdownGracefully();
            }
            if (null != nettyConfig.getWorkerGroup()){
                nettyConfig.getWorkerGroup().shutdownGracefully();
            }
        }
    }

    @Override
    public InetSocketAddress getSocketAddress() {
        return nettyConfig.getSocketAddress();
    }
    @Override
    public ChannelInitializer<? extends Channel> getChannelInitializer() {
        return this.channelInitializer;
    }

    @Override
    public NettyConfig getNettyConfig() {
        return this.nettyConfig;
    }
    // 获取bossGroup，在spring中配置
    protected EventLoopGroup getBossGroup(){
        return nettyConfig.getBossGroup();
    }

    // 获取workerGroup， 在spring中配置
    protected EventLoopGroup getWorkerGroup(){
        return nettyConfig.getWorkerGroup();
    }

    @Override
    public String toString() {
        return "AbstractNettyServer[" +
                "nettyConfig=" + nettyConfig +
                ", channelInitializer=" + channelInitializer +
                ']';
    }
}
