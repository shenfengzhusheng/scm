package org.xfs.scm.data.business.crm.user_customer.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.crm.user_customer.entity.UserCustomer;
import org.xfs.scm.data.business.crm.user_customer.vo.GrantUserCustomerVo;
import org.xfs.scm.data.business.crm.user_customer.vo.UserCustomerVo;

import java.util.List;

public interface UserCustomerServiceI {
    int removeUserCustomer(UserCustomerVo vo);

    int addUserCustomer(UserCustomerVo vo);

    int addUserCustomers(List<UserCustomerVo> list);

    List<UserCustomer> find(UserCustomerVo vo);

    Grid<UserCustomer> grid(UserCustomerVo vo);

    UserCustomer get(UserCustomerVo vo);

    int modifyUserCustomer(UserCustomerVo vo);

    boolean grantUserCustomer(GrantUserCustomerVo vo);
}
