package org.xfs.scm.data.system.payaccount.mapper;

import org.xfs.scm.data.system.payaccount.entity.PayAccount;
import org.xfs.scm.data.system.payaccount.po.PayAccountPo;
import org.xfs.scm.data.system.payaccount.vo.PayAccountVo;

import java.util.List;

public interface PayAccountMapper {
    int removePayAccount(PayAccountVo record);


    int addPayAccount(PayAccountVo record);

    List<PayAccountPo> getPayAccounts(PayAccountVo record);

    PayAccountPo getPayAccount(PayAccountVo record);


    int modifyPayAccount(PayAccountVo record);


}