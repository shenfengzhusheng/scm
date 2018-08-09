package org.xfs.core.business.goods.item.service;

import org.xfs.core.business.goods.item.po.ItemPo;

public interface ItemServiceI {
    int removeItem(ItemPo vo);

    int addItem(ItemPo record);

    ItemPo getItem(Long itemId);

    int modifyItem(ItemPo record);
}
