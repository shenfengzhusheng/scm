package org.xfs.scm.business.pay.record.mapper;



import org.xfs.scm.business.pay.record.entity.PayRecordWithBLOBs;
import org.xfs.scm.business.pay.record.vo.PayRecordVo;

import java.util.List;

public interface PayRecordMapper {
    int removePayRecord(PayRecordVo recordVo);

    int addPayRecord(PayRecordVo recordVo);

    int pay(PayRecordVo recordVo);


    List<PayRecordWithBLOBs>getPayRecords(PayRecordVo recordVo);

    int modifyPayRecord(PayRecordVo record);

}