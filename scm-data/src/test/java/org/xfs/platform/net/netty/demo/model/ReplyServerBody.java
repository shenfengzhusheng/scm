package org.xfs.platform.net.netty.demo.model;

public class ReplyServerBody extends ReplyBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1590420856630038908L;
    private String serverInfo;
    public ReplyServerBody(String serverInfo) {
    	this.serverInfo=serverInfo;
    }
	public String getServerInfo() {
		return serverInfo;
	}
	public void setServerInfo(String serverInfo) {
		this.serverInfo = serverInfo;
	}
	
    
}
