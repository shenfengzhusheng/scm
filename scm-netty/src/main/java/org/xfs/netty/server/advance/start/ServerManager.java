package org.xfs.netty.server.advance.start;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.xfs.mq.spring.config.ActiveMqConfig;
import org.xfs.netty.server.advance.server.AbstractNettyServer;
import org.xfs.netty.server.advance.spring.AppContext;
import org.xfs.netty.server.advance.spring.SpringConfig;

public class ServerManager {
    private AbstractNettyServer tcpServer;

    public ServerManager() {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(ActiveMqConfig.class,SpringConfig.class);
       // tcpServer = (AbstractNettyServer) applicationContext.getBean(AppContext.TCP_SERVER);
        tcpServer =applicationContext.getBean(AbstractNettyServer.class);
    }

    public void startServer(int port) throws Exception {
        tcpServer.startServer(port);
    }

    public void startServer() throws Exception {
        tcpServer.startServer();
    }
    public void stopServer() throws Exception {
        tcpServer.stopServer();
    }
}
