package org.xfs.scm.data.system.payaccount.po;

import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;
import org.xfs.scm.data.system.payaccount.entity.PayAccount;

public class PayAccountPo extends PayAccount {
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
