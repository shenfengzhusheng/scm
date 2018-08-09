package org.xfs.test.study.dozer;


public class DItem {
    private int goodId;
    private String goodCode;
    private String goodName;

    private String status = "可用";

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DItem [goodId=" + goodId + ", goodCode=" + goodCode + ", goodName=" + goodName + ",status=" + status + "]";
    }
}
