package org.xfs.scm.goods.item.dao;

import java.util.List;

import org.xfs.scm.item.entity.Item;
import org.xfs.scm.item.vo.ItemVo;

import com.github.abel533.mapper.Mapper;

public interface ItemMapper extends Mapper<Item>{
    int removeItem(ItemVo vo);

    int addItem(ItemVo vo);

    List<Item> getItem(ItemVo vo);

    int modfiyItem(ItemVo record);
}