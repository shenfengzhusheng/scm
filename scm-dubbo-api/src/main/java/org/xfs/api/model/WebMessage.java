package org.xfs.api.model;

import java.io.Serializable;

public class WebMessage  extends CustomProtocol implements Serializable {
    private String receiveTime;


    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }
}
