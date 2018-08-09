package org.xfs.scm.data.business.goods.item.mapper;

import org.xfs.scm.data.business.goods.item.po.ItemPo;
import org.xfs.scm.data.business.goods.item.vo.ItemVo;

import java.util.List;

public interface ItemPoMapper {

    int removeItemPo(ItemVo vo);
    int addItem(ItemVo vo);

    List<ItemPo> getItemPos(ItemVo vo);

    int modifyItemPo(ItemVo record);

}