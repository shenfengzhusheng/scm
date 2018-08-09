package org.xfs.platform.net.netty.demo.model;

import java.io.Serializable;

public class AskParams implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6545328761492682887L;
	private String auth;
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
}
