package org.xfs.scm.data.business.crm.user_customer.vo;

import org.xfs.scm.data.business.crm.user_customer.entity.UserCustomer;

import java.util.ArrayList;
import java.util.List;

public class UserCustomerVo extends UserCustomer {
    private int superFlag;
    private int page=1;
    private int rows=10;

    private List<Long> ids=new ArrayList<>();


    public int getSuperFlag() {
        return superFlag;
    }

    public void setSuperFlag(int superFlag) {
        this.superFlag = superFlag;
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

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
