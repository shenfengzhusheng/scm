package org.xfs.scm.map.model.base;

import java.io.Serializable;

public class BaseMapModel  implements Serializable {
    private String ak;
    private Integer service_id;
    private String sn;


    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
