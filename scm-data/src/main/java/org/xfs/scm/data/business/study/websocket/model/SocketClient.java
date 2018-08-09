package org.xfs.scm.data.business.study.websocket.model;


import java.io.Serializable;

public class SocketClient implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2597598075513537725L;
	private String id;
    private String protocol;
    private String name;
    public SocketClient(){}
    public SocketClient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
