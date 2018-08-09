package org.xfs.scm.platform.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.platform.constant.CommonConstants;

import java.io.IOException;


public class SystemWebSocketHandler implements WebSocketHandler {

    private static final Logger logger= LoggerFactory.getLogger(SystemWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        if(logger.isInfoEnabled()){
            logger.info("---------now has【"+(CommonConstants.user_session.keySet().size())+"】client site------------");
        }
        String userKey=(String)webSocketSession.getAttributes().get(CommonConstants.CURRENT_USER_KEY);
        if(userKey!=null){
            CommonConstants.user_session.put(userKey,webSocketSession);
        }else{
            logger.info("------afterConnectionEstablished------------but not userKey--->");
            CommonConstants.user_session.put(IdGenerator.generator(),webSocketSession);
        }

        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户

    }

    /**
     * 接收消息
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if(logger.isInfoEnabled()){
            logger.info("--------------------->handleMessage");
            String userKey=(String)webSocketSession.getAttributes().get(CommonConstants.CURRENT_USER_KEY);
            if(userKey!=null){
//                logger.info("client site is :"+userKey);
                String receiverMessage= (String)webSocketMessage.getPayload();
//                logger.info("receiver client site message:"+receiverMessage);
                try{
                    send2User(userKey,"receive message:"+receiverMessage);
                }catch(Exception e){
                    logger.error("send message error!",e);
                }
            }


        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(logger.isInfoEnabled()){
            logger.info("--------------------->handleTransportError");
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

        String userKey=(String)webSocketSession.getAttributes().get(CommonConstants.CURRENT_USER_KEY);
        if(userKey!=null && CommonConstants.user_session.containsKey(userKey)){
            if(logger.isInfoEnabled()){
                logger.info( "-------【"+userKey+"】断开连接-------------->");
            }
            CommonConstants.user_session.remove(userKey);
            System.out.println("系统当前还有【"+(CommonConstants.user_session.keySet().size())+"】个连接!");
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        if(logger.isInfoEnabled()){
            logger.info("---------------supportsPartialMessages---------------------");
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
                } catch (IOException e) {
                    logger.error("发送socket消息出错{},{}",userKey ,e.getMessage() );
                }
            }
        }
    }
}
