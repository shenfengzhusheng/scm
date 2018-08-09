package org.xfs.scm.business.pay.account.vo;


import org.xfs.scm.business.pay.account.entity.PayAccount;

public class PayAccountVo extends PayAccount {
    private Integer page=1;
    private Integer rows=10;
    public PayAccountVo(){}
    public PayAccountVo(String appid){
        super.setAppid(appid);
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
