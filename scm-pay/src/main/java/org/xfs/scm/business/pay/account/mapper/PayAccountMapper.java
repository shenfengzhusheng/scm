package org.xfs.scm.business.pay.account.mapper;



import org.xfs.scm.business.pay.account.entity.PayAccount;
import org.xfs.scm.business.pay.account.vo.PayAccountVo;

import java.util.List;

public interface PayAccountMapper {
    int removePayAccount(PayAccountVo record);


    int addPayAccount(PayAccountVo record);

    List<PayAccount> getPayAccounts(PayAccountVo record);

    int modifyPayAccount(PayAccountVo record);


}