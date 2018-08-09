package org.xfs.scm.platform.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

@EnableWebSocket
public class SocketConfig   implements WebSocketConfigurer {
    private static final Logger logger= LoggerFactory.getLogger(SocketConfig.class);
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        if(logger.isDebugEnabled()){
            logger.debug("---------------------initWebSocket()--------------------------------");
        }
        registry.addHandler(systemWebScoketHandler(),"socketService").addInterceptors(handshakeInterceptor()).setAllowedOrigins("*");
        registry.addHandler(systemWebScoketHandler(),"sockjs/socketService").addInterceptors(handshakeInterceptor()).setAllowedOrigins("*").withSockJS();
    }

    @Bean
    public WebSocketHandler systemWebScoketHandler(){
        return new SystemWebSocketHandler();
    }
    @Bean
    public HandshakeInterceptor handshakeInterceptor(){
        return new WebSocketHandshakeInterceptor();
    }
}
