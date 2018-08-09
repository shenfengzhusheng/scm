package org.xfs.core.business.goods.item.mapper;

import org.xfs.core.business.goods.item.po.ItemPo;

public interface ItemPoMapper {
    int removeItem(ItemPo vo);

    int addItem(ItemPo record);

    ItemPo getItem(Long itemId);

    int modifyItem(ItemPo record);

}
