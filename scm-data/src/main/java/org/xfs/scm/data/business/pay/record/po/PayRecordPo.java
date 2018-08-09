package org.xfs.scm.data.business.pay.record.po;

import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;
import org.xfs.scm.data.business.pay.record.entity.PayRecordWithBLOBs;

public class PayRecordPo extends PayRecordWithBLOBs {
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
