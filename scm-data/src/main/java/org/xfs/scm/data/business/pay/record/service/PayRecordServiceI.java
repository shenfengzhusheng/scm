package org.xfs.scm.data.business.pay.record.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.pay.ali.model.AliPayBackModel;
import org.xfs.scm.data.business.pay.record.po.PayRecordPo;
import org.xfs.scm.data.business.pay.record.vo.PayRecordVo;

import java.util.List;

public interface PayRecordServiceI {

    int remove(PayRecordVo recordVo);

    int add(PayRecordVo recordVo);

    int pay(AliPayBackModel recordVo);

    int pay(PayRecordVo recordVo);


    List<PayRecordPo> find(PayRecordVo recordVo);

    Grid<PayRecordPo> grid(PayRecordVo recordVo);

    PayRecordPo get(PayRecordVo recordVo);

    PayRecordPo getPayRecordStatus(PayRecordVo recordVo);


    int modify(PayRecordVo record);
}
