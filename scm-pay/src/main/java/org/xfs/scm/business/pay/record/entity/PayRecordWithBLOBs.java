package org.xfs.scm.business.pay.record.entity;

public class PayRecordWithBLOBs extends PayRecord {
    private String body;

    private String memo;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}