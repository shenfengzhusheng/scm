package org.xfs.scm.data.business.pay.record.mapper;

import org.xfs.scm.data.business.pay.record.entity.PayRecord;
import org.xfs.scm.data.business.pay.record.po.PayRecordPo;
import org.xfs.scm.data.business.pay.record.vo.PayRecordVo;

import java.util.List;

public interface PayRecordMapper {
    int removePayRecord(PayRecordVo recordVo);

    int addPayRecord(PayRecordVo recordVo);

    int pay(PayRecordVo recordVo);


    List<PayRecordPo>getPayRecords(PayRecordVo recordVo);

    int modifyPayRecord(PayRecordVo record);

    PayRecordPo getPayRecordStatus(PayRecordVo recordVo);

}