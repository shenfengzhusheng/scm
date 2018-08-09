package org.xfs.scm.data.business.crm.cumtomer.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;
import org.xfs.scm.data.business.crm.cumtomer.vo.CustomerVo;

import java.util.List;

public interface CustomerServiceI {

    int removeCustomer(CustomerVo vo);

    int addCustomer(CustomerVo vo);

    List<Customer> getCustomers(CustomerVo vo);

    Customer getCustomer(CustomerVo vo);

    Grid<Customer> grid(CustomerVo vo);

    int modifyCustomer(Customer record);
}
