package org.xfs.scm.data.business.crm.cumtomer.vo;

import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;

import java.util.Set;

public class CustomerVo extends Customer {
    private int page=1;
    private int rows=10;
    private Set<Long> custIds;
    private int queryType;

    private String q;
    private Long userId;
    private int superFlag;

    public CustomerVo(){}
    public CustomerVo(Long custId){
        super.setCustId(custId);
    }
    public CustomerVo(Set<Long> custIds){
        this.custIds=custIds;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
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

    public Set<Long> getCustIds() {
        return custIds;
    }

    public void setCustIds(Set<Long> custIds) {
        this.custIds = custIds;
    }

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
}
