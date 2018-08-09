package org.xfs.netty.server.advance.spring;

import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.xfs.netty.server.advance.config.NettyConfig;
import org.xfs.netty.server.advance.factory.NamedThreadFactory;
import org.xfs.netty.server.advance.init.MyCustomChannelInitializer;
import org.xfs.netty.server.advance.server.NettyTCPServer;

import java.util.HashMap;
import java.util.Map;

@ComponentScan(basePackages = {"org.xfs.netty"})
public class SpringConfig {
    public static Logger LOG= LoggerFactory.getLogger(SpringConfig.class);
    private String bossThreadFactoryName;
    private String workerThreadFactoryName;
    private int port;


    /**
     * 配置线程池
     * @project qht
     * @return
     * @company 企货通(福建)科技有限公司
     * @datetime 2018年4月16日下午4:52:26
     * @versions 1.0
     * @author liujc
     */
    @Bean
    public ThreadPoolTaskExecutor threadPool() {
        ThreadPoolTaskExecutor threadPool=new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(5);//线程池维护线程的最少数量
        threadPool.setKeepAliveSeconds(30000);//线程池维护线程所允许的空闲时间
        threadPool.setMaxPoolSize(50);//线程池维护线程的最大数量
        threadPool.setQueueCapacity(100);//线程池所使用的缓冲队列
        return threadPool;
    }

    /**
     * 引入property配置文件
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyLoader() {
        PropertySourcesPlaceholderConfigurer property = new PropertySourcesPlaceholderConfigurer();
        property.setLocations(
                new ClassPathResource("/config/properties/dev/dubbo.properties"),
                new ClassPathResource("/config/properties/dev/mq.properties"));
        return property;
    }
    @Bean(value = "bossThreadFactory")
    public NamedThreadFactory bossThreadFactory(){
        NamedThreadFactory bossThreadFactory=new NamedThreadFactory("Server-Boss");
        return bossThreadFactory;
    }

    @Bean(value = "workerThreadFactory")
    public NamedThreadFactory workerThreadFactory(){
        NamedThreadFactory workerThreadFactory=new NamedThreadFactory("Server-Worker");
        return workerThreadFactory;
    }



    @Bean(value = "bossGroup")
    public NioEventLoopGroup bossGroup(NamedThreadFactory bossThreadFactory){
        NioEventLoopGroup bossGroup=new NioEventLoopGroup(2,bossThreadFactory);
        return bossGroup;
    }

    @Bean(value = "workerGroup")
    public NioEventLoopGroup workerGroup(NamedThreadFactory workerThreadFactory){
        NioEventLoopGroup workerGroup=new NioEventLoopGroup(3,workerThreadFactory);
        return workerGroup;
    }

    @Bean
    public NettyConfig nettyConfig(NioEventLoopGroup bossGroup,NioEventLoopGroup workerGroup){
        NettyConfig nettyConfig=new NettyConfig();
        nettyConfig.setPortNumber(10000);
        Map<ChannelOption<?>,Object> map=new HashMap<>();
        map.put(io.netty.channel.ChannelOption.SO_KEEPALIVE,true);
        map.put(io.netty.channel.ChannelOption.SO_BACKLOG,100);
        nettyConfig.setChannelOptions(map);
        nettyConfig.setBossGroup(bossGroup);
        nettyConfig.setWorkerGroup(workerGroup);
        return nettyConfig;
    }

    @Bean
    public MyCustomChannelInitializer myCustomChannelInitializer(){
        return new MyCustomChannelInitializer();
    }

    @Bean
    public NettyTCPServer tcpServer(NettyConfig nettyConfig,MyCustomChannelInitializer myCustomChannelInitializer){
        NettyTCPServer tcpServer=new NettyTCPServer(nettyConfig,myCustomChannelInitializer);
        return tcpServer;
    }


}
