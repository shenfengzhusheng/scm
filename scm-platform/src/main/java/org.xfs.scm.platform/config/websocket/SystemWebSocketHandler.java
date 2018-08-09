package org.xfs.scm.platform.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.xfs.scm.platform.constant.CommonConstants;

import java.io.IOException;


public class SystemWebSocketHandler implements WebSocketHandler {

    private static final Logger logger= LoggerFactory.getLogger(SystemWebSocketHandler.class);


    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        if(logger.isDebugEnabled()){
            logger.debug("--------------------->afterConnectionEstablished");
        }
        String userKey=(String)webSocketSession.getAttributes().get(CommonConstants.CURRENT_USER_KEY);
        if(userKey!=null){
            CommonConstants.user_session.put(userKey,webSocketSession);
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if(logger.isDebugEnabled()){
            logger.debug("--------------------->handleMessage");
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(logger.isDebugEnabled()){
            logger.debug("--------------------->handleTransportError");
        }
        if(webSocketSession.isOpen()){
           try{
               webSocketSession.close();
           }catch (Exception e){
               logger.error("正常关闭！");
           }
        }
        String userKey=(String)webSocketSession.getAttributes().get(CommonConstants.CURRENT_USER_KEY);
        CommonConstants.user_session.remove(userKey);
    }

    /**
     * 关闭连接后
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        if(logger.isDebugEnabled()){
           logger.debug( "--------------------->afterConnectionClosed");
        }
        String userKey=(String)webSocketSession.getAttributes().get(CommonConstants.CURRENT_USER_KEY);
        if(userKey!=null && CommonConstants.user_session.containsKey(userKey)){
            CommonConstants.user_session.remove(userKey);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        if(logger.isDebugEnabled()){
            logger.debug("---------------supportsPartialMessages---------------------");
        }
        return false;
    }

    public static void send2Users(String message){
    //    System.out.println("--------------------->send2Users");

        CommonConstants.user_session.forEach((userKey,user)->{
          // System.out.println("lambod 表达式");
            if(user!=null){

               if(user.isOpen()){
                  try{
                      user.sendMessage(new TextMessage(userKey+"接收的"+message));
                  }catch(Exception e){
                        logger.error("发送socket消息出错{},{}",userKey ,e.getMessage() );
                  }
               }
           }
        });
    }

    public static void send2User(String userKey,String message){
   //     System.out.println("--------------------->send2User");

        WebSocketSession user=CommonConstants.user_session.get(userKey);
        if(user!=null){
            if(user.isOpen()){
                try {
                    user.sendMessage(new TextMessage(userKey+"========"+message) );
                    user.getTextMessageSizeLimit();
                } catch (IOException e) {
                    logger.error("发送socket消息出错{},{}",userKey ,e.getMessage() );
                }
            }
        }
    }
}
