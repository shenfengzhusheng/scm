package org.xfs.scm.data.business.crm.company.vo;

import org.xfs.scm.data.business.crm.company.entity.Company;

public class CompanyVo extends Company {
    private int page=1;
    private int rows=10 ;
    private String q;
    public CompanyVo(){}

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public CompanyVo(Long comId){
        super.setComId(comId);
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
