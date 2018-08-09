package org.xfs.scm.platform.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.platform.constant.CommonConstants;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private static Logger logger= LoggerFactory.getLogger(WebSocketHandshakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        logger.debug("--------some client connect------------------------->beforeHandshake");

        if(request instanceof ServletServerHttpRequest){
            ServletServerHttpRequest servletServerHttpRequest=(ServletServerHttpRequest)request;
            HttpSession session=servletServerHttpRequest.getServletRequest().getSession(false);
            if(session!=null){
                Object object=session.getAttribute(CommonConstants.CURRENT_USER_KEY);
                String userKey=object==null?null:object.toString();
                if(userKey!=null){
                    attributes.put(CommonConstants.CURRENT_USER_KEY,userKey);
                }else{
                    attributes.put(CommonConstants.CURRENT_USER_KEY, IdGenerator.generator());
                }
            }else{
                if(logger.isDebugEnabled()) {
                    logger.debug("no session!");
                }
            }


        }else{
            logger.debug("--------some client connect---error---------------------->beforeHandshake");

            if(logger.isDebugEnabled()){
                logger.debug("ServerHttpRequest is:"+request.getClass().getName());
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        if(logger.isDebugEnabled()){
            logger.debug("--------------------------------->afterHandshake");
        }
    }
}
