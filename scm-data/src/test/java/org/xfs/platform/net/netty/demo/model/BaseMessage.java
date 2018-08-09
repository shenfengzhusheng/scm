package org.xfs.platform.net.netty.demo.model;

import java.io.Serializable;
/**
 * //必须实现序列,serialVersionUID 一定要有,否者在netty消息序列化反序列化会有问题，接收不到消息！！！

 * @author fengling9874
 *
 */
public abstract class BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8722175055879183816L;
	
	private MessageTypeEnum type;
    //必须唯一，否者会出现channel调用混乱
	private String clientId;
    //初始化客户端id
	public  BaseMessage() {
		this.clientId=NettyConstants.getClientId();
	}
	public MessageTypeEnum getType() {
		return type;
	}
	public void setType(MessageTypeEnum type) {
		this.type = type;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
	
	
}
