package org.xfs.scm.data.business.basic.bill.vo;

import org.xfs.scm.data.business.basic.bill.entity.Bill;

public class BillVo extends Bill {
    private String q;
    private int rows;
    private int page;

    public BillVo(){}
    public BillVo(Long billId){
        super.setBillId(billId);
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
