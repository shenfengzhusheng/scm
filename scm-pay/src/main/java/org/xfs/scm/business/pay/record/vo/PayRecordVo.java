package org.xfs.scm.business.pay.record.vo;

import org.xfs.scm.business.pay.record.entity.PayRecordWithBLOBs;

public class PayRecordVo extends PayRecordWithBLOBs {

    private String payQrcode;
    private String oldPayStatus;

    public String getPayQrcode() {
        return payQrcode;
    }

    public void setPayQrcode(String payQrcode) {
        this.payQrcode = payQrcode;
    }

    public String getOldPayStatus() {
        return oldPayStatus;
    }

    public void setOldPayStatus(String oldPayStatus) {
        this.oldPayStatus = oldPayStatus;
    }
}
