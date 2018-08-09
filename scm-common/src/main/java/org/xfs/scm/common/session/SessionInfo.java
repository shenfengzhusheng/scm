package org.xfs.scm.common.session;

import java.io.Serializable;
import java.util.List;

public class SessionInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4482683805162202059L;
	private Long userId;
	private String userCode;
	private String userName;
	private String ip;
	private Integer superFlag=0;
	private List<String>resourceList;
	private String token;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getSuperFlag() {
		return superFlag;
	}
	public void setSuperFlag(Integer superFlag) {
		this.superFlag = superFlag;
	}
	public List<String> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
