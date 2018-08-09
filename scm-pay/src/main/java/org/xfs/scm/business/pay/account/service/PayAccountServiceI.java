package org.xfs.scm.business.pay.account.service;

import org.xfs.scm.business.pay.account.entity.PayAccount;
import org.xfs.scm.business.pay.account.vo.PayAccountVo;
import org.xfs.scm.common.base.model.Grid;


import java.util.List;

public interface PayAccountServiceI {

    int removePayAccount(PayAccountVo vo);

    int addPayAccount(PayAccountVo vo);

    List<PayAccount> getPayAccounts(PayAccountVo vo);

    PayAccount getPayAccount(PayAccountVo vo);


    Grid<PayAccount> grid(PayAccountVo vo);

    int modifyPayAccount(PayAccountVo vo);
}
