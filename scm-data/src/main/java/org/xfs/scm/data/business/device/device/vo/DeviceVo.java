package org.xfs.scm.data.business.device.device.vo;

import org.xfs.scm.data.business.device.device.entity.Device;

public class DeviceVo extends Device {
    private String q;
    private int page=1;
    private int rows=10;
    private String deviceCodeEq;
    public DeviceVo(){}
    public DeviceVo(Long deviceId){
        super.setDeviceId(deviceId);
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getDeviceCodeEq() {
        return deviceCodeEq;
    }

    public void setDeviceCodeEq(String deviceCodeEq) {
        this.deviceCodeEq = deviceCodeEq;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
