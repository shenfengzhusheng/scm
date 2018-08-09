package org.xfs.scm.data.business.study.socket.model;

import java.io.Serializable;

public class SocketModel implements Serializable {
    private Integer clientId;
    private String msg;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
