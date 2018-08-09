package org.xfs.platform.net.netty.demo.model;

/**
 * 响应类型消息：

 * @author fengling9874
 *
 */
public class ReplyMsg extends BaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5500422162387127943L;
	public ReplyMsg() {
		super.setType(MessageTypeEnum.REPLY);
	}
    private ReplyBody body;
	public ReplyBody getBody() {
		return body;
	}
	public void setBody(ReplyBody body) {
		this.body = body;
	}

}
