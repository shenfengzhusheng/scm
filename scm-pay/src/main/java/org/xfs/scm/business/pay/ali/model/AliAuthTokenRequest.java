package org.xfs.scm.business.pay.ali.model;

public class AliAuthTokenRequest {
    private String grant_type="authorization_code";
    private String code;
    public AliAuthTokenRequest(){}
    public AliAuthTokenRequest(String code) {
        this.code = code;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
