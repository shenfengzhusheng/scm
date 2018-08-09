package org.xfs.scm.data.business.device.device.po;

import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;
import org.xfs.scm.data.business.device.device.entity.Device;

public class DevicePo extends Device {
   private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
