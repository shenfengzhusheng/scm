package org.xfs.scm.data.business.busi.order.mapper;

import org.xfs.scm.data.business.busi.order.po.BusiOrder;
import org.xfs.scm.data.business.busi.order.vo.BusiOrderVo;

import java.util.List;

public interface BusiOrderMapper {
    int removeBusiOrder(BusiOrderVo vo);


    int addBusiOrder(BusiOrderVo vo);

    List<BusiOrder> getBusiOrders(BusiOrderVo vo);

    int modifyBusiOrder(BusiOrderVo vo);

    List<BusiOrderVo>gridBusiOrders(BusiOrderVo vo);

}