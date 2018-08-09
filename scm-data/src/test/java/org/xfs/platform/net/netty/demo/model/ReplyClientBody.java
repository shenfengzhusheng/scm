package org.xfs.platform.net.netty.demo.model;

public class ReplyClientBody extends ReplyBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1590420856630038908L;
    private String clientInfo;
    public ReplyClientBody(String clientInfo) {
    	this.clientInfo=clientInfo;
    }
	public String getClientInfo() {
		return clientInfo;
	}
	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}
    
}
