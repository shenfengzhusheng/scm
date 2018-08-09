package org.xfs.scm.business.pay.record.service;


import org.xfs.scm.business.pay.ali.model.AliPayBackModel;
import org.xfs.scm.business.pay.record.entity.PayRecordWithBLOBs;
import org.xfs.scm.business.pay.record.vo.PayRecordVo;

import java.util.List;

public interface PayRecordServiceI {

    int removePayRecord(PayRecordVo recordVo);

    int addPayRecord(PayRecordVo recordVo);

    int pay(AliPayBackModel recordVo);

    List<PayRecordWithBLOBs> getPayRecords(PayRecordVo recordVo);

    int modifyPayRecord(PayRecordVo record);
}
