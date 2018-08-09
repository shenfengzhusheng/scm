package org.xfs.scm.data.business.study.websocket.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.common.utils.date.DateUtil;
import org.xfs.scm.data.business.study.websocket.model.SocketClient;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;
import org.xfs.scm.platform.config.websocket.model.MessageSocketModel;
import org.xfs.scm.platform.constant.CommonConstants;
import org.xfs.scm.platform.config.websocket.SystemWebSocketHandler;
import org.xfs.scm.platform.util.components.Config;
import org.xfs.scm.platform.util.string.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value="/socket")
public class WebSocketTestWeb {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketTestWeb.class);
    @Resource
    private Config config;


    @RequestMapping(method = RequestMethod.GET,value = "/socket")
    public ModelAndView socket(String userName){
        if(logger.isDebugEnabled()){
            logger.debug("imgPath is :"+config.getImagePath());
        }
        ModelAndView modelAndView=new ModelAndView("/socket");
        modelAndView.addObject("uuidUser", IdGenerator.generator());
        return modelAndView;
    }

    @RequestMapping(value = "/setSession",method = RequestMethod.POST)
    public Object setSession(String userName, HttpServletRequest request){
        JsonResponse<String> json=new JsonResponse<String>();
        try{
            Object obj=request.getSession().getAttribute(CommonConstants.CURRENT_USER_KEY);
            if(obj!=null){
                String oldUserName=obj.toString();
                System.out.printf("原先的session是:【%s】",oldUserName);
            }

            if(userName==null || userName==""){
                userName="admin";
            }
            request.getSession().setAttribute(CommonConstants.CURRENT_USER_KEY,userName);
            json.setCode(100);
            json.setMessage("成功！");
        }catch (Exception e){
            json.setMessage(e.getMessage());
        }
        return json;


    }

    @RequestMapping(value = "/delSession",method = RequestMethod.POST)
    public Object delSession(String userName){
        JsonResponse<String> json=new JsonResponse<String>();
        try{
            CommonConstants.user_session.remove(userName);
            System.out.printf("现在有【%s】个连接！",CommonConstants.user_session.size());
            json.setCode(100);
            json.setMessage("成功！");
        }catch (Exception e){
            json.setMessage(e.getMessage());
        }
        return json;


    }
    @ResponseBody
    @RequestMapping(value = "list.do")
    public static Object listClient(){
        Map<String,WebSocketSession> map=CommonConstants.user_session;
        List<SocketClient> clients = new ArrayList<SocketClient>();
        if(!map.isEmpty()){
            for(String key:map.keySet()){
                WebSocketSession user=map.get(key);
                SocketClient client= new SocketClient();

                client.setName(key);
                if(user!=null){
                    client.setId(user.getId());
                    client.setProtocol(user.getAcceptedProtocol());
                }
                clients.add(client);
            }

        }
        return JsonResponse.success("成功",clients);
    }
    @RequestMapping(value="/sendMessage",method = RequestMethod.POST)
    @ResponseBody
    public Object sendMessage(String userName,String message){
        if(!StringUtil.isEmpty(userName)){
            if (StringUtil.isEmpty(message)){
                message="1212121!";
            }
            MessageSocketModel messageSocketModel=new MessageSocketModel();
            messageSocketModel.setId(IdGenerator.generator());
            messageSocketModel.setType(new Random().nextInt(7));
            messageSocketModel.setMessage(message);
            messageSocketModel.setName(TokenManager.getCurrentUser().getUserName());
            messageSocketModel.setSendTime(DateUtil.getNowTime());
            messageSocketModel.setImage(TokenManager.getCurrentUser().getHeaderUrl());
            SystemWebSocketHandler.send2User(userName,messageSocketModel);
        }
        return JsonResponse.success("消息已发送！");
    }

    @RequestMapping(value="/test")
    @ResponseBody
    public void test(HttpSession session){
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    String userKey = (String)session.getAttribute(CommonConstants.CURRENT_USER_KEY);

                    System.out.println("----------------------------------->"+userKey);
                    if(userKey!=null){
                        SystemWebSocketHandler.send2User(userKey,Thread.currentThread().getName()+"Hello World!"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date()));

                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
