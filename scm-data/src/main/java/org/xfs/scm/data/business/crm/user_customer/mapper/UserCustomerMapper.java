package org.xfs.scm.data.business.crm.user_customer.mapper;

import org.xfs.scm.data.business.crm.user_customer.entity.UserCustomer;
import org.xfs.scm.data.business.crm.user_customer.vo.UserCustomerVo;

import java.util.List;

public interface UserCustomerMapper {
    int removeUserCustomer(UserCustomerVo vo);

    int addUserCustomer(UserCustomerVo record);

    int addUserCustomers(List<UserCustomerVo> list);


    List<UserCustomer> getUserCustomers(UserCustomerVo vo);

    int modifyUserCustomer(UserCustomerVo record);
}