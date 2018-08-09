package org.xfs.scm.data.business.busi.order.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.busi.order.po.BusiOrder;
import org.xfs.scm.data.business.busi.order.vo.BusiOrderVo;

import java.util.List;

public interface BusiOrderServiceI {

    int removeBusiOrder(BusiOrderVo vo);


    int addBusiOrder(BusiOrderVo vo);

    List<BusiOrder> getBusiOrders(BusiOrderVo vo);

    BusiOrder getBusiOrder(BusiOrderVo vo);

    int modifyBusiOrder(BusiOrderVo vo);

    /**
     * 查询订单列表
     * @param vo
     * @return
     */
    List<BusiOrderVo>gridBusiOrders(BusiOrderVo vo);

    /**
     * 查找单条订单信息
     * @param vo
     * @return
     */
    BusiOrderVo getBusiOrderVo(BusiOrderVo vo);


    /**
     * 添加订单
     * @param vo
     * @return
     */
    BusiOrderVo addBusiOrderVo(BusiOrderVo vo);


    Grid<BusiOrderVo> grid(BusiOrderVo vo, Integer page, Integer rows);

}
