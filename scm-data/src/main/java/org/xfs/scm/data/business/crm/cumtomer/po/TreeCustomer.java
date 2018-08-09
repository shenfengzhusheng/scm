package org.xfs.scm.data.business.crm.cumtomer.po;

import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;

import java.util.List;


public class TreeCustomer extends Customer {
    private List<TreeCustomer> children;

    public List<TreeCustomer> getChildren() {
        return children;
    }

    public void setChildren(List<TreeCustomer> children) {
        this.children = children;
    }
}
