package org.xfs.scm.data.system.payaccount.vo;

import org.xfs.scm.data.system.payaccount.entity.PayAccount;

public class PayAccountVo extends PayAccount {
    private Integer page=1;
    private Integer rows=10;
    private Long userId;
    private int superFlag;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getSuperFlag() {
        return superFlag;
    }

    public void setSuperFlag(int superFlag) {
        this.superFlag = superFlag;
    }

    public PayAccountVo(){}
    public PayAccountVo(String appid){
        super.setAppid(appid);
    }

    public PayAccountVo(int payId){
        super.setPayId(payId);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
