package org.xfs.platform.net.netty.demo.model;

public class PingMsg extends BaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 629638187843531419L;
	public PingMsg() {
		super.setType(MessageTypeEnum.PING);
	}
}
