package org.xfs.scm.data.system.payaccount.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.pay.record.entity.PayRecord;
import org.xfs.scm.data.business.pay.record.po.PayRecordPo;
import org.xfs.scm.data.system.payaccount.entity.PayAccount;
import org.xfs.scm.data.system.payaccount.po.PayAccountPo;
import org.xfs.scm.data.system.payaccount.vo.PayAccountVo;

import java.util.List;

public interface PayAccountServiceI {

    int remove(PayAccountVo vo);

    int add(PayAccountVo vo);

    List<PayAccountPo> find(PayAccountVo vo);

    PayAccountPo get(PayAccountVo vo);


    PayAccountPo getById(Integer id);

    PayAccountPo getByAppId(String appId);

    PayAccountPo getFormCache(String appId);

    Grid<PayAccountPo> grid(PayAccountVo vo);

    int modify(PayAccountVo vo);
}
