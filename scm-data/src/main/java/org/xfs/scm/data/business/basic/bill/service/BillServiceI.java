package org.xfs.scm.data.business.basic.bill.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.basic.bill.entity.Bill;
import org.xfs.scm.data.business.basic.bill.vo.BillVo;

import java.util.List;

public interface BillServiceI {
    int remove(BillVo vo);

    int add(BillVo record);

    List<Bill> find(BillVo vo);

    Grid<Bill> grid(BillVo vo);

    Bill get(BillVo vo);

    int modify(BillVo record);
}
