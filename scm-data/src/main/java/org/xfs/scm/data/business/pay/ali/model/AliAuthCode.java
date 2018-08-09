package org.xfs.scm.data.business.pay.ali.model;

public class AliAuthCode {
    private String app_id;
    private String app_auth_code;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_auth_code() {
        return app_auth_code;
    }

    public void setApp_auth_code(String app_auth_code) {
        this.app_auth_code = app_auth_code;
    }

    @Override
    public String toString() {
        return "AliAuthCode【" +
                "app_id='" + app_id + '\'' +
                ", app_auth_code='" + app_auth_code + '\'' +
                '】';
    }
}
