package org.xfs.scm.platform.config.netty;

import org.xfs.scm.platform.config.netty.socket.NettyServerBootstrap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class NettyListener  implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.err.println("nettyListener Startup!");
        new Thread(){
            @Override
            public  void run(){
                try {
                    new NettyServerBootstrap(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        System.err.println("nettyListener end!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
