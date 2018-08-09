package org.xfs.core.business.goods.item.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.core.business.goods.item.mapper.ItemPoMapper;
import org.xfs.core.business.goods.item.po.ItemPo;
import org.xfs.core.business.goods.item.service.ItemServiceI;

@Service
public class ItemServiceImpl implements ItemServiceI {

    @Resource
    ItemPoMapper itemMapper;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public int removeItem(ItemPo vo) {
        return this.itemMapper.removeItem(vo);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public int addItem(ItemPo record) {
        return this.itemMapper.addItem(record);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @Override
    public ItemPo getItem(Long itemId) {
        return this.itemMapper.getItem(itemId);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, timeout = 20)
    @Override
    public int modifyItem(ItemPo record) {
        int count = this.itemMapper.modifyItem(record);
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }

}
