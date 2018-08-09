package org.xfs.message.model;

import java.io.Serializable;
import java.util.List;

public class PushMessage  implements Serializable{
    private String messageId;
    /**
     * 消息类型:0、单播;1组播;2广播
     */
    private int messageType;
    /**
     * 消息标题
     */
    private String messageTitle;
    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 客户名称
     */
    private List<String> client;

    /**
     * 消息目标系统
     */
    private String messageOs;

    /**
     *  消息模板
     */
    private String messageTemplateId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public List<String> getClient() {
        return client;
    }

    public void setClient(List<String> client) {
        this.client = client;
    }

    public String getMessageOs() {
        return messageOs;
    }

    public void setMessageOs(String messageOs) {
        this.messageOs = messageOs;
    }

    public String getMessageTemplateId() {
        return messageTemplateId;
    }

    public void setMessageTemplateId(String messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
    }
}
