package org.xfs.scm.data.business.goods.item.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.goods.item.po.ItemPo;
import org.xfs.scm.data.business.goods.item.vo.ItemVo;

import java.util.List;

public interface ItemServiceI {


    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    int removeItemPo(ItemVo vo);

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    int addItemPo(ItemVo vo);

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    List<ItemPo> getItemPos(ItemVo vo);

    Grid<ItemPo> grid(ItemVo vo, Integer page, Integer rows);

    ItemPo getItemPo(ItemVo vo);
    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    int modifyItemPo(ItemVo record);
}
