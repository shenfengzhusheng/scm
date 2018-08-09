package org.xfs.platform.net.netty.demo.model;

public class LoginMsg extends BaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7034179465254711230L;
	private String userName;
	private String password;
	private String message;

	public LoginMsg() {
		super.setType(MessageTypeEnum.LOGIN);
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
