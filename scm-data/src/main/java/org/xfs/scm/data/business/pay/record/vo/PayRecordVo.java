package org.xfs.scm.data.business.pay.record.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.xfs.scm.data.business.pay.record.entity.PayRecordWithBLOBs;

import java.util.Date;

public class PayRecordVo extends PayRecordWithBLOBs {
    public PayRecordVo(){}
    public PayRecordVo(Long id){
        super.setId(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date startPayTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date endPayTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date startCreatedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date endCreatedTime;

    private String payQrcode;
    private String oldPayStatus;

    private int page=1;
    private int rows=20;

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

    public Date getStartPayTime() {
        return startPayTime;
    }

    public void setStartPayTime(Date startPayTime) {
        this.startPayTime = startPayTime;
    }

    public Date getEndPayTime() {
        return endPayTime;
    }

    public void setEndPayTime(Date endPayTime) {
        this.endPayTime = endPayTime;
    }

    public Date getStartCreatedTime() {
        return startCreatedTime;
    }

    public void setStartCreatedTime(Date startCreatedTime) {
        this.startCreatedTime = startCreatedTime;
    }

    public Date getEndCreatedTime() {
        return endCreatedTime;
    }

    public void setEndCreatedTime(Date endCreatedTime) {
        this.endCreatedTime = endCreatedTime;
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
