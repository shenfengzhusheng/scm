package org.xfs.scm.data.business.basic.bill.mapper;

import org.xfs.scm.data.business.basic.bill.entity.Bill;
import org.xfs.scm.data.business.basic.bill.vo.BillVo;

import java.util.List;

public interface BillMapper {
    int removeBill(BillVo vo);

    int addBill(BillVo record);

    List<Bill> getBills(BillVo vo);

    int modifyBill(BillVo record);

}