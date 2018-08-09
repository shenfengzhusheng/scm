package org.xfs.message.jg.push.impl;


import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.CIDResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.message.MessagePushI;
import org.xfs.message.model.PushMessage;
import org.xfs.scm.common.utils.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JiGuangPushImpl implements MessagePushI {
    private static final Logger logger= LoggerFactory.getLogger( JiGuangPushImpl.class );
    private static final String PUSH_URL=" https://api.jpush.cn/v3/push";
    private static final String CID_URL="https://api.jpush.cn/v3/push/cid";

    private static String APPKEY="d060849b56686ccc1a674ac4";
    private static String MASTER_SECRET="20d7dc86f5ba2252f97cd5bb" ;
    public static void main(String[]args){

        PushMessage message=new PushMessage();
        String cid="d060849b56686ccc1a674ac4-94a5a399-aecf-4a51-9d43-52deaaac1d6f";
        //cid=getCid();
        message.setMessageId(cid);
        message.setMessageContent("通信测试！");
        message.setMessageTitle("业务通知1111");
        List<String> client=new ArrayList<String>();
        client.add("18071adc03062cf9fd1");
        client.add("13065ffa4e03b890381");
        message.setClient(client);
        //System.out.println();
        push(message);
    }

    public static String  getCid(){
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APPKEY, null, ClientConfig.getInstance());
        // For push, all you need do is to build PushPayload object.
        try {
            CIDResult result = jpushClient.getCidList(1,"push");
            logger.info("Got result - " + result);
            if(result.isResultOK()){
                return result.cidlist.get(0);
            }
        } catch (APIConnectionException e) {
            // Connection error, should retry later
            logger.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            logger.error("Should review the error, and fix the request", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }
        return null;
    }

    public static void push(PushMessage message){
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APPKEY, null, ClientConfig.getInstance());
        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushAndroidObject(message);
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            logger.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            logger.error("Should review the error, and fix the request", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }
    }

    public static PushPayload buildPushObject_all_all_alert(String content) {
        return PushPayload.alertAll(content);
    }

    public static PushPayload buildPushObject_all_alias_alert(String content,String alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
              //  .setAudience(Audience.alias("alias1"))
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(content))
                .build();
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle(String content,String tag) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
              //  .setAudience(Audience.tag("tag1"))
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.android(content, "测试title", null))
                .build();
    }

    public static PushPayload buildPushAndroidObject(PushMessage message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setCid(message.getMessageId())

                //  .setAudience(Audience.tag("tag1"))
                .setAudience(Audience.registrationId(message.getClient()))
                .setNotification(Notification.android(message.getMessageContent(), message.getMessageTitle(), null))
                .build();
    }

    @Override
    public boolean pushNotification(PushMessage message) {
        return false;
    }
}
