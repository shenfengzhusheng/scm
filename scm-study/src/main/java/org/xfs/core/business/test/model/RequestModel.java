package org.xfs.core.business.test.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RequestModel<T> implements Serializable {
    private String requestId;
    private String appId = "web";
    private T object;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
