package org.xfs.scm.data.business.crm.cumtomer.mapper;

import org.xfs.scm.data.business.crm.cumtomer.entity.Customer;
import org.xfs.scm.data.business.crm.cumtomer.vo.CustomerVo;

import java.util.List;

public interface CustomerMapper {
    int removeCustomer(CustomerVo vo);

    int addCustomer(CustomerVo vo);

    List<Customer> getCustomers(CustomerVo vo);

    int modifyCustomer(Customer record);

}