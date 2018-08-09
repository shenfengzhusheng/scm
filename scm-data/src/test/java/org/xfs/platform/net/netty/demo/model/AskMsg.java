package org.xfs.platform.net.netty.demo.model;

public class AskMsg extends BaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 579554810786663138L;
	private AskParams params;
	public AskMsg() {
		super.setType(MessageTypeEnum.ASK);
	}
	public AskParams getParams() {
		return params;
	}
	public void setParams(AskParams params) {
		this.params = params;
	}

}
