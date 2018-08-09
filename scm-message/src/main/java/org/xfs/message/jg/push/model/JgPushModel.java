package org.xfs.message.jg.push.model;

import java.io.Serializable;
import java.util.Map;

public class JgPushModel implements Serializable {
    private String cid;
    private Map<String,Object> platform;
    private Map<String,Object> audience;
    private Map<String,Object> notification;
    private Map<String,Object> message;
    private Map<String,Object> sms_message;
    private Map<String,Object> options;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Map<String,Object> getPlatform() {
        return platform;
    }

    public void setPlatform(Map<String,Object> platform) {
        this.platform = platform;
    }

    public Map<String, Object> getAudience() {
        return audience;
    }

    public void setAudience(Map<String, Object> audience) {
        this.audience = audience;
    }

    public Map<String, Object> getNotification() {
        return notification;
    }

    public void setNotification(Map<String, Object> notification) {
        this.notification = notification;
    }

    public Map<String, Object> getMessage() {
        return message;
    }

    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }

    public Map<String, Object> getSms_message() {
        return sms_message;
    }

    public void setSms_message(Map<String, Object> sms_message) {
        this.sms_message = sms_message;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }
}
